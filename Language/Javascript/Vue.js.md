## Vue.js

### 组件的生命周期

vue 组件的生命周期如下图

![zujian1](./img/vue_lifecycle.png)

其中，红色标注的部分可以注入自定义的钩子，包括

1. beforeCreate
2. created
3. beforeMount
4. mounted
5. beforeUpdate
6. updated
7. beforeDestroy
8. destroyed


### Virtual Dom

Vue在2.0版本也引入了vdom。其vdom算法是基于snabbdom算法所做的修改。

使用vnode来模拟Virtual DOM，每一个原生DOM元素或者Vue组件都对应一个VNode对象。VNode有两种方式生成，分别是普通DOM元素生成，Vue组件生成。区别在于ComponentOptions的值，普通DOM元素VNode的该值为空。

在具体组成上VNode由data属性（VNodeData），componentOptions（VNodeComponentOptions). 

VnodeData 内部包含VNode的节点数据，包括slot，ref，staticClass，style等。还包括directive属性（VNodeDirective）用来描述VNode存储的指令数据，包括name，value， oldValue等。

**VNode 的创建**




****

```

```


#### 

在component初始化的最后阶段，会调用vm.$mount将实例挂载到dom上，此时会调用mountComponent（src/core/instance/lifecycle.js）方法。

mountComponent方法的具体内容如下：

```
export function mountComponent (
  vm: Component,
  el: ?Element,
  hydrating?: boolean
): Component {
  // vm.$el为真实的node
  vm.$el = el
  // 如果vm上没有挂载render函数
  if (!vm.$options.render) {
    // 空节点
    vm.$options.render = createEmptyVNode
  }
  // 钩子函数
  callHook(vm, 'beforeMount')

  let updateComponent
  /* istanbul ignore if */
  if (process.env.NODE_ENV !== 'production' && config.performance && mark) {
    ...
  } else {
    // updateComponent为监听函数, new Watcher(vm, updateComponent, noop)
    updateComponent = () => {
      // Vue.prototype._render 渲染函数
      // vm._render() 返回一个VNode
      // 更新dom
      // vm._render()调用render函数，会返回一个VNode，在生成VNode的过程中，会动态计算getter,同时推入到dep里面
      vm._update(vm._render(), hydrating)
    }
  }

  // 新建一个_watcher对象
  // vm实例上挂载的_watcher主要是为了更新DOM
  // vm/expression/cb
  vm._watcher = new Watcher(vm, updateComponent, noop)
  hydrating = false

  // manually mounted instance, call mounted on self
  // mounted is called for render-created child components in its inserted hook
  if (vm.$vnode == null) {
    vm._isMounted = true
    callHook(vm, 'mounted')
  }
  return vm
}
```

### 响应式原理


#### 参考资料

> https://github.com/DDFE/DDFE-blog/issues/18
> https://cn.vuejs.org/v2/api/#mounted
> https://github.com/snabbdom/snabbdom