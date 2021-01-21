### **Linux 常用操作记录**

+ ls

原意：list，

+ **netstat:**

根据端口号查找进程号，查找程序路径
netstat -nlp | grep 端口号
得到PID后，执行命令：
ll /proc/进程号/cwd

```
params:
-n, --numeric              don't resolve names-> 不根据IP解析机器名
-l, --listening            display listening server sockets->展示监听服务套接字连接
-p, --programs             display PID/Program name for sockets-> 展示套接字的程序名称和PID

```

+ ps

  显示系统进程

  -e all processes

  -f full

  -a  all w/ tty, including other users

  -u by effective user ID (supports names)

  -x  processes w/o controlling ttys

  ```
  ps -ef  是用标准的格式显示进程的
  ps -aux 使用BSD的格式显示进程的
  用户ID      进程ID  父进程ID  进程占用CPU的百分比   进程启动到现在的时间                    命令的名称和参数
  UID        PID     PPID      C                  STIME           TTY          TIME CMD
  
  
  ```

  

给redis设置密码：
redis-cli->config set requirepass inspur123!@# 

redis远程密码登陆

1、redis-cli.exe -h 127.0.0.1 -p 6379

2、输入密码命令：auth 密码

+ useradd:

创建用户user1: useradd user1

删除用户user1：userdel -f user1

+ passwd ：

为新建用户设置密码或修改用户密码。

passwd user1

groupadd：创建用户组

gpasswd：为组添加用户，gpasswd -a user1 usergroup

+ chown

  改变文件属性，改变文件拥有者

  ```
  语法
  chown [-cfhvR] [--help] [--version] user[:group] file...
  -R : 处理指定目录以及其子目录下的所有文件
  ```

+ LINUX下不同的文件类型有不同的颜色，这里

![img](https://img-blog.csdn.net/20180524165513592?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTIxMDYzMDY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

蓝色表示目录;
绿色表示可执行文件，可执行的程序;
红色表示压缩文件或包文件;
浅蓝色表示链接文件;
灰色表示其它文件;

+ 为脚本设置可执行权限

  ```
  chmod +x test.sh
  ```
  
+ 杀死进程

  ps -aux| grep ***   和ps -ef作用类似，也是显示当前运行的线程

  kill -9 PID

  aux选项如下所示：

  a-显示所有用户的进程

  u-显示进程的用户和拥有者

  x-显示不依附于终端的进程
  
  
  
+ awk文本处理工具

  awk 是一种处理文本文件的语言，强大的文本分析工具

  ```
  # 每行按空格或TAB分割，输出文本中的1、4项
  awk '{print $1,$4}' log.txt
  cat log.txt | awk '{print $1,$4}'
  # 或者指定分隔符，
  awk -F "," '{print $1}' log.txt
  ```

+ top 性能分析工具

  top命令是Linux下常用的性能分析工具，能够实时显示系统各个进程的资源占用情况，类似于windows的任务管理器。

  

+ man 

  想了解一个命令代表什么含义和用法，可以使用man command查看。

+ grep是一个强大的文本搜索工具，能够使用正则表达式搜索文本，并把匹配的行打印出来。

  打印匹配行的前后10行：grep -10

+ source命令（点命令）

  使修改的配置文件立即生效：source fileName或者. fileName

+ ulimit

  控制shell程序的资源

  ulimit -n <文件数目>指定同一时间最多开启的文件数

+ sed命令

  命令格式：sed [选项] ‘命令’ 输入文本 

  利用脚本处理文本文件
  
  sed -i 插入， i 的后面可以接字串，而这些字串会在新的一行出现(目前的上一行)；

```
  sed -i 's/原字符串/新字符串/'  fileName

  sed -i '$a\要插入的文字' fileName
  
  sed -n '1p' fileName 显示第一行
```

+ eval 重新运算求出参数的内容

  + $$、$!、 $?、$-、$*、$@、$#、$0、$1-$n

  $$：Shell本身的PID（ProcessID，即脚本运行的当前[进程ID](https://www.baidu.com/s?wd=进程ID&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)号）

  $!: Shell最后运行的后台Process的PID(后台运行的最后一个进程的[进程ID](https://www.baidu.com/s?wd=进程ID&tn=SE_PcZhidaonwhc_ngpagmjz&rsv_dl=gh_pc_zhidao)号)

  $?:最后运行的命令的结束代码（返回值）即执行上一个指令的返回值 (显示最后命令的退出状态。0表示没有错误，其他任何值表明有错误)

  $-:显示shell使用的当前选项，与set命令功能相同

  

+ rpm 命令

  rpm -ivh XXX:安装软件

  参数： -i 　显示套件的相关信息

  -a 　查询所有套件 

  rpm -qa | grep -i mysql :查询安装的mysql

+ tar 解压

  tar -zxvf XXX

  参数：

  ​       z:gzip压缩

  　　c:创建压缩包

  　　v:显示打包压缩解压过程

  　　f:接着压缩

  　　t:查看压缩包内容

  　　x:解压

  　　X:指定文件列表形式排除不需要打包压缩的文件或目录

  　　-exclude:指定排除文件或目录不需要打包压缩的文件或目录（也可以用正则匹配/通配符等）

  　　-C:解压到指定目录

  

### **Linux 网络配置**

网络配置文件位置：/etc/sysconfig/network-scripts	

ifcfg-ethX: ethX 是设备名，例如eth0，eth1，分别代表不同的接口，网卡。

```
DEVICE=eth0  #网卡设备名称   
ONBOOT=yes  #启动时是否激活 yes | no  
BOOTPROTO=static  #协议类型 dhcp bootp none  
IPADDR=192.168.1.90  #网络IP地址  
NETMASK=255.255.255.0  #网络子网地址  
GATEWAY=192.168.1.1  #网关地址  
BROADCAST=192.168.1.255  #广播地址  
HWADDR=00:0C:29:FE:1A:09  #网卡MAC地址  
TYPE=Ethernet  #网卡类型为以太网
```



### shell test命令

shell 的test命令用于检查某个条件是否成立，它可以进行数值、字符、文件	三个方面的测试。

+ 数值测试

  -eq 等于

 ``` 
num1=100
num2=100
if test $[num1] -eq $[num2]
then
    echo '两个数相等！'
else
    echo '两个数不相等！'
fi
 ```

+ 字符串测试

  -n 字符串 字符串的长度不为零判断

  ```
  if [ -n "kk" ]
  then
  echo "字符串kk长度不为零"
  fi
  ```

  -z 字符串 字符串的长度为零判断

+ 文件测试

  -e 文件名 判断文件是否存在，存在则为true

  ```
  if test -e ./bash
  then
      echo '文件已存在!'
  else
      echo '文件不存在!'
  fi
  ```


+ $(command)和${paramter}的区别

  $(commad)是执行command并返回结果

  ${paramter}是返回变量的值

+ 运行shell脚本问题解决

  运行shell脚本时出现错误：

  /bin/bash^M: bad interpreter: 没有那个文件或目录

  windows 下编辑过脚本的原因

+ []和test

  两者是一样的，在命令行里test expr和[ expr ]的效果相同。[]

### **查看Linux用户相关信息**

Linux系统中用户信息放在/etc/passwd下，包含每个用户的基本信息，每行记录一个用户的基本信息。改文件包含三部分。

```
* 第 1 部分：`root` 用户信息，这代表管理员账户，对系统的每个方面都有完全的权力。
* 第 2 部分：系统定义的账号信息，这些群组和账号是正确安装和更新系统软件所必需的
* 第 3 部分：真实用户的账户信息，代表一个使用系统的真实用户。
创建新用户时将修改一下几个文件：
* `/etc/passwd`： 用户账户的详细信息在此文件中更新。
* `/etc/shadow`： 用户账户密码在此文件中更新。
* `/etc/group`： 新用户群组的详细信息在此文件中更新。
* `/etc/gshadow`： 新用户群组密码在此文件中更新。
```

```
mageshm:x:506:507:2g Admin - Magesh M:/home/mageshm:/bin/bash
7 个字段的详细信息如下。

用户名 （magesh）： 已创建用户的用户名，字符长度 1 个到 12 个字符。
密码（x）：代表加密密码保存在 `/etc/shadow 文件中。
**用户 ID（506）：代表用户的 ID 号，每个用户都要有一个唯一的 ID 。UID 号为 0 的是为 root 用户保留的，UID 号 1 到 99 是为系统用户保留的，UID 号 100-999 是为系统账户和群组保留的。
**群组 ID （507）：代表群组的 ID 号，每个群组都要有一个唯一的 GID ，保存在 /etc/group文件中。
**用户信息（2g Admin - Magesh M）：代表描述字段，可以用来描述用户的信息（LCTT 译注：此处原文疑有误）。
**家目录（/home/mageshm）：代表用户的家目录。
**Shell（/bin/bash）：代表用户使用的 shell 类型。
```

### **防火墙配置**

配置文件位置： /etc/sysconfig/iptables

-A FORWARD -j REJECT --reject-with icmp-host-prohibited

**```/etc/init.d```** 目录说明：存放系统服务管理脚本，用service 命令可以执行init.d目录中相应的脚本

**```/proc``` ** 目录是一种文件系统，存储的是当前内核运行状态的一系列特殊文件，用户可以查看有关系统硬件和运行进程的一些信息，甚至可以改变他们。

**```/var```** 存储各种变化的文件

### **SSH免密登陆**



A电脑：192.168.95.12

B电脑：192.168.95.10

设置hosts：

vim /etc/hosts

#添加

192.168.95.12 serverA

192.168.95.10 serverB

serverA服务器使用ssh-keygen工具生成公私钥对：

ssh-keygen命令，输入三个回车：

![image-20201010145641228](D:\JeffreyLearn\jeffrey\jeffrey-docs\image\media\linux命令使用\image-20201010145641228.png)

到~/.ssh目录下可以看到id_rsa、 id_rsa.pub两个文件

将id_rsa.pub复制到serverB：

使用ssh-copy-id -i id_rsa.pub root@serverB命令操作：

![image-20201010150720012](D:\JeffreyLearn\jeffrey\jeffrey-docs\image\media\linux命令使用\image-20201010150720012.png)

到serverB服务器下查看~/ .ssh目录，发现多了authorized_keys，这个文件的内容和serverA中的id_rsa.pub是相同的

此时已经可以serverA已经可以免密登陆到serverB，可以测试下：

ssh serverB,发现已经不需要输入密码了。

使用同样的操作使得serverB免密登陆到serverA，这样两个服务器可以相互免密登陆



使用ssh登陆本机的时候，发现也需要密码登陆，按照上面的操作，将生成的公钥复制到本机下：

ssh-copy-id -i .ssh/id_rsa.pub root@serverA

查看本机的.ssh/authorized_keys 文件，已经多了本机的公钥，再次ssh登陆本机，发现已经不需要密码登陆了。



```
# 查看文件系统磁盘使用情况df(disk free)
df -h
# 显示目录或文件的大小du(disk usage)
du
```



## 查看命令帮助信息

```
# 查看内部命令帮助，外部命令只能用man或info查看 
help(选项)(参数)
-s: 输出短格式的帮助信息。仅包括命令格式。

# 判断一个命令是内部命令还是外部命令的方法，如内部命令，type会明确指出；如外部命令，会给出命令的执行路径
type 

# 查看一个命令执行什么功能
whatis

#查看info格式的帮助命令；
info(选项)(参数)

#查找并显示命令的绝对路径
which(选项)(参数)

#whereis 命令只能用于程序名的搜索，而且只搜索二进制文件（参数-b）、man 说明文件（参数-m）和源代码文件（参数-s）。如果省略参数，则返回所有信息
whereis -b pwd

# man 命令是 Linux 下的帮助指令
man
```

## 用户管理相关命令

参考http://c.biancheng.net/view/3042.html

```
# 创建用户组
groupadd(选项)(参数)
-g：指定新建工作组的id；
-r：创建系统工作组，系统工作组的组ID小于500；
-K：覆盖配置文件“/ect/login.defs”；
-o：允许添加组ID号不唯一的工作组。
groupadd -g 344 inspur

# 修改用户组
groupmod(选项)(参数)
-g<群组识别码>：设置欲使用的群组识别码；
-o：重复使用群组识别码；
-n<新群组名称>：设置欲使用的群组名称。
参数：组名：指定要修改的工作的组名。
groupmod  jeffrey -n inspur

# 删除用户组
groupdel(参数)
groupdel inspur

# 添加用户，不指定用户组时会创建同名用户组
useradd

# 新建用户加入inspur组
 useradd jeffrey01 -g inspur
 
# 修改用户的基本信息，将用户jeffrey02添加到用户组inspur;并查看用户组inspur下的用户
usermod(选项)(参数)
-c<备注>：修改用户帐号的备注文字；
-d<登入目录>：修改用户登入时的目录；
-e<有效期限>：修改帐号的有效期限；
-f<缓冲天数>：修改在密码过期后多少天即关闭该帐号；
-g<群组>：修改用户所属的群组；
-G<群组>；修改用户所属的附加群组；
-l<帐号名称>：修改用户帐号名称；
-L：锁定用户密码，使密码无效；
-s<shell>：修改用户登入后所使用的shell；
-u<uid>：修改用户ID；
-U:解除密码锁定。
usermod jeffrey02 -g inspur
grep inspur /etc/group
grep 727 /etc/passwd

# 删除用户

```

## linux目录

```
Linux 的文件系统层次标准（FHS）
/bin目录：存放所有核心系统二进制文件
/boot:存储了计算机启动所需要的东西，其中最重要的是引导程序和内核，
/dev:存储类似文件的对象来表示被系统识别为设备的各种东西。
/etc目录：用来存放系统的主要配置文件，
/home:用户个人文件所在的位置
/lib:系统运行的依赖库
/media:访问像 USB 闪存驱动器或摄像机这样的可移动媒体
/proc:动态显示系统数据的虚拟文件系统
/tmp:用户放置缓存等临时信息。
```

## 配置相关

```
#设置系统编码
vim /etc/locale.conf
添加
LANG="zh_CN.UTF-8"
使用source /etc/locale.conf，执行命令使配置生效
查看系统编码命令：
locale

#设置静态IP地址
查看网卡信息并获取网卡名称：
ifconfig
修改网卡配置文件：
vim /etc/sysconfig/network-scripts/ifcfg-ens33

BOOTPROTO=static #这里讲dhcp换成static
ONBOOT=yes #将no换成yes
#新增（黄色为举例数据，具体视部署情况而定）
IPADDR=192.168.240.240 #静态IP
GATEWAY=192.168.240.2 #默认网关
NETMASK=255.255.255.0 #子网掩码

重启网络服务，命令：systemctl restart network.service

```



