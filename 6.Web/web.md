### 缓存

#### manifest

可以在html元素上添加manifest属性来开启，页面缓存，例如`manifest="example.appcache"`。

开启缓存后，页面加载资源的流程如下：

1. 当浏览器访问一个包含 manifest 特性的文档时，如果应用缓存不存在，浏览器会加载文档，然后获取所有在清单文件中列出的文件，生成应用缓存的第一个版本。
2. 对该文档的后续访问会使浏览器直接从应用缓存(而不是服务器)中加载文档与其他在清单文件中列出的资源。此外，浏览器还会向 window.applicationCache 对象发送一个 checking 事件，在遵循合适的 HTTP 缓存规则前提下，获取清单文件。
3. 如果当前缓存的清单副本是最新的，浏览器将向 applicationCache 对象发送一个 noupdate 事件，到此，更新过程结束。注意，如果你在服务器修改了任何缓存资源，同时也应该修改清单文件，这样浏览器才能知道它需要重新获取资源。
3. 如果清单文件已经改变，文件中列出的所有文件—也包括通过调用 applicationCache.add() 方法添加到缓存中的那些文件—会被获取并放到一个临时缓存中，遵循适当的 HTTP 缓存规则。对于每个加入到临时缓存中的文件，浏览器会向 applicationCache 对象发送一个 progress 事件。如果出现任何错误，浏览器会发送一个 error 事件，并暂停更新。
4. 一旦所有文件都获取成功，它们会自动移送到真正的离线缓存中，并向  applicationCache 对象发送一个 cached 事件。鉴于文档早已经被从缓存加载到浏览器中，所以更新后的文档不会重新渲染，直到页面重新加载(可以手动或通过程序).

其中example.appcache是缓存清单文件，格式如下

```
CACHE MANIFEST
# v1 2011-08-14
# This is another comment
index.html
cache.html
style.css
image1.png

# Use from network if available
NETWORK:
network.html

# Fallback content
FALLBACK:
/ fallback.html
```

**CACHE**
这是缓存文件中记录所属的默认段落。在 CACHE: 段落标题后(或直接跟在 CACHE MANIFEST 行后)列出的文件会在它们第一次下载完毕后缓存起来。
**NETWORK**
在 NETWORK: 段落标题下列出的文件是需要与服务器连接的白名单资源。所有类似资源的请求都会绕过缓存，即使用户处于离线状态。可以使用通配符。
**FALLBACK**
FALLBACK: 段指定了一个后备页面，当资源无法访问时，浏览器会使用该页面。该段落的每条记录都列出两个 URI—第一个表示资源，第二个表示后备页面。两个 URI 都必须使用相对路径并且与清单文件同源。可以使用通配符。


#### Cache-Control

**Cache-Control**

**ETag**

ETag 可以看做是服务端缓存技术。ETag HTTP响应头是资源的特定版本的标识符。这可以让缓存更高效，并节省带宽，因为如果内容没有改变，Web服务器不需要发送完整的响应。

HTTP 304 未改变说明无需再次传输请求的内容，也就是说可以使用缓存的内容。这通常是在一些安全的方法（safe），例如GET 或HEAD 或在请求中附带了头部信息： If-None-Match 或If-Modified-Since。

**If-Match**

请求首部 If-Match 的使用表示这是一个条件请求。在请求方法为 GET 和 HEAD 的情况下，服务器仅在请求的资源满足此首部列出的 ETag 之一时才会返回资源。而对于 PUT 或其他非安全方法来说，只有在满足条件的情况下才可以将资源上传。

**If-None-Match**

If-None-Match 是一个条件式请求首部。对于 GETGET 和 HEAD 请求方法来说，当且仅当服务器上没有任何资源的 ETag 属性值与这个首部中列出的相匹配的时候，服务器端会才返回所请求的资源，响应码为  200  。


**If-Modified-Since**
If-Modified-Since 是一个条件式请求首部，服务器只在所请求的资源在给定的日期时间之后对内容进行过修改的情况下才会将资源返回，状态码为 200  。如果请求的资源从那时起未经修改，那么返回一个不带有消息主体的  304  响应，而在 Last-Modified 首部中会带有上次修改时间。 不同于  If-Unmodified-Since, If-Modified-Since 只可以用在 GET 或 HEAD 请求中。

当与 If-None-Match 一同出现时，它会被忽略掉，除非服务器不支持 If-None-Match。

最常见的应用场景是来更新没有特定 ETag 标签的缓存实体。



#### Service Workers

