**资料列表**  
[python在线手册](http://docs.pythontab.com)
https://docs.python.org/zh-cn/3.6/   
[廖雪峰 Python教程](https://www.liaoxuefeng.com/wiki/1016959663602400/1017032074151456)

**hello world**
print()方法
**python 注释**
单行注释#号标注  
多行注释需要三对引号，单引号双引号都行
**用户输入**
input()

常量：用全部大写名表示常量，例如PI
除法：/计算结果是浮点数 //地板除，结果是整数  %余数运算 
编码：python3中字符串以Unicode编码，因此python的字符串支持多语言。python中提供了ord()函数获取字符的整数表示，chr()函数把编码转化对应的字符。bytes类型的数据用带b的单引号或双引号表示。使用encode()函数把字符串转为bytes类型，decode（）函数把bytes类型转为字符串类型。len函数计算字符的长度。
当python解释器读取源码时，为了让它以utf8的编码读取，通常在文件开头加上两行：  
#！/usr/bin/env python3  
# -*- coding:utf-8 -*-  
第一行告诉系统这是一个python可执行程序，windows会忽略，第二行告诉解释器按照utf8的编码读取，否则可能出现乱码  
**格式化：使用%和format()函数格式化**  
占位符	替换内容
%d	整数
%f	浮点数
%s	字符串
%x	十六进制整数  
**list和tuple**  
list是一个可变的有序表，可以往list中追加元素。apend()方法追加元素，insert()方法插入元素，pop()方法删除最后一个元素，pop(i)删除指定元素，list中的数据类型可以不同;tuple是元组，tuple是不可变的。只有一个元素的tuple在定义是需要加上，号，如：t = (2,)
**循环**  
1.for in 循环
2.while 循环
**dict和set**  
dict相当于java中的map
**python语法采取缩进方式**

生成器：一边循环一边计算的机制
hex函数将数转为16进制;
用pass定义空函数;
python函数返回多值本质是返回一个tuple;
默认参数必须指向不变对象；
*nums表示把nums这个list中的所有元素作为可变参数传进去。
**nums关键字参数，自动组装成dict;
切片符号（：）
迭代：用for in完成迭代
列表生成式：把生成的元素放在循环前面
-----20191112-----
argparse是一个命令行参数解析模块，要运行在命令行窗口中。
------------------
学习使用Jupyter notebook
今天发现了一个后缀是.ipynb的文件，于是就了解了下。查了下它的全称是ipython notebook,又叫Jupyter notebook,它既可以编写/执行代码，又可以像文档一样写文章，很适合教学。
Jupyter notebook是基于网页用于交互计算的应用程序，可被用于全过程计算：开发、文档编写、运行代码和展示结果。是以网页的形式打开，可以在页面中直接编写和运行代码，代码的运行结果直接在代码块下显示。特点：
1.编程时具有语法高亮、缩进、tab补全的功能。
2.可直接通过浏览器运行代码，同时在代码块下方展示运行结果。
3.以富媒体格式展示计算结果。富媒体格式包括：HTML，LaTeX，PNG，SVG等。
4.对代码编写说明文档或语句时，支持Markdown语法。
5.支持使用LaTeX编写数学性说明。
反正就是有点很多，直接安装学习吧。
参考资料：
https://www.jianshu.com/p/91365f343585
使用python3环境，直接运行：
pip install jupyter 等待安装完成。
启动Jupyter
在目录或者将此目录添加到环境变量中D:\Python\Python37\Scripts>下执行
Jupyter notebook。
执行命令之后，在终端中将会显示一系列notebook的服务器信息，同时浏览器将会自动启动Jupyter Notebook。
也可以指定端口启动，默认端口是8888，指定端口启动命令：
jupyter notebook --port 9999
启动服务器，不打开浏览器的命令：
jupyter notebook --no-browser
获取配置文件所在路径的命令：
jupyter notebook --generate-config
将配置文件中的一行注释取消，并设置文件存放位置。
c.NotebookApp.notebook_dir = 'F:\Jupyter'
可以看到一个清爽的界面了。默认的目录显示了很多安装目下下的文件。
确实是一个网页版的编辑器，可以在上面新建文件，直接运行python代码，写MD文档，值得尝试一下。
学习编写第一个人工智能教程，参考资料
https://blog.csdn.net/jiangjunshow/article/details/81704868
matplotlib是python的绘图库，可以和numpy一起使用，提供了一种有效的MATLAB开源替代方案。也可以和图形工具包一起使用，pyQt和wxPython。
安装：