查看系统内核：  
uname -r
默认版本是2.6,网上建议docker在内核3.1以上版本运行。在2.6下直接用yum install docker-io安装是不行的，提示没有包，于是：
直接用下载源安装：  
yum install https://get.docker.com/rpm/1.7.1/centos-6/RPMS/x86_64/docker-engine-1.7.1-1.el6.x86_64.rpm
查看docker启动状态：
service docker status
启动docker：
service docker start，再次查看状态提示：docker dead but pid file exists
说是因为device-mapper-libs版本过低导致的，于是进行升级：
yum update -y device-mapper-libs
很顺利，执行成功。再次启动docker并查看docker运行状态。docker此时已经在running中。
使用docker安装mysql：
查找mysql镜像：
docker search mysql
使用命令docker pull docker.io/mysql或者docker pull  mysql安装提示Could not reach any registry endpoint。失败原因大概是服务器没有成功访问到docker仓库，应该是docker版本太低了。
查看docker版本：
 docker --version
Docker version 1.7.1, build 786b29d
查资料的过程中说是要创建一个自己的镜像仓库，以前知道JFrog Artifactory是管理镜像仓库的工具。docker默认的是docker hub 镜像管理工具。

-----
3. 容器常用的管理命令
    docker run 创建并启动一个容器，在run后面加上-d参数，则会创建一个守护式容器在后台运行。
    docker ps -a 查看已经创建的容器
    docker ps -s 查看已经启动的容器
    docker start con_name 启动容器名为con_name的容器
    docker stop con_name 停止容器名为con_name的容器
    docker rm con_name 删除容器名为con_name的容器
    docker rename old_name new_name 重命名一个容器
    docker attach con_name 将终端附着到正在运行的容器名为con_name的容器的终端上面去，前提是创建该容器时指定了相应的sh
执行这个命令后，按下回车键，会进入容器的命令行Shell中。
    docker logs con_name 获取容器名为con_name的容器日志
    docker inspect 查看容器的详细信息
    docker top con_name 查看容器名为con_name的容器内部的进程
    docker exec 可以用来在容器中运行一个进程

创建

1.1 docker基本信息查看

（1）docker version：查看docker的版本号，包括客户端、服务端、依赖的Go等 ；

 

（2）docker info ：查看系统(docker)层面信息，包括管理的images, containers数等；

 

1.2 docker镜像的获取与删除

(1) docker pull centos ：下载centos所有的镜像

 

(2)docker pull centos:centos6  下载centos6镜像

 

(3)docker images  查看本机所有的镜像包

 (4)docker images -a  列出所有的images（包含历史）

（5）构建镜像

docker build -f Dockerfile -t jeffrey/platform-feign-1.2.0.jar:1.2.0 .

2，容器的创建
#docker run -it  centos:centso6 test      创建容器
#docker run -it -p 8001:80 --name nginx01 -h nginx ng1 /bin/bash   创建并指定端口号映射

docker run -P 8180:8180 jeffrey/platform-feign-1.2.0.jar

#docker ps -a                   列出已经创建的容器

删除docker镜像：
docker rmi imagesid

停止容器：
docker stop imagesid

修改容器中语言：
export LANG=zh_CN.UTF-8

#docker exec -it  id  /bin/bash               我们还可以通过指定参数，启动一个bash交互终端
----



#  K8s使用

### 进入pod查看mysql最大连接数

进入Pod内部执行相关操作: 

 kubectl exec -it pod-name /bin/sh  
登陆mysql：mysql -uroot -pXXXXX
执行：show variables like "%max_connections%";
命令行修改最大连接数为1000： set global max_connections = 1000;

### namespace操作

查询namespace： kubectl get namespace

创建namespace： kubectl create namespace jeffrey

### 查看当前pod的yaml配置文件

kubectl get pods -n jeffrey -o yaml

### 查看一个或多个资源的详细信息

+ 查看pod详细信息 

  kubectl describe pod podname

### k8s中**imagePullSecrets**是什么？怎么使用？

imagePull



centos7下安装docker：

yum install docker

启动：

systemctl start docker

出现错误：

Job for docker.service failed because the control process exited with error code. See "systemctl status docker.service" and "journalctl -xe" for details.

解决：

查看docker版本：docker version 只有client，没有server

执行 vi /etc/sysconfig/selinux , 把 selinux 属性值改为disabled。然后重启系统。

```
[root@serverA docker]# vi /etc/sysconfig/selinux
[root@serverA docker]# reboot
重新启动后启动docker：
[root@serverA ~]# systemctl start docker
[root@serverA ~]# docker images

```

k8s的endpoint

endpoint是k8s集群中的一个资源对象，存储在etcd中，用来记录一个service对应的所有pod的访问地址，

etcd是一个高可用的分布式键值数据库。service配置selector，endpoint controller才会自动创建对应的endpoint对象；否则，不会生成endpoint对象.ENDPOINTS就是service关联的pod的ip地址和端口，一个service有一个或多个实例。



