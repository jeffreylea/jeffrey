### CentOS7安装Minio文件服务器

下载：

wget https://dl.minio.io/server/minio/release/linux-amd64/minio

创建minio数据目录：

[root@serverA ~]# mkdir /opt/data/minio

启动：

![image-20201016082447768](D:\JeffreyLearn\jeffrey\jeffrey-docs\image\media\CentOS7安装Minio文件服务器\image-20201016082447768.png)

防火墙配置：

命令方式添加端口：

+ firewall-cmd --zone=public --permanent --add-port=9000/tcp

```
1、firwall-cmd：是Linux提供的操作firewall的一个工具；
2、--permanent：表示设置为持久；
3、--add-port：标识添加的端口
```

+ firewall-cmd --list-all

//查看防火墙规则，可查到你当前开放的端口信息

```
常用命令介绍
firewall-cmd --state ##查看防火墙状态，是否是running
firewall-cmd --reload ##重新载入配置，比如添加规则之后，需要执行此命令
firewall-cmd --get-zones ##列出支持的zone
firewall-cmd --get-services ##列出支持的服务，在列表中的服务是放行的
firewall-cmd --query-service ftp ##查看ftp服务是否支持，返回yes或者no
firewall-cmd --add-service=ftp ##临时开放ftp服务
firewall-cmd --add-service=ftp --permanent ##永久开放ftp服务
firewall-cmd --remove-service=ftp --permanent ##永久移除ftp服务
firewall-cmd --add-port= 80/tcp --permanent ##永久添加80端口
iptables -L -n ##查看规则，这个命令是和iptables的相同的
man firewall-cmd ##查看帮助
```

访问：

http://192.168.95.12:9000 访问，默认用户名密码为：minioadmin/minioadmin



参考资料

minio快速指导: [官方文档](https://docs.min.io/cn/minio-quickstart-guide.html)

[Minio Cookbook 中文版](https://www.bookstack.cn/books/MinioCookbookZH)