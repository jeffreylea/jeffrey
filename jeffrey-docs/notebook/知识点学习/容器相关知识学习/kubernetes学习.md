+ k8s部署mysql时使用configMap

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