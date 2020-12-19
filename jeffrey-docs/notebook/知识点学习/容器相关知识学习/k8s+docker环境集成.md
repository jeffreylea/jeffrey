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