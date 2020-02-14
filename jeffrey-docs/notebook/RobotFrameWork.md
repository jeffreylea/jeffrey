学习robotFramework  
资料  
https://www.jianshu.com/p/c3a9d20db4e5
https://www.cnblogs.com/leozhanggg/p/9643180.html
https://www.cnblogs.com/yigui/p/8687109.html
https://www.jianshu.com/p/0afa59b25ab1
https://blog.csdn.net/a5650892/article/details/78075168
http://www.voidcn.com/article/p-wftgnxif-bdn.html
安装pip工具  
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py   # 下载安装脚本
python get-pip.py    # 运行安装脚本
pip和setuptools的安装  
python2.7.9以上是自带pip的，但如果没有pip可以手动安装。  
https://pypi.python.org/pypi/setuptools 
https://pypi.python.org/pypi/pip
安装 robotframework  
pip install robotframework
中间出现错误，提示没有对应的robotframework，因为我安装的python版本是python2.7.2，没有自带的pip，可能由于版本不对。后来换成python2.7.19版本才安装成功。

1.安装python2.7.19  
https://www.python.org/downloads/windows/
2.安装robotFramework  
pip install robotframework
查看版本信息 robot --version
最好设置一下系统变量，避免找不到命令，如果不设置，可以直接到对应目录下执行命令。
命令位置:D:\Python\Python27\Scripts
3.安装robotframework-selenium2library  
pip install  robotframework-selenium2library

下载Chrome驱动，
4. 打开ride，运行ride.py,弹出图形界面
5.简单运行案例  
https://blog.csdn.net/weixin_34088838/article/details/93893078

6.log关键字 在 robot Framework 中通过“Set variable”关键字来定义变量

+ Robot Framework Run运行界面，没有打印出日志  
用例执行完，run界面执行完，日志没有打印东西，report和log也不能点击，这是因为浏览器驱动没有关闭导致
https://www.jianshu.com/p/0b9207d2c882

+ 自动化测试工具  
https://blog.csdn.net/qq_36396763/article/details/97944411

+ 标准库是Robot Framework可以直接导入使用的库，包含以下几类：  
https://www.cnblogs.com/yrxns/p/6676645.html
Screenshot、Remote、Collections

https://www.cnblogs.com/pachongshangdexuebi/p/7112347.html
# 定位元素  
在使用与web交互的关键字时，通常需要一个locator参数，改参数是用来定位元素使用的。
+ 定位器的语法  
Selenium2Library支持根据不同的策略查找元素，比如元素id、XPath表达式或CSS选择器。可以使用前缀显式地指定策略，也可以隐式地指定策略.  
+ 默认定位策略
+ Selenium2Library 中的定位器locator
	+ 使用id和name定位  
	+ 使用xpath定位  
	示例：xpath = //*[@id="app"]/div/div[3]/div/svg，一个元素的xpath简单的寻找方法是在浏览器中的调试模式，找到该元素，然后右键复制xpath
+ 明确定位策略  
下表列出了默认支持的定位器策略。此外，还可以注册自定义定位器
|Strategy	|Match based on	|Example |
id	Element id.	id:example
name	name attribute.	name:example
identifier	Either id or name.	identifier:example
class	Element class.	class:example
tag	Tag name.	tag:div
xpath	XPath expression.	xpath://div[@id="example"]
css	CSS selector.	css:div#example
dom	DOM expression.	dom:document.images[5]
link	Exact text a link has.	link:The example
partial link	Partial link text.	partial link:he ex
sizzle	Sizzle selector provided by jQuery.	sizzle:div.example
jquery	Same as the above.	jquery:div.example
default	Keyword specific default behavior.	default:example	

# 超时、等待和延迟
+ 超时 
Selenium2Library包含各种关键字，这些关键字有一个可选的超时参数，该参数指定这些关键字应该等待某些事件或操作多长时间。这些关键词包括，例如，等待…关键字和与警报相关的关键字

+ Comment关键字
用来注释
+ Should Be Equal As Strings 关键字
 将对象转化成string比较，如果不相等，则失败
#  接口自动化测试  
1. 环境安装
pip install requests
pip install robotframework-requests	 可以正常导入RequestsLibrary
同时需要导出Collections
get请求：
step1:使用关键字 Create Session创建一个连接到服务器的host
step2:使用get request关键字请求
post请求：
如果传参数类型为Content-Type=application/x-www-form-urlencoded，
1.首先设置Content-Type=application/x-www-form-urlencoded传参类型。
2.创建session，参数设置为第一步设置的参数
3.使用post request关键字请求
接口测试返回值解析：  
从D:\Python\Python27\Lib\site-packages\requests\models.py文件中，可以找到Response类。经常用到的几个属性：
频繁用到的：
content: 响应body的内容，二进制编码，如果返回只有文本内容，和text差不多
cookies：响应回写的cookies，cookieJar类对象
headers: 响应头内容
json(): 响应body内容，json格式
status_code: 状态码
text: 响应body的内容，默认unicode编码

+ Robot Framework 接口自动化测试传token。  
header中Authorization=Bearer ${token},“=”不要有空格，注意Bearer第一个字母是大写，我在小写的时候没有检测出来传token。
+ 省略号（...）拆分多行
如果测试数据太长，可以使用这个符号拆分多行
+ Set To Dictionary 关键字
把给定的key-value或者字典项值添加到字典dict当中
+  Set Variable 关键字
给变量设置值
+ pybot和rebot命令
rebot用于合并多个报告，pybot用于执行robotframe脚本
Windows安装完之后robotFramework之后，有pybot，ride也可以正常使用，但没有pybot命令。解决办法：在Scripts目录下加一个pybot.bat 文件 内容为
@Echo off
python -m robot.run %*
+ Robot frame 基本运算符
https://blog.csdn.net/banrieen/article/details/80429319
+ robotFramework 因滚动条导致元素不可见
首先使用Scroll Element Into View关键字将元素滚动到视图当中，再进行点击。
+ 数据库操作  
需要用到databaselibrary，同时需要安装pymysql模块，安装方式：
 pip install -U robotframework-databaselibrary
 pip install pymysql
 使用关键字 Connect To Database Using Custom Params 连接数据库
 query关键字查询数据库
 
+ 变量与常量  
Scalar型变量用$作为标志符； List型变量用@作为标志符。
和python一样，变量不需要特定声明，只要有初始化赋值就行。每个变量默认都是局部变量。变量的作用阈可以改变，使用set global variable 设定全局变量；使用set suite variable 设定suite级别变量。  
除变量以外，还有些常量，主要有环境变量，数值常量，特殊字符常量，系统保留常量。其中环境变量的标志符是%，
 RF内置库学习：
 资料：
 https://robotframework.org/robotframework/
 + BuiltIn测试库  
 这个库不需要手动添加，ride默认加载。这个测试库主要有以下几类关键字。  
 1.Convert  
Convert To Integer：将给定的项转换成整数
Convert To Number：将给定的值转换成浮点数
Convert To Binary：将给定的值转换成二进制
Convert To Octal：将给定的值转换成8进制
Convert To Hex：将给定的值转换成16进制
Convert To String：将给定的值转换成Unicode字符串
Convert To Bytes：根据input_type将给定的输入转换成字节
Convert To Boolean：将给定的项转换成Boolean值true或者false
2.verify  
Fail :使用给定的消息测试失败，停止测试当前案例。  
Fatal Error： 使用给定的消息测试失败，停止所有测试，包括后面没有执行的测试案例。
Should Not Be Empty： 验证给定项不为空
Should Be Empty：验证给定项是空。  
Should Not Be Equal：验证是否相等，相等会失败。  
Should Not Be Equal As Integers：将对象转换为整数后，如果相等，则失败。  
Should Not Be Equal As Numbers：将对象转换成数字后相等，则失败。  
Should Not Be Equal As Strings：将对象转换成字符串后相等，则失败。  
Should Not Be True：如果给定的条件是true，则失败
Should Start With：如果字符串1没有以字符串2开头，则失败  
Should Match：如果给定的字符串与给定的模式不匹配，则失败  
Should Match Regexp：如果给定的字符串与正则表达式不匹配，则失败  
Get Count：获取第二个参数在第一个参数出现的次数  
Get Length：获取给定的项的长度  
Length Should Be： 验证给定的项的长度是否正确
3.Variables 
变量是有作用阈的，所以这边有set test，suite，global variable来分别给不同的作用阈设置变量。
Get Variables：获取当前作用阈内所有变量  
Set Variable: 返回可以赋给变量的值  
Set Variable If：根据给定条件设置变量  
Log Variables：打印所有变量  
Variable Should Exist： 当前范围内存在给定的变量  
Replace Variables：用当前值替换给定文本中的变量
4.RunKeyword  
Run Keyword:执行给定的关键字以及关键字的参数  
Run Keywords：按顺序执行所有的关键字  
Wait Until Keyword Succeeds: 运行指定的关键字，如果失败则重试
Repeat Keyword:多次执行指定的关键字
Run Keyword And Return Status:使用给定的参数运行给定的关键字，并以布尔值的形式返回状态
5.Control  
与流程相关的关键字