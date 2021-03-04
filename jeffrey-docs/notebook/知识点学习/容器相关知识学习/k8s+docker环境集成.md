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



+ kubernetes对外暴露服务的方法

  1、nodePort

  在服务变多的情况下会导致节点要开的端口越来越多，不好管理，

  2。LoadBalancer 

  3、Ingress

  ingress是k8s官方提供的对外暴露服务的方式，也是在生产环境中用的比较多的方式；ingress是k8s的资源对象，用于对外暴露服务。ingress controller是一个pod服务，封装了一个web前端负载均衡器，

+ 删除ingress

  kubectl delete ingress

+ 删除pod
  kubectl delete pod

+ k8s 安装部署mysql

  

+ PVC （PersistentVolumeClaim）

  持久卷声明

  

  ```
  # 创建具有指定名称的namespace
  kubectl create namespace
  
  # 通过配置文件名或stdin创建一个集群资源对象
  kubectl create -f filename
  
  # 输出一个多个资源的详细信息
  #首先检查是否有精确匹配TYPE和NAME_PREFIX的资源，如果没有，将会输出所有名称以NAME_PREFIX开头的资源详细信息
  kubectl describe TYPE NAME_PREFIX
  
  ```

+ Kube-dns

  通常会为service赋予一个名为“service名称.namespace.svc.cluster.local”的A记录，用来解析service的cluster IP。如果访问default namespace下的服务，可以通过“service名称”直接访问；如果访问其他namespace下的服务，可以通过“service名称.namespace”访问。k8s会为每个容器提供默认的/etc/resolv.conf配置

+ yaml 知识

  + yaml map

    ```
    apiVersion: v1
    kind: Pod
    　　注：---为可选的分隔符 ，当需要在一个文件中定义多个结构的时候需要使用。上述内容表示有两个键apiVersion和kind，分别对应的值为v1和Pod。
    ```

  + Maps的value既能够对应字符串也能够对应一个Maps。例如：

    ```
    apiVersion: v1
    kind: Pod
    metadata:
      name: kube100-site
      labels:
        app: web
    ```

+ 创建Deployment|deployment部署文件详解

```
#test-pod 
apiVersion: v1 #指定api版本，此值必须在kubectl apiversion中   
kind: Pod #指定创建资源的角色/类型   
metadata: #资源的元数据/属性   
  name: test-pod #资源的名字，在同一个namespace中必须唯一   
  labels: #设定资源的标签 
    k8s-app: apache   
    version: v1   
    kubernetes.io/cluster-service: "true"   
  annotations:            #自定义注解列表   
    - name: String        #自定义注解名字   
spec: #specification of the resource content 指定该资源的内容   
  restartPolicy: Always #表明该容器一直运行，默认k8s的策略，在此容器退出后，会立即创建一个相同的容器   
  nodeSelector:     #节点选择，先给主机打标签kubectl label nodes kube-node1 zone=node1   
    zone: node1   
  containers:   
  - name: test-pod #容器的名字   
    image: 10.192.21.18:5000/test/chat:latest #容器使用的镜像地址   
    imagePullPolicy: Never #三个选择Always、Never、IfNotPresent，每次启动时检查和更新（从registery）images的策略， 
                           # Always，每次都检查 
                           # Never，每次都不检查（不管本地是否有） 
                           # IfNotPresent，如果本地有就不检查，如果没有就拉取 
    command: ['sh'] #启动容器的运行命令，将覆盖容器中的Entrypoint,对应Dockefile中的ENTRYPOINT   
    args: ["$(str)"] #启动容器的命令参数，对应Dockerfile中CMD参数   
    env: #指定容器中的环境变量   
    - name: str #变量的名字   
      value: "/etc/run.sh" #变量的值   
    resources: #资源管理 
      requests: #容器运行时，最低资源需求，也就是说最少需要多少资源容器才能正常运行   
        cpu: 0.1 #CPU资源（核数），两种方式，浮点数或者是整数+m，0.1=100m，最少值为0.001核（1m） 
        memory: 32Mi #内存使用量   
      limits: #资源限制   
        cpu: 0.5   
        memory: 1000Mi   
    ports:   
    - containerPort: 80 #容器开发对外的端口 
      name: httpd  #名称 
      protocol: TCP   
    livenessProbe: #pod内容器健康检查的设置 
      httpGet: #通过httpget检查健康，返回200-399之间，则认为容器正常   
        path: / #URI地址   
        port: 80   
        #host: 127.0.0.1 #主机地址   
        scheme: HTTP   
      initialDelaySeconds: 180 #表明第一次检测在容器启动后多长时间后开始   
      timeoutSeconds: 5 #检测的超时时间   
      periodSeconds: 15  #检查间隔时间   
      #也可以用这种方法   
      #exec: 执行命令的方法进行监测，如果其退出码不为0，则认为容器正常   
      #  command:   
      #    - cat   
      #    - /tmp/health   
      #也可以用这种方法   
      #tcpSocket: //通过tcpSocket检查健康    
      #  port: number    
    lifecycle: #生命周期管理   
      postStart: #容器运行之前运行的任务   
        exec:   
          command:   
            - 'sh'   
            - 'yum upgrade -y'   
      preStop:#容器关闭之前运行的任务   
        exec:   
          command: ['service httpd stop']   
    volumeMounts:  #挂载持久存储卷 
    - name: volume #挂载设备的名字，与volumes[*].name 需要对应     
      mountPath: /data #挂载到容器的某个路径下   
      readOnly: True   
  volumes: #定义一组挂载设备   
  - name: volume #定义一个挂载设备的名字   
    #meptyDir: {}   
    hostPath:   
      path: /opt #挂载设备类型为hostPath，路径为宿主机下的/opt,这里设备类型支持很多种 
    #nfs
```

+ kubectl version #查看k8s版本
+ kubectl apply -f time.yaml 部署文件

### 利用VMWare搭建Kubernetes集群

+ 参考资料

  ```
  https://www.cnblogs.com/paul8339/p/12938221.html
  https://ke.qq.com/course/379938?taid=2934283002301474
  https://blog.51cto.com/13555423/2440608
  ```

+ 准备环境

  ```
  准备3台机器，master，slave1，slave2
  集群中所有机器之间网络互通
  可以访问外网，需要拉取镜像
  禁止swap分区
  
  #关闭防火墙
  systemctl status firewalld
  systemctl disable firewalld
  
  #查看SELinux状态
  /usr/sbin/sestatus -v ##如果SELinux status参数为enabled即为开启状态
  
  #关闭selinux：
  sed -i 's/enforcing/disabled/' /etc/selinux/config 
  setenforce 0
  
  #关闭swap
  swapoff -a  //临时关闭
  vi /etc/fstab   #永久关闭，删除swap配置哪一行
  
  #添加主机名与IP对应关系（记得设置主机名）：
  cat /etc/hosts
  192.168.95.10 k8s-master
  192.168.95.11 k8s-node1
  192.168.95.12 k8s-node2
  
  将桥接的IPv4流量传递到iptables的链：
  $ cat > /etc/sysctl.d/k8s.conf << EOF
  net.bridge.bridge-nf-call-ip6tables = 1
  net.bridge.bridge-nf-call-iptables = 1
  EOF
  $ sysctl --system
  ```

+ 安装docker/kubeadm/kubelet

  参考： https://kubernetes.io/zh/docs/setup/production-environment/tools/kubeadm/install-kubeadm/

```
添加阿里云YUM软件源
$ cat > /etc/yum.repos.d/kubernetes.repo << EOF
[kubernetes]
name=Kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF

1、3台机器分别安装好docker
安装docker
# Docker 要求 CentOS 系统的内核版本高于 3.10 ，查看本页面的前提条件来验证你的CentOS 版本是否支持 Docker 。
#通过 uname -r 命令查看你当前的内核版本
uname -r

#更新yum，确保 yum 包更新到最新
yum update

#安装wget,为了下载阿里云yum源
yum install wget

#设置阿里云yum源(可以先把CentOS-Base.repo备份成CentOS-Base.repo.bak,会覆盖此文件)
wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo

#安装需要的软件包， yum-util 提供yum-config-manager功能，另外两个是devicemapper驱动依赖的
yum install -y yum-utils device-mapper-persistent-data lvm2
#设置docker-ce安装yum源
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo

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

#配置加速器等配置，vim /etc/docker/daemon.json
#镜像加速器地址：https://cr.console.aliyun.com/cn-shanghai/instances/mirrors
{
 "registry-mirrors": ["https://sanen8ui.mirror.aliyuncs.com"],
 "insecure-registries":["192.168.95.10:5000"],
 "exec-opts": ["native.cgroupdriver=systemd"]
 }


2、3台机器分别安装kubeadm/kubelet/kubectl
由于版本更新频繁，这里指定版本号部署：
yum install -y kubelet-1.14.0 kubeadm-1.14.0 kubectl-1.14.0
systemctl enable kubelet

3、部署Kubernetes Master
在192.168.95.10（Master）执行：
kubeadm init \
  --apiserver-advertise-address=192.168.95.10 \
  --image-repository registry.aliyuncs.com/google_containers \
  --kubernetes-version v1.14.0 \
  --service-cidr=10.1.0.0/16 \
  --pod-network-cidr=10.244.0.0/16
  
  
 #成功提示信息如下：
 To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

Then you can join any number of worker nodes by running the following on each as root:

kubeadm join 192.168.95.10:6443 --token whhnqr.umhr6136qjve9v5p \
    --discovery-token-ca-cert-hash sha256:a6e57596be4c75f40f9cb9d18cc80baa1beb1e623bc7c6fdc5ba12ef3491d20c
    
  4、安装Pod网络插件（CNI）
  kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/a70459be0084506e4ec919aa1c114638878db11b/Documentation/kube-flannel.yml
  
#出现错误error: SchemaError(io.k8s.api.apps.v1beta1.DeploymentStatus): invalid object doesn't have additional properties
解决：之前安装过minikube，使用的版本是kubectl1.10，二进制文件是在/usr/local/bin/kubectl这个目录下，直接执行kubectl就是找的这个目录，之后安装的kubectl1.14版本二进制文件是在/usr/bin/kubectl这里，没有生效，将/usr/bin/kubectl这个目录下的kubectl复制到/usr/local/bin/kubectl，就可以了。这是由于版本不同引起的问题。
参考：https://www.bookstack.cn/read/kubernetes-practice-guide/troubleshooting-cases-schemaerror-when-using-kubectl-apply-or-edit.md

#机器重启之后报错，重新执行kubectl命令报错：The connection to the server localhost:8080 was refused - did you specify the right host or port?
重新初始化
kubeadm reset
kubeadm init \
  --apiserver-advertise-address=192.168.95.10 \
  --image-repository registry.aliyuncs.com/google_containers \
  --kubernetes-version v1.14.0 \
  --service-cidr=10.1.0.0/16 \
  --pod-network-cidr=10.244.0.0/16
  参考：https://www.cnblogs.com/CoderLinkf/p/12410749.html
  
 #出现错误  [ERROR Port-10252]: Port 10252 is in use
  
  
  
```



+ 加入kubernates node

  ```
  # 在安装kubernetes master时，集群初始化后成功返回如下信息，在所有节点执行如下命令：
  kubeadm join 192.168.95.10:6443 --token 8v851r.1jq37d1lf48ugb3f  --discovery-token-ca-cert-hash sha256:1a3540d8078aa07de5952608cb81dc0fe29d99f815864ab39ec0074e57cd3cd2
  #kubelet启动报错，join成功后会自动启动并生成主要配置文件：
  # 同时要注意kubelet版本是否与集群一致，不一致可能会join不到集群中
#参考：
  https://bbs.huaweicloud.com/blogs/158024
  
  
  #加入node报错：error execution phase preflight: couldn't validate the identity of the API Server: abort connecting to API servers after timeout of 5m0s
  可能由于token过期，需要重新生成，步骤如下：
  主节点执行命令：
  kubeadm token create
  会生成token，如：whhnqr.umhr6136qjve9v5p
  执行命令：
  openssl x509 -pubkey -in /etc/kubernetes/pki/ca.crt | openssl rsa -pubin -outform der 2>/dev/null | openssl dgst -sha256 -hex | sed 's/^.* //'
  会生成sha256：a6e57596be4c75f40f9cb9d18cc80baa1beb1e623bc7c6fdc5ba12ef3491d20c
  在node节点再次join即可：
  kubeadm join 192.168.95.10:6443 --token whhnqr.umhr6136qjve9v5p     --discovery-token-ca-cert-hash sha256:a6e57596be4c75f40f9cb9d18cc80baa1beb1e623bc7c6fdc5ba12ef3491d20c
  ```
  
+ 测试kubernetes集群

  ```
  在Kubernetes集群中创建一个pod，验证是否正常运行：
  $ kubectl create deployment nginx --image=nginx
  $ kubectl expose deployment nginx --port=80 --type=NodePort
  $ kubectl get pod,svc
  访问地址：http://NodeIP:Port
  ```

+ 部署 Dashboard

  ```
  #执行命令：
  kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
  
  由于上面镜像网络访问限制，需要修改下镜像地址：lizhenliang/kubernetes-dashboard-amd64:v1.10.1
  
  wget https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
  
  #默认Dashboard只能集群内部访问，修改Service为NodePort类型，暴露到外部：
  kind: Service
  apiVersion: v1
  metadata:
    labels:
      k8s-app: kubernetes-dashboard
    name: kubernetes-dashboard
    namespace: kube-system
  spec:
    type: NodePort
    ports:
      - port: 443
        targetPort: 8443
        nodePort: 30001
    selector:
      k8s-app: kubernetes-dashboard
      
    #部署
    kubectl apply -f kubernetes-dashboard.yaml
    #访问，需要用https访问,chrome浏览器出现网络错误时，输入thisisunsafe即可跳转
    https://NodeIP:30001
    
   # 创建service account并绑定默认cluster-admin管理员集群角色：
  $ kubectl create serviceaccount dashboard-admin -n kube-system
  $ kubectl create clusterrolebinding dashboard-admin --clusterrole=cluster-admin --serviceaccount=kube-system:dashboard-admin
  $ kubectl describe secrets -n kube-system $(kubectl -n kube-system get secret | awk '/dashboard-admin/{print $1}')
  
  使用生成的token登陆。
  
  ```

  + 容器镜像仓库选择
  
    ```
    Harbor介绍：
    参考资料：https://blog.csdn.net/zhangjunli/article/details/107642592
    harbor是一个用于存储和分发docker镜像的企业级registry服务器，harbor是由VMware公司开源的docker registry的管理项目，它以docker公司开源的registry为基础，帮助用户迅速搭建一个企业级的docker registry服务。harbor的所有组件都在docker中部署，所以harbor可以使用docker compose快速部署；Harbor是基于Docker Registry V2版本，所以docker必须大于等于1.10.0版本，docker-compose必须要大于1.6.0版本！
    docker-compose：是docker官方的开源项目，负责实现docker容器集群的快速编排。
    harbor的每个组件都是以docker容器的形式的构建的，
    可以使用Docker Compose来进行部署。如果环境中使用了kubernetes，Harbor也提供了kubernetes的配置文件。Harbor大概需要以下几个容器组成：
    ui(Harbor的核心服务)
    log(运行着rsyslog的容器，进行日志收集)
    mysql(由官方mysql镜像构成的数据库容器)
    Nginx(使用Nginx做反向代理)
    registry(官方的Docker registry)
    adminserver(Harbor的配置数据管理器)
    jobservice(Harbor的任务管理服务)
    redis(用于存储session)
    Artifactory介绍：
    是一款maven仓库服务端软件，nexus也是maven仓库服务端软件
    ```
  
    