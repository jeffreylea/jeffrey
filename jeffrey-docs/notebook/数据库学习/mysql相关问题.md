

“Too many connections”引起MySQL崩溃并启动失败**
------------

https://blog.51cto.com/10950710/2160220

show variables like '%max_connections%'; 查看最大连接数
set global max_connections=1000;        重新设置最大连接数

**为表test添加索引**
-----------
创建主键索引：
alter table test add PRIMARY KEY(id)

创建普通索引：
alter TABLE test add index cid(id)

create index cid1 on test(id)

添加唯一索引：
create UNIQUE index cunique on test(id)

mysql 在使用like的时候只有后面使用%，才会使用到索引。

mysql中有哪几种锁？
-------
+ 表级锁：开销小，加锁快；不会出现死锁；锁定粒度大，发生锁冲突的概率最高，并发度最低。
+ 行级锁：开销大，加锁慢；会出现死锁；锁定粒度小，发生锁冲突的概率最低，并发度也最高。
+ 页面锁：开销和加锁时间介于表锁和行锁之间，会出现死锁，锁定粒度介于表锁和行锁之间，并发度一般。

innoDB使用行锁，MyISAM使用表级锁，myisam在执行select之前，会自动给涉及的表加读锁，在执行更新操作（update、delete、insert等），会自动给涉及的表加写锁，这个过程不需要用户干预。inNoDB自动给修改操作加锁，给查询操作不自动加锁。

mysql数据库支持的存储引擎：
---------------
show engines;

查询表使用的索引：
-------
show index from tableName

like声明中的%和_的区别：
-----
%匹配0个或多个字符，_匹配一个字符

mysql和Unix时间戳之间的转换：
----
UNIX_TIMESTAMP 是从 MySQL 时间戳转换为 Unix 时间戳的命令
FROM_UNIXTIME 是从 Unix 时间戳转换为 MySQL 时间戳的命令

BLOB 和 TEXT 有什么区别：
----
BLOB 是一个二进制对象，可以容纳可变数量的数据。TEXT 是一个不区分大小写的 BLOB，BLOB 和 TEXT 类型之间的唯一区别在于对 BLOB 值进行排序和比较时区分大小写，对 TEXT 值不区分大小写。

通用sql函数：
--------
+ concat: 连接两个字符串
+ format(x,d):将数字x格式化为四舍五入d位小数





## mysql索引底层数据结构与算法

索引是帮助mysql高效获取数据的排好序的数据结构。

B-tree

### 高版本sql_mode=only_full_group_by问题

这个错误发生在mysql 5.7 版本及以上版本会出现的问题：

​    mysql 5.7版本默认的sql配置是:sql_mode="ONLY_FULL_GROUP_BY"，这个配置严格执行了"SQL92标准"。

解决方法：

+ 很多从5.6升级到5.7时，为了语法兼容，大部分都会选择调整sql_mode，使其保持跟5.6一致，为了尽量兼容程序。

  ```
  查看sqlmode: select @@GLOBAL.sql_mode;
  设置sqlmode:
  set @@GLOBAL.sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
  这种方式是暂时解决，永久生效需要修改my.cnf配置文件。
  ```

+ 使用ANY_VALUE（），MySQL会忽略每个名称组中的地址值的不确定性并接受查询

### 进入pod查看mysql最大连接数

进入Pod内部执行相关操作: 

 kubectl exec -it pod-name /bin/sh  
登陆mysql：mysql -uroot -pXXXXX
执行：show variables like "%max_connections%";
命令行修改最大连接数为1000： set global max_connections = 1000;

```
# 查看时区
show variables like '%time_zone'
# 设置时区
set time_zone='+8:00';

```



