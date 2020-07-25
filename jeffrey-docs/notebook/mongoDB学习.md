centOS6.5下yum安装MongoDB记录
-----------
1、创建repo  
`vi /etc/yum.repos.d/mongodb-org-3.2.repo`
```
[mongodb-org-3.2]  
name=MongoDB Repository  
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/3.2/x86_64/  
gpgcheck=0  
enabled=1 
```

2、安装MongoDB和相关工具  
`sudo yum install -y mongodb-org`

3、查看mongoDB启动状态;启动、停止、重启  
```
service mongod status
service mongod stop
service mongod start
service mongod restart
```
4、进入MongoDB命令窗口：   
```
[root@master ~]# mongo //进入MongoDB
> show dbs //显示所有数据的列表
> db //显示当前数据库对象或集合
> use local//连接到指定local数据库
> use runboot创建数据库runboot，如果已经存在则切换到runboot
> db.runoob.insert({"name":"jeffrey"})//向 runoob数据库插入数据
>  db.dropDatabase()//删除当前数据库
db.createCollection("tables1") // 创建集合tables1
> show tables 查看集合
> db.tables1.drop() 删除集合table1

```
MongoDB概念解析：
|sql概念|MongoDB概念|解释|
| :------: | :----------: | :----: |
|database|database|数据库|
|table|collection|数据库表/集合|
|row|document|数据记录行/文档|
|column|field|数据字段/域|
|index|index|索引|
|table joins||表连接,MongoDB不支持|
|primary key|primary key|主键,MongoDB自动将_id字段设置为主键|

远程连接mongoDB连接时27017端口连接不上
-------------------------
1、确认防火墙关闭
`service iptables status`
2.修改mongo的配置文件
vim /etc/mongod.conf 
将 bindIp: 127.0.0.1 修改为0.0.0.0，service mongod restart重启数据库
即解决远程连不上的问题。

使用NoSQLBooster for MongoDB工具连接mongoDN数据库
-----------
下载地址：
https://nosqlbooster.com/downloads

