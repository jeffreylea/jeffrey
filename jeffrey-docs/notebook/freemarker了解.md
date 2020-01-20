# 模板+数据模型=输出 
参考：
http://freemarker.foofun.cn/dgui_template_exp.html#exp_cheatsheet
## 数据模型：
+ 数据模型是树状结构；
+ 标量scalar：存储单值的变量称为标量，单独的数，没有方向，只有数值大小。
+ 哈希表是一种存储变量及其相关且有唯一标识名称的容器；
+ 序列是存储有序变量的容器。
## 模板
+ ${...}：FreeMarker将会输出真实的值替换表达式，这样的表达式被称为插值（interpolation）
+ FTL标签（freemarker template language）：FTL标签和HTML标签有一些相识之处，标签的名字以#开头，FTL标签也被称为指令。
+ 注释:和HTML类似，使用 <#-- and --> 来标识
其他不是FTL标签、注释、插值的内容，被视为静态文本，原样输出。
## 基本指令
+ if 指令
<#if animals.python.price == 0>
  Pythons are free today!
</#if>
+ list 指令
<#list animals as animal>
    <tr><td>${animal.name}<td>${animal.price} Euros
</#list>
