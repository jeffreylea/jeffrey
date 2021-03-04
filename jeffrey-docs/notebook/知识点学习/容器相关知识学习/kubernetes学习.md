+ ### docker架构
  + 镜像（image）：docker镜像，就相当于一个root文件系统，
  + 容器（Container）:镜像和容器的关系，就像面向对象的程序设计的类和实例一样，镜像时静态的定义，容器是镜像运行的实体，容器可以被创建、启动、停止、删除、暂停等
  + 仓库（repository）：仓库可以看成一个代码控制中心，用来保存镜像

  docker采用C/S架构，使用API来管理和创建Docker容器，docker容器通过docker镜像来创建。

+ ### Kubernetes 特点

  - **可移植**: 支持公有云，私有云，混合云，多重云（multi-cloud）
  - **可扩展**: 模块化, 插件化, 可挂载, 可组合
  - **自动化**: 自动部署，自动重启，自动复制，自动伸缩/扩展

+ ### k8s集群中的三种IP

  ``` 
  Node IP：node节点的IP地址，即物理网卡的IP地址；可以是物理机的IP，也可能是虚拟机IP，每个service都会在node节点上开通一个端口，外部可以通过nodeIP：nodePort就可以访问service里的pod，和我们访问服务器部署的项目一样，
  
  Pod IP:Pod的ip地址，即docker容器的IP地址，此为虚拟IP地址；是Docker Engine根据docker网桥的IP地址段进行分配的；同service下的pod可以直接根据PODIP直接通信；不同service下的pod在集群间通信需要借助cluster ip;pod和集群外通信要借助nodeIP。
  
  Cluster IP：service的IP地址，此为虚拟IP地址；外部网略无法ping通，只有kubernetes集群内部访问使用；cluster IP 仅作用于kubernetes service这个对象并由kubernetes管理和分配；cluster IP不能被ping，他没有一个“实体网络对象”来响应；在不同Service下的pod节点在集群间相互访问可以通过Cluster IP
  
  对应三种网络：节点网络、service网络、pod网络
  ```

+ ### k8s暴露服务的三种方式

  ```
  NodePort:
  ```

  

+ ### k8s部署mysql时使用configMap

```
apiVersion: v1
kind: ConfigMap
metadata:
  name: mysql-configmap
  namespace: ai-screen-dev
data:
  mysqld.cnf: |-
    [mysqld]
    skip-name-resolve
    character_set_server=utf8
    innodb_buffer_pool_size=1G
    sync_binlog=0
    lower_case_table_names=1
    innodb_file_per_table=1
    max_connections=301
    back_log=150
    max_user_connections=150
    innodb_flush_log_at_trx_commit=2
    innodb_log_files_in_group=3
    slow_query_log=1
    long_query_time=2
    slow_query_log_file=/var/log/mysql/slow.log
    innodb_log_file_size=128M
    sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION

参考：https://www.cnblogs.com/yuhaohao/p/13474826.html
```

+ ### kubernetes基础概念理解

  ```
   参考：
   K8S核心组件和架构图：https://blog.csdn.net/weixin_38028611/article/details/103133316?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&dist_request_id=bc311be4-af50-400b-8234-bbec5a5d9261&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
   k8s主要概念和组件：https://blog.csdn.net/fangfu123/article/details/105792826
  
  ```

+ k8s常用命令

  ```
  #批量删除状态pod
  kubectl get pods -n <Namespace> | grep Evicted | awk '{print $1}' | xargs kubectl delete pod -n <Namespace>
  
  kubectl get pods -n <Namespace> 获取指定 Namespace 下的所有 Pods
  grep <status> 筛选出指定状态的 Pods
  awk '{print $1}' 取 Pod 的 Name
  xargs kubectl delete pod -n <Namespace> 传递 Pod's Name 用于删除 Pod
  
  ```

+ kubectl config命令

  ```
  kubectl config命令管理kubeconfig文件，
  ```

+ 组件

  ```
  etcd 保存了整个集群的状态，就是一个数据库，只有API Server能与其通信；
  apiserver 提供了资源操作的唯一入口，并提供认证、授权、访问控制、API 注册和发现等机制；
  controller manager 负责维护集群的状态，比如故障检测、自动扩展、滚动更新等;
  scheduler 负责资源的调度，按照预定的调度策略将 Pod 调度到相应的机器上；
  kubelet 负责维护容器的生命周期，同时也负责 Volume（CSI）和网络（CNI）的管理；
  Container runtime 负责镜像管理以及 Pod 和容器的真正运行（CRI）;
  kube-proxy 负责为 Service 提供 cluster 内部的服务发现和负载均衡；
  除了上面的这些组件，还有一些第三方的组件：
  
  kube-dns 负责为整个集群提供 DNS 服务
  Ingress Controller 为服务提供外网入口
  Heapster 提供资源监控
  Dashboard 提供 GUI
  组件之间的关系：
  从上面图可以看到所有组件均是通过API Server进行通信，所以API Server就是一个中枢神经，在生产中我们会把master部署为多节点，做高可用。
  etcd主要是存储集群里的信息，比如集群状态，集群的各Node信息等，只有API能与其通信，所以我们也会把etcd做高可用，etcd是一个单独的组件，一个应用软件，做高可用建议是奇数节点，比如3，5等，节点不应过多，因为节点过多，节点之间的数据同步是会有一定的开销，影响集群的性能。
  Schduler负责整个集群的调度，它通过API Server来检测Node上Pod的状态，然后会根据定义的策略来调度pod并绑定Node。
  Controller manager负责pod的控制，常见的比如定义了一个Pod为replicaSet，然后因为某些原因当前pod挂掉了，这时候Controller manager就会为你在Node上重启该Pod。
  kubelet是Node上的组件，它会检测Node上的Pod，并将其状态更新到API Server。
  kube-proxy主要是负责代理转发，主要控制service，并将sevice状态更新到API Server。
  kubectl是集群的管理组件，主要也是调用API Server，然后进行整个集群的管理。
  ```

  + Helm使用

    ```
    可以将Helm看作Kubernetes下的apt-get/yum,Helm 是一个命令行下的客户端工具。主要用于 Kubernetes 应用程序 Chart 的创建、打包、发布以及创建和管理本地和远程的 Chart 仓库。
    Helm 客户端安装:
    方式一：使用官方提供的脚本一键安装:
    curl https://raw.githubusercontent.com/helm/helm/master/scripts/get > get_helm.sh
    $ chmod 700 get_helm.sh
    $ ./get_helm.sh
    
    方式二：手动下载安装:
    #从官网下载最新版本的二进制安装包到本地：https://github.com/kubernetes/helm/releases
    tar -zxvf helm-2.9.0.tar.gz # 解压压缩包
    # 把 helm 指令放到bin目录下
    mv helm-2.9.0/helm /usr/local/bin/helm
    helm help # 验证
    ```

    

| 单词与术语 |
| ---------- |
| spec：规格 |

+ 参考资料

  k8s中文社区：http://docs.kubernetes.org.cn/

+ k8s相关命令

```
#kubectl命令行的语法如下
kubectl [command] [TYPE] [NAME] [flags]
其中，command、TYPE、NAME、flags的含义如下。
command：子命令，用于操作Kubernetes集群资源对象的命令，例如create、delete、describe、get、apply等
TYPE：资源对象的类型，区分大小写，能以单数形式、复数形式或者简写形式表示。例如以下3种TYPE是等价的
$ kubectl get pod pod1
$ kubectl get pods pod1
$ kubectl get po pod1
NAME：资源对象的名称，区分大小写。如果不指定名称，则系统将返回属于TYPE的全部对象的列表，例如$ kubectl get pods将返回所有Pod的列表
flags：kubectl子命令的可选参数，例如使用“-s”指定apiserver的URL地址而不用默认值。
#创建namespace
kubectl create namespace test
#查看k8s版本
kubectl verison

#删除pod
kubectl delete pod -n retailcloud retailcloud-data-screen-svc-bbb56cc48-6z2gm

#kubectl输出格式使用-o指定，可以使用这种方法快速创建pod
kubectl get pods emqx3-1-644f9979d-jcg5v -n ai-screen-test -o yaml

#执行命令创建pod或者说yaml的对应类型
kubectl create -f testitem_deployment.yaml

#使用scale命令扩展pod数量；scale可以指定多个前提条件，如：当前副本数量--current-replicas或资源版本--resource-version，进行伸缩比例设置前，系统会先验证前提条件是否成立
kubectl scale --replicas=2 deployment/iot-devicemanager -n ai-screen-dev

#强制删除pod，--grace-period参数指定删除延迟时间，--force表示强制删除
kubectl delete pods iot-devicemanager-test-5bc4775ccd-vsgfl --grace-period=0 --force -n ai-screen-test

# 查看pod资源，非常详细
kubectl describe 



```

# 