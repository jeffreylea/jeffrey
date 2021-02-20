### vmware安装centos7

```
点击新建虚拟机->选择推荐的典型安装->点击下一步->选择稍后安装操作系统->选择客户机操作系统和版本->点击下一步->选择虚拟机名称和虚拟机文件位置->点击下一步->指定磁盘容量，这里默认20G，选择将虚拟磁盘拆分成多个文件（选择两者都可以，VMware有自带工具可以将两者转化，如果计算机文件格式为NTFS可以选择单个文件）

参考：
https://www.jianshu.com/p/f4ac7f4555d3
```

### CentOS7下安装docker-ce-17.12.0.ce

```
# Docker 要求 CentOS 系统的内核版本高于 3.10 ，查看本页面的前提条件来验证你的CentOS 版本是否支持 Docker 。
#通过 uname -r 命令查看你当前的内核版本
uname -r

#更新yum，确保 yum 包更新到最新
yum update

#安装wget,为了下载阿里云yum源
yum install wget

#设置阿里云yum源(可以先把CentOS-Base.repo备份成CentOS-Base.repo.bak,会覆盖此文件)
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo

#也可以新建/etc/yum.repos.d/CentOS-Base.repo，内容如下：
# CentOS-Base.repo
#
# The mirror system uses the connecting IP address of the client and the
# update status of each mirror to pick mirrors that are updated to and
# geographically close to the client.  You should use this for CentOS updates
# unless you are manually picking other mirrors.
#
# If the mirrorlist= does not work for you, as a fall back you can try the 
# remarked out baseurl= line instead.
#
#
 
[base]
name=CentOS-$releasever - Base - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/os/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/os/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/os/$basearch/
gpgcheck=1
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
 
#released updates 
[updates]
name=CentOS-$releasever - Updates - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/updates/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/updates/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/updates/$basearch/
gpgcheck=1
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
 
#additional packages that may be useful
[extras]
name=CentOS-$releasever - Extras - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/extras/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/extras/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/extras/$basearch/
gpgcheck=1
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
 
#additional packages that extend functionality of existing packages
[centosplus]
name=CentOS-$releasever - Plus - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/centosplus/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/centosplus/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/centosplus/$basearch/
gpgcheck=1
enabled=0
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
 
#contrib - packages by Centos Users
[contrib]
name=CentOS-$releasever - Contrib - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/contrib/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/contrib/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/contrib/$basearch/
gpgcheck=1
enabled=0
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7


#安装需要的软件包， yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的
yum install -y yum-utils device-mapper-persistent-data lvm2
#设置docker-ce安装yum源
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

#也可以新建/etc/yum.repos.d/docker-ce.repo文件,内容如下:（未验证）
[docker-ce-stable]
name=Docker CE Stable - $basearch
baseurl=https://mirrors.aliyun.com/docker-ce/linux/centos/7/$basearch/stable
enabled=1
gpgcheck=1
gpgkey=https://mirrors.aliyun.com/docker-ce/linux/centos/gpg

#查看所有仓库中所有docker版本
yum list docker-ce --showduplicates | sort -r

#安装docker
yum install docker-ce #默认安装最新版
yum install docker-ce-17.12.0.ce #安装指定版本

#查看docker运行状态：
service docker status
#启动docker
service docker start
systemctl start docker.service
#设置开机启动
systemctl enable docker.service

```

### 安装minikube

```
#首先安装docker环境
#关掉swap：关闭命令swapoff -a
#使用阿里云修改好的minikube进行安装，否则在初始化minikube的时候会卡在墙上下不来
curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v0.35.0/minikube-linux-amd64
chmod +x minikube
mv minikube /usr/bin/minikube

#加载阿里云k8s的官方源并且安装相关命令组件
cd /etc/yum.repos.d/
cat>>kubernetes.repo<<EOF
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF

yum install kubectl kubelet kubeadm -y

systemctl start kubelet && systemctl enable kubelet

#使用缺省VirtualBox驱动来创建Kubernetes本地环境
minikube start --registry-mirror=https://registry.docker-cn.com

#提示错误；! Please don't run minikube as root or with 'sudo' privileges. It isn't necessary.
切换到非root用户jeffrey下，使用非root用户启动
#提示错误;VBoxManage not found. Make sure VirtualBox is installed and VBoxManage is in the path
需要安装VBoxManage
vi /etc/yum.repos.d/virtualbox.repo

[virtualbox]
name=Oracle Linux / RHEL / CentOS-$releasever / $basearch - VirtualBox
baseurl=http://download.virtualbox.org/virtualbox/rpm/el/$releasever/$basearch
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://www.virtualbox.org/download/oracle_vbox.asc

#安装VBoxManage,一路输入y。完成在Centos中安装VirtualBox。(下载挺慢)
yum install VirtualBox-6.0

#CentOS7下minikube start后VirtualBox执行报错
解决办法：

1. 根据提示去运行“sudo /sbin/vboxconfig”

2. 继续根据报错提示，安装 kernel-devel-3.10.0-862.el7.x86_64  // 根据报错提示安装

yum -y install kernel-devel-3.10.0-1160.el7.x86_64

2. 安装完成后再运行“sudo /sbin/vboxconfig”，成功。

#使用缺省VirtualBox驱动来创建Kubernetes本地环境
minikube start --registry-mirror=https://registry.docker-cn.com
#还是出错，提示Please install the gcc make perl packages from your distribution
yum install gcc make perl

# 启动时需要CPU支持虚拟化，可以从bios开启，有一个检测的小工具cpu-v(https://dl.pconline.com.cn/download/400994.html?qq-pf-to=pcqq.group)


参考：https://www.cnblogs.com/linguoguo/p/10619460.html
https://www.icode9.com/content-4-50687.html

https://blog.csdn.net/qq_38780765/article/details/105278313




············
#先决条件安装docker;;Minikube全程在普通用户下安装使用，使用root用户会报错，如果在minikube start的时候，报错没有权限访问docker，把当前用户添加到docker组里面就行

#安装kubectl


curl -Lo minikube http://kubernetes.oss-cn-hangzhou.aliyuncs.com/minikube/releases/v1.2.0/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/

minikube start --driver=docker --registry-mirror=https://registry.docker-cn.com


```

### docker-machine使用

```
#列出可用的机器
docker-machine ls
#创建机器，指定指定用来创建机器的驱动类型，这里是 virtualbox
docker-machine create --driver virtualbox test
#查看vm环境变量
docker-machine env test
#连接test的docker-server
 @FOR /f "tokens=*" %i IN ('docker-machine env --unset') DO @%i
 
 
```

### CentOS7下搭建registry

```
#查找registry镜像
docker search registry
#拉取镜像
docker pull registry
#启动
docker run -p 5000:5000 registry

#registry接口默认是https的，需要设置insecure-registry参数
#在vim /usr/lib/systemd/system/docker.service 文件中添加参数----insecure-registry #192.168.95.10，没有生效，使用的另一种方法，就是添加daemon.json文件
vim /etc/docker/daemon.json
{ "insecure-registries":["192.168.95.10:5000"] }
# 重新启动docker
systemctl daemon-reload
systemctl restart docker
#重新push镜像，可以成功push上去
docker push 192.168.95.10:5000/registry
```





### 创建image：

1、通过Dockerfile文件创建image

```
#dockerfile内容
FROM java:8
MAINTAINER bingo
ADD retailcloud-registry-0.0.1-SNAPSHOT.jar retailcloud-registry.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","retailcloud-registry.jar"]

#根据dockerfile创建image
docker build -t nginx:v3 .
```

2、通过container创建image

```
docker commit pensive_bohr jeffrey/hello
```

docker push到dockerhub,格式：docker注册用户名/镜像名

ENTRYPOINT：让容器以应用程序或者服务的形式运行

### window下搭建minikube

```


参考：https://blog.csdn.net/u014636124/article/details/105145674/
```

