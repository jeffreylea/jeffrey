+ 全局安装docsify

npm i docsify-cli -g

+ 初始化项目

  docsify init

  初始化之后会生成几个文件

  `index.html` 入口文件

  `README.md` 会做为主页内容渲染

  `.nojekyll` 用于阻止 GitHub Pages 会忽略掉下划线开头的文件

直接编辑`README.md`文件，即可更新网站

+ 网站预览

  docsify serve即可启动，访问localhost:3000,即可预览网站

+ 配置项

  **Github Corner**

  通过设置index.html中window.$docsify的 `repo` 参数配置仓库地址或者 `username/repo` 的字符串，会在页面右上角渲染一个 [GitHub Corner](https://links.jianshu.com/go?to=http%3A%2F%2Ftholman.com%2Fgithub-corners%2F) 挂件，点击即可跳转到Github中对应的项目地址。

  ```xml
  <script>
      window.$docsify = {
        name: '豆瓣影音',
        repo: 'https://github.com/Hanxueqing/Douban-Movie.git',
        coverpage: true
      }
    </script>
  ```

+ **封面**

通过设置index.html中window.$docsify的 `coverpage` 参数，即可开启渲染封面的功能。

```xml
<script>
    window.$docsify = {
      name: '豆瓣影音',
      repo: '',
      coverpage: true
    }
  </script>
```

封面的生成同样是从 markdown 文件渲染来的。开启渲染封面功能后在文档根目录创建 `_coverpage.md` 文件，在文档中编写需要展示在封面的内容。

```csharp
![logo](https://docsify.js.org/_media/icon.svg)

# 豆瓣影音

> 使用Vue全家桶+Node.js搭建的小型全栈项目.

* 前端框架：vue-cli、vue-router、axios、vuex
* UI类库：Mint-UI、Vant
* 后端数据接口：Express、MongoDB

[GitHub](https://github.com/Hanxueqing/Douban-Movie.git)
[Get Started](#quick-start)
```

+ 主题

```html
 <link rel="stylesheet" href="//unpkg.com/docsify/themes/vue.css">
  <link rel="stylesheet" href="//unpkg.com/docsify/themes/buble.css">
  <link rel="stylesheet" href="//unpkg.com/docsify/themes/dark.css">
  <link rel="stylesheet" href="//unpkg.com/docsify/themes/pure.css">
  <link rel="stylesheet" href="//unpkg.com/docsify/themes/dolphin.css">
<!-- Theme: Defaults -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/docsify-themeable@0/dist/css/theme-defaults.css">
<!-- Theme: Defaults -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/docsify-themeable@0/dist/css/theme-defaults.css">
<!-- Theme: Simple Dark -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/docsify-themeable@0/dist/css/theme-simple-dark.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/docsify-themeable@0/dist/css/theme-simple.css">
```

+ **多页面**

如果想创建多个页面，即点击左侧侧边栏导航跳转到不同url，就需要配置多级路由，这一功能在docsify中也很容易实现，我们需要在index.html文件中的`window.$docsify`中开启loadSidebar选项：

```xml
<script>
  window.$docsify = {
    loadSidebar: true
  }
</script>
<script src="//unpkg.com/docsify"></script>
```

配置了loadSidebar之后就不会生成默认的侧边栏了。



+ 插件

  有很多插件，具体可以参考官网[](https://docsify.js.org/#/zh-cn/plugins)

  添加搜索插件：

  <script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/search.min.js"></script>

  配置

  ```
  window.$docsify = {
  search: {
          placeholder: '搜索',
          noData: '找不到结果!',
          depth: 3
        },
  }
  ```

  

