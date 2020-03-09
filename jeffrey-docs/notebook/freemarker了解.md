# 什么是freemarker？

freemarker是一种模板引擎（为了界面和业务数据分离），即一种基于模板和要改变的数据，并用来生成输出文本（HTML网页、电子邮件、配置文件、源代码等）的通用工具。它不是面向最终用户的，而是一个java类库，是一款程序员可以嵌入他们所开发产品的组件。  
![freemarker原理](/jeffrey-docs/image/freemarker-01.png)

# springboot中freemarker的简单使用
创建一个springboot项目，依赖Spring Web、apache freemarker。
创建完之后看pom文件是否有freemarker的依赖，没有要添加上。pom文件依赖如下：

```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
我在生成项目的时，第一行报错，这里报错一般不影响程序运行：
```
<?xml version="1.0" encoding="UTF-8"?>
```
我选择的springboot 的版本是2.2.5.RELEASE，修改成2.0.5.RELEASE之后就解决了，可能是IDE不支持高版本的吧。
这里创建TestController.java文件和index.ftl页面

```
TestController.java:
@Controller
public class TestController {
     
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("welcome","hello world");
        return "index";
    }
}
index.ftl:
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
${welcome}
</body>
</html>
```
在编写ftl页面时，发现eclipse中并没有像编写jsp页面时有颜色，这里可以设置下使用jsp编辑方式打开*.ftl文件，设置方式：Window → preferences → Content Types → Text → JSP，点击下方的 File associaions 右侧的 Add... 按钮，输入 *.ftl 添加。
这里了解下@Controller和@RestController注解的区别，@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用，如果只是使用@RestController注解，则controller中的方法无法返回ftl页面。如果需要返回到指定页面，则需要用 @Controller配合视图解析器FreeMarkerViewResolver才行。如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
启动项目之后，发送请求：http://localhost:8080/index，页面会显示hello world，这就是freemarker的简单使用。

# 模板+数据模型=输出 
参考：
[Freemarker在线文档](http://freemarker.foofun.cn/dgui_template_exp.html#exp_cheatsheet)
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
