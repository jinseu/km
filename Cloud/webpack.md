## Webpack

webpack 的安装非常简单，只需要运行`npm install -D webpack webpack-cli`即可。

### 基本概念

webpack 的所有的行为都通过配置文件指定，配置文件的默认路径是`webpack.config.js`，也可以通过命令行参数指定。

webpack的核心概念如下

1. entry  说明了应该使用哪个模块作为构建依赖图的起点，可以指定一个或多个。
2. output 说明了在哪里生成所创建的bundles，以及bundles的命名 
3. loader 说明了对不同类型的依赖文件，该如何处理。默认只支持JavaScript
4. plugins 说明了以插件的形式加载的扩展功能。包括：打包优化、资源管理和注入环境变量

### 实践


### 原理

### FAQ

#### Webpack 的路径配置

1. context 是webpack 编译时的基础目录，entry会相对此目录进行查找。context的默认值为`procss.cwd()`，即package.json所在的目录，context目录需要配置为绝对路径。
2. output 是打包文件的输出目录，也必须配置为绝对路径。默认值和context一样都是`procss.cwd()`
3. output.publicPath 是存放静态资源的路径，默认值为空。事实上`静态资源最终访问路径 = output.publicPath + 资源 loader 或插件等配置路径`，ExtractTextPlugin等插件也会被output.publicPath 所影响。output.public 可以设置为绝对路径，相对路径，甚至是URL（适用于静态资源部署在CDN上的场景）。注意，output.public 是最终在html文件中看到的路径，所以应该以`/`，同时其他loader或者插件的配置不能以`/`开头。
4. webpack-dev-server publicPath 这个值一般回合output.publicPath 一致（除了output.publicPath为URL时）。这是因为在dev-server中，打包的内容是放在内存中，通过express匹配请求路径，然后读取对应的资源输出。但是对于webpack中loader和插件而言，仍然是以output.publicPath，所以二者只有一样时，才可以正常访问匹配路径。
5. html-webpack-plugin 插件有两处涉及到路径resolve。
 * template 的路径是相对于output.context
 * filename 的路径是相对于output.path
