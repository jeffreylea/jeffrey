### CentOS7 安装ELK

---
+ **yum安装elasticsearch**

yum 源配置([免费yum源地址](免费yum源镜像地址.md))：

```

vim /etc/yum.repos.d/elasticsearch.repo

[elasticsearch-6.0.0]
name=Elasticsearch repository for 6.0.0 packages
baseurl=https://mirrors.tuna.tsinghua.edu.cn/elasticstack/yum/elastic-6.x
gpgcheck=0
enabled=1


安装：yum install elasticsearch
启动：service elasticsearch start 
测试：curl -X GET localhost:9200

默认只能本机通过localhost或者127.0.0.1访问，不能通过本机IP访问。

打开elasticsearch配置文件：

vim /etc/elasticsearch/elasticsearch.yml

network.host，把后面改为0.0.0.0

修改后重启：service elasticsearch restart

查看并关闭防火墙状态：

1. CentOS 7使用systemctl 来运行命令，而CentOS 6是用service来运行命令的。

2. CentOS 6使用的是 iptables。CentOS 7 使用的是firewalld，而不在使用iptables

临时关闭防火墙：systemctl stop firewalld

禁止开机启动防火墙：systemctl disable firewalld

http://192.168.95.12:9200/ 可以通过查看es信息


注意：防火墙关闭后，当能够ping通地址，而telnet端口不通时，需要检查下服务端口的可访问IP地址。

elasticsearch默认只能本机localhost、127.0.0.1访问，在配置中需要将 network.host: 0.0.0.0注释打开并修改。
```

+ **yum安装kibana：**

安装：yum install kibana

配置：

```
vim /etc/kibana/kibana.yml

elasticsearch.hosts: ["http://localhost:9200"] // 绑定es

server.host: "0.0.0.0" //默认只能本机localhost访问

server.port: 5601 //默认端口是5601
```

启动kibana：service kibana start

访问：http://192.168.95.12:5601/

出现 Kibana server is not ready yet

可能是因为kibana启动时需要初始化一些东西，还没有准备好，等过两个小时后，再次访问发现可以访问页面了。我第一次在CentOs6.5中安装ES2.x版本时，当时安装的是kibana4.x版本，反正是两者版本不对应吧，也出现了Kibana server is not ready yet，查网上原因说是版本不对，于是就换版本安装，折腾了很长时间也没有在CentOS6.5中安装成功。当这次出现`Kibana server is not ready yet` 时我非常疑惑和崩溃，已经因为版本一致的原因折腾了很久，现在版本一致了，还是出现这个问题，于是打算吃完饭回来再弄，回来发现可以访问了，现在不确定之前是不是因为版本的原因导致的。

+ **yum安装logstash**：

安装：yum install logstash

启动：systemctl start logstash.service
报错：Failed to start logstash.service: Unit not found

这可能是在安装时内存不足，没有生成logstash.service

 生成logstash.service: `sudo /usr/share/logstash/bin/system-install /etc/logstash/startup.options systemd`

还是报错：

![image-20201007160203758](D:\JeffreyLearn\jeffrey\jeffrey-docs\image\media\ES安装笔记\image-20201007160203758.png)

调整参数：

```
vim /etc/logstash/jvm.options
由1g调整为：
-Xms256m
-Xmx256m
```

再次执行生成logstash.service: `sudo /usr/share/logstash/bin/system-install /etc/logstash/startup.options systemd`

启动：systemctl restart logstash

发现并没有提示，从配置文件找到日志文件输出位置：

/var/log/logstash

查看日志发现在不断重启，错误提示为：No config files found in path {:path=>"/etc/logstash/conf.d/*.conf"}

发现该目录下是空的。



测试标准输入输出：./logstash -e 'input { stdin { } } output { stdout {} }'

电脑性能不好，运行较慢，差点认为又不行，

![image-20201008103458106](D:\JeffreyLearn\jeffrey\jeffrey-docs\image\media\ES安装笔记\image-20201008103458106.png)

测试输出到文件：

./logstash -e 'input {stdin {}} output {file { path => "/tmp/test-%{+YYYY.MM.dd}.log"}}'

测试输出到elasticsearch：

./logstash -e  'input {stdin {}} output {elasticsearch { hosts => ["192.168.95.12:9200"] index => "logstash-test-%{+YYYY.MM.dd}"}}'

**logstash收集日志到elasticsearch**

```
# cat /etc/logstash/conf.d/logstash_to_es_systemlog.conf
 
input {
    file {
        path => "/var/log/messages"
        type => "systemlog"
        start_position => "beginning"
        stat_interval => "2"
    }
}
 
output {
    elasticsearch {
        hosts => ["192.168.95.12:9200"]
        index => "logstash-systemlog-%{+YYYY.MM.dd}"
    }
}
```



测试配置文件：

```
1：./logstash --path.settings /etc/logstash/  -f /etc/logstash/conf.d/logstash-sample.conf --config.test_and_exit
2：/usr/share/logstash/bin/logstash -t -f /etc/logstash/conf.d/logstash_to_es_systemlog.conf  #测试配置文件

Configuration OK
/usr/share/logstash/bin/logstash -f /etc/logstash/conf.d/logstash_to_es_systemlog.conf  #启动logstash
```



指定配置文件启动：

./logstash --path.settings /etc/logstash/  -f /etc/logstash/conf.d/logstash-test.conf





---

以下安装环境是centos6.5,没有安装成功，安装过程中遇到了很多问题

**Es的yum安装配置**

ES不在yum本地源，需要添加ES的yum配置。

**配置安装ES的yum公钥**

**#rpm --import https://artifacts.elastic.co/GPG-KEY-elasticsearch

**配置ES yum源**

**vim /etc/yum.repos.d/elasticsearch.repo**

 	

```
[elasticsearch-2.x]
name=Elasticsearch repository for 2.x packages
baseurl=https://mirrors.tuna.tsinghua.edu.cn/ELK/yum/elasticsearch-2.x/
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md
```

```
注：这是yum安装ES2.x版本
rpm --import https://packages.elastic.co/GPG-KEY-elasticsearch

vim /etc/yum.repos.d/elasticsearch.repo

[elasticsearch-2.x]
name=Elasticsearch repository for 2.x packages
baseurl=http://packages.elastic.co/elasticsearch/2.x/centos
gpgcheck=1
gpgkey=http://packages.elastic.co/GPG-KEY-elasticsearch
enabled=1

```



jvm.options配置文件，可以修改es所使用的JVM内存大小

vi /etc/security/limits.conf

```
* hard nofile 655360
* soft nofile 131072
* hard nproc 4096
* soft nproc 2048
# End of file

```







**二、yum安装ES**

**1、更新yum的缓存**

**# yum makecache**

 

**2、安装ES**

**# yum install elasticsearch**



**三、测试ES**

**2、运行测试**

启动：service elasticsearch start

**# curl -X GET localhost:9200**

四、设置通过网络IP访问

默认只能本机通过localhost或者127.0.0.1访问，不能通过本机IP访问。

打开elasticsearch配置文件：

vim /etc/elasticsearch/elasticsearch.yml

network.host，把后面改为0.0.0.0

修改后重启：service elasticsearch restart

http://192.168.95.10:9200/ 可以通过查看es信息



注意：防火墙关闭后，当能够ping通地址，而telnet端口不通时，需要检查下服务端口的可访问IP地址。

elasticsearch默认只能本机localhost、127.0.0.1访问，在配置中需要将 network.host: 0.0.0.0注释打开并修改。



注意：在启动时，提示glib版本过低，于是升级glib，方法如下：

```
//各个版本的glibc可以从http://ftp.gnu.org/gnu/glibc/找，包括其插件glibc-port，最新到2.20，我保守的选择2.15 
//对于低版本glibc，还有glibc-linuxthreads-2.x需要编译，可参考很多网上文档，但2.15没有，所以不用了
# wget http://ftp.gnu.org/gnu/glibc/glibc-2.15.tar.gz
# wget http://ftp.gnu.org/gnu/glibc/glibc-ports-2.15.tar.gz
# tar -xvf  glibc-2.15.tar.gz
# tar -xvf  glibc-ports-2.15.tar.gz
# mv glibc-ports-2.15 glibc-2.15/ports
# mkdir glibc-build-2.15 
# cd glibc-build-2.15
# ../glibc-2.15/configure  --prefix=/usr --disable-profile --enable-add-ons --with-headers=/usr/include --with-binutils=/usr/bin
# make
# make install
//如果提示install成功，去看glibc所在的共享库：
# ll /lib64/libc*
//可以看到2.12的旧库文件还在，多了2.15版本的库文件，而且软链接文件全部指向了2.15版本。


```





# yum 安装kibana

**配置yum源**

*# 下载并导入公开签署密钥*

 rpm --import https://artifacts.elastic.co/GPG-KEY-elasticsearch

vim /etc/yum.repos.d/kibana.repo

```
[elasticsearch-6.x]
name=Elasticsearch repository for 6.x packages
baseurl=https://artifacts.elastic.co/packages/6.x/yum
gpgcheck=1
gpgkey=https://artifacts.elastic.co/GPG-KEY-elasticsearch
enabled=1
autorefresh=1
type=rpm-md
```

配置好后，yum install kibana安装

```
vim /etc/kibana/kibana.yml

elasticsearch.hosts: ["http://localhost:9200"] // 绑定es

server.host: "0.0.0.0" //默认只能本机localhost访问

server.port: 5601 //默认端口是5601

```





```
docker pull kibana:6.4.0
docker run -d --name kibana_es --net somenetwork -e ELASTICSEARCH_URL=http://192.168.16.241:9200 -p 5601:5601 kibana:6.4.0

拷贝docker里myES这个容器里面的/usr/share/elasticsearch/config/elasticsearch.yml到宿主机/root/myfile/elasticsearch.yml:
docker cp myES:/usr/share/elasticsearch/config/elasticsearch.yml  /root/myfile/elasticsearch.yml

docker run -d --name myES -p 9200:9200 -p 9300:9300 -v /root/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml docker.io/elasticsearch:7.4.0
```



----------------------------------------------

docker安装elasticsearch

```
docker pull elasticsearch:6.4.0
docker run -p 9200:9200 elasticsearch:6.4.0
#报错1：requires kernel 3.5+ with CONFIG_SECCOMP and CONFIG_SEC

#报错2：max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
```

![image-20201007093004520](D:\JeffreyLearn\jeffrey\jeffrey-docs\image\media\ES安装笔记\image-20201007093004520.png)

报错1：这是由于Linux版本过低导致，可以选择升级版本或者忽略。

报错2：设置系统最大打开的文件数

```
vim /etc/security/ulimit.conf
在末尾添加：
* hard nofile 65536
* soft nofile 65536

```

发现还是启动不了，想着这是在容器中启动，容器应该也对线程数有限制。

```
Docker容器中关于用户最大线程数限制问题:
发现centos 6以后真正限制max user processes的有2个地方一个是/etc/security/limits.conf,还有一个地方是/etc/security/limits.d/90-nproc.conf,如果90-nproc.conf这个不存在，那么/etc/security/limits.conf这个生效，但是如果90-nproc.conf这个存在，那么这个文件真正生效。
于是把90-nproc.conf文件中的1024修改为65536
```

还是启动不了

还是换系统吧，换成了centos7安装时就比较顺利了。



# 一提到大数据就想到Hadoop，那么ELK呢？







hadoop生态圈：

![img](https://img2018.cnblogs.com/blog/1219922/201905/1219922-20190510232151425-1083708783.jpg)



![img](https://img2018.cnblogs.com/blog/1219922/201905/1219922-20190510232151954-340553575.png)



![img](https://img2018.cnblogs.com/blog/1219922/201905/1219922-20190510232152268-533129475.png)



![img](https://img2018.cnblogs.com/blog/1219922/201905/1219922-20190510232152657-550887091.png)

