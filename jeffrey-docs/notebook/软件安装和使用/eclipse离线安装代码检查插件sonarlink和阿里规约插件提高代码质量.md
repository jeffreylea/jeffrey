
sonarlink是eclipse的代码质量检查工具插件，在写代码的时候会给出提示。java程序员应该知道阿里规约吧，我们在写代码的时候应该遵循规约来提高我们的代码质量。阿里规约不仅只有文档，还有配合IDE使用的插件。在线安装方式很简单，在网上搜到的大部分也都是在线安装的，在这里我只记录下离线安装，因为有时候网络原因，在线安装并不是那么简单。
# 离线安装sonarlink
参考：https://blog.csdn.net/luckystar689/article/details/70182477
下载： https://bintray.com/sonarsource/SonarLint-for-Eclipse/releases

下载之后解压，将featrues和plugins文件夹复制到eclipse下的dropins文件夹下。重启eclipse，在window->show views->others中可以看到SonarLint，就说明安装成功了。
使用：
右键所要检查的文件或项目，点击
在sonarlint中，你会看到五个视图。
1、sonarqube servers：
连接sonarqube服务，连接上sonarqube之后就可以使用服务的所有配置。
2、sonarlint report: 显示所有的代码不规范事项列表。
3、sonarqube rule description ：需要结合Sonarqube report和 sonarlint on-the-fly一起使用，点击不规范事项，会在这里显示问题的描述以及正确代码应该如何编写，和在sonarqube上看到的一样。
4、sonarlint on-the-fly：即时反馈，显示当前打开文件的不规范描述

5、sonarlint Issue locations ：显示问题的位置
插件下载：
链接：https://pan.baidu.com/s/1-sgiN7MGv_PsWYOQIJMG-g 
提取码：m7s5 

# eclipse离线安装阿里规约
下载插件，直接将jar包复制到plugins文件夹下，重启eclipse。使用时点击需要检查的文件，右键选择阿里规则即可。
链接：https://pan.baidu.com/s/1T60e3W1EAyMizNi4fqocMQ 
提取码：rxsy 