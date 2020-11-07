### npm使用介绍

- 允许用户从NPM服务器下载别人编写的第三方包到本地使用。
- 允许用户从NPM服务器下载并安装别人编写的命令行程序到本地使用。
- 允许用户将自己编写的包或命令行程序上传到NPM服务器供别人使用

```
npm -v 查看npm版本

npm install npm -g npm升级

由于npm安装比较慢，可以使用淘宝镜像，及其cnpm命令：
npm install -g cnpm --registry=https://registry.npm.taobao.org

使用npm安装模块：
npm install <Module Name>
```

`v-bind`指令：绑定元素属性指令
```
<!-- 完整语法 -->
<a v-bind:href="url">...</a>

<!-- 缩写 -->
<a :href="url">...</a>
```

<!-- 完整语法 --> <a v-bind:href="url">...</a> <!-- 缩写 --> <a :href="url">...</a>

`v-if` 指令：

`v-for`指令：

`v-on`指令：

```
<!-- 完整语法 -->
<a v-on:click="doSomething">...</a>

<!-- 缩写 -->
<a @click="doSomething">...</a>
```



`v-model` 指令:实现表单输入和应用状态的双向绑定



除了数据属性，Vue实例还暴露了一些有用的实例属性与方法。它们都有前缀 `$`，以便与用户定义的 属性区分开来

