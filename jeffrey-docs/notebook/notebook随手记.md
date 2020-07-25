Visio图形模板
http://www.visiocafe.com/

# java开发中出现错误：Value '0000-00-00 00:00:00' can not be represented as java.sql.Timestamp  
数据库字段类型为datatime，为空时默认Value '0000-00-00 00:00:00',在mysql中作为一个特殊值存在。但是在java项目编译的时候会被视为不合法的值，被JVM认为格式不正确。有些地方说datatime的范围是1000-01-01 00:00:00 到 9999-12-31 23:59:59，但0000-00-00 00:00:01也是可以的
解决方案：
在jdbc连接中加上配置：zeroDateTimeBehavior=convertToNull

OAuth2学习：

jenkins学习：
https://jenkins.io/zh/doc/book/blueocean/

消息中间件学习：

appolo学习：
![](../basic/image/无标题.png)

Spring Security Oauth2中获取token接口：oauth/token，这个接口在TokenEndpoint这个类里，
这个类在`org.springframework.security.oauth2.provider.endpoint`这个包下面。
当输入错误的client_id和client_secret，接口返回invalid_client，并没有进入到这个接口的方法里面，所以在进入接口之前应该是首先验证了客户端id和secret的有效性。

# java中枚举的使用

#list.toArray的使用
userIdList.toArray(new String[] {})
userIdList.toArray(new String[0])


    
 ```
 public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
 
 public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
```

# 使用collection标签实现嵌套查询

```
<resultMap id="UserRolesResultMap" type="userShowSubRole">
       <result column="user_id" jdbcType="VARCHAR" property="userId"/>
       <collection property="roles" ofType="roleSimple" >
            <result column="role_id" jdbcType="VARCHAR" property="roleId"/>
            <result column="role_code" jdbcType="VARCHAR" property="roleCode"/>
            <result column="role_name" jdbcType="VARCHAR" property="roleName"/>
            <result column="tenant_id" jdbcType="VARCHAR" property="tenantId"/>
       </collection>    
  </resultMap>
  <select id="listRolesByUserIdsAndTenantId" resultMap = "UserRolesResultMap">
    select ur.user_id, ur.role_id, r.role_code, r.role_name, ur.tenant_id   
    from sys_user_role ur
    left join sys_role r on ur.role_id=r.role_id
    where ur.user_id in     
      <foreach collection="userIds" item="userId" open="(" close=")" separator=",">
        #{userId,jdbcType=VARCHAR}
      </foreach>     
      and r.status = '1'
      <if test="tenantId != null">
        and ur.tenant_id = #{tenantId,jdbcType=VARCHAR}
      </if>
  </select>
  
  <resultMap id="SimpleTenantResultMap" type="tenantSimple">
    <result column="tenant_id" jdbcType="VARCHAR" property="tenantId"/>
    <result column="tenant_name" jdbcType="VARCHAR" property="tenantName"/>
    <result column="status" jdbcType="VARCHAR" property="status"/>
  </resultMap>  
  <resultMap id="UserSimpleTenantsResultMap" type="UserShowSubTenant">
    <result column="user_id" jdbcType="VARCHAR" property="userId"/>
    <collection property="tenants" ofType="tenantSimple" resultMap="SimpleTenantResultMap"/>        
  </resultMap>
  
  <select id="listTenantsByUserIdPg" resultMap="SimpleTenantResultMap">
    select t.tenant_id, t.tenant_name, t.status
    from sys_tenant t
    left join sys_user_tenant ut on t.tenant_id = ut.tenant_id
    where ut.user_id = #{userId,jdbcType=VARCHAR}
    and t.status = 1
  </select>
  <!-- 查询用户所属租户信息 -->
  <select id="listTenantsByUserIdsAndTenantId" resultMap="UserSimpleTenantsResultMap">
    select ut.user_id, t.tenant_id, t.tenant_name, t.status
    from sys_user_tenant ut
    left join  sys_tenant t on t.tenant_id = ut.tenant_id
    where ut.user_id in     
     <foreach collection="userIds" item="userId" open="(" close=")" separator=",">
        #{userId,jdbcType=VARCHAR}
     </foreach>    
     <if test="tenantId != null">
        and ut.tenant_id = #{tenantId,jdbcType=VARCHAR}
      </if>
  </select>
  
传入数组ids  
  <foreach collection="ids" separator="," open="(" close=")" item="id">
         #{id, jdbcType=VARCHAR}
    </foreach>
 传入列表list      
    <foreach collection="list" item="i" separator=",">
    (#{i.relId,jdbcType=VARCHAR}, #{i.elementId,jdbcType=VARCHAR}, #{i.apiId,jdbcType=VARCHAR}, 
      #{i.modifierId,jdbcType=VARCHAR}, #{i.modifyTime,jdbcType=TIMESTAMP})
  </foreach>
```

Caused by: java.lang.ClassNotFoundException: org.springframework.boot.context.properties.ConfigurationPropertiesBean

springboot的版本和springcloud的版本不匹配导致。我的配置如下：删除org.springframework.cloud的配置项目就能正常启动了。

```
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.5.RELEASE</version>
    </parent>
    
    
            <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-oauth2</artifactId>
            <version>2.2.0.RELEASE</version>
        </dependency>
```

白玉搜一搜：
http://www.baiyu.tech/code/list/21
openJDK源码下载：
地址：http://hg.openjdk.java.net，找到对应的jdk版本，点击进去就可以看到里面的详细文件，在左侧有

sl4j使用log.info 打印日志时只有父类属性或子类属性：
在我打印日志时，发现只显示了父类的属性，子类的属性没有打印，使用get方法也能获取子类属相参数。于是检查了两者的不同之处，发现在子类中使用的是@Data注解，但在子类中使用的是@getter,@setter注解，网上查了一番，是@Data这个注解还包含了@toString注解。在子类中也同样使用@data注解时，只打印了子类中的属性，在子类中加上@ToString(callSuper = true)，就可以解决了。

Word转MD工具
http://www.writage.com/

nodejs安装：
下载网址
https://nodejs.org/zh-cn/
断网安装（我开始没有断网时安装失败）。
查看node版本：node -v -》v12.16.3
查看npm版本：npm -v -》6.4.1
可以看到两个版本不对应
npm root -g  查看全局的包的安装路径
在查看全局包安装路径时提示：
npm does not support Node.js v12.16.3
解决：
百度搜索"以往的版本 | Node.js"，可以查出来node与npm版本的对关系
找到v12.16.3对应的版本npm版本6.14.4
1、删除全局模块里面的npm
npm uninstall -g npm
2、  安装npm
npm install -g npm
再次npm -v 查看npm版本：6.14.5，已更改为最新的版本
现在可以使用命令(npm root -g )查看全局的包的安装路径。

# StopWatch计时器的使用
start和stop之间不能嵌套，会报错。
Can't start StopWatch: it's already running

# java.util.concurrent包
这是java的并发工具包，java5开始添加的，它使得并发变成更加容易。
 + Executor接口
  
```
public interface Executor {
    void execute(Runnable command);
}
```

接口里只有一个方法，它的子类和已经实现的类有：
All Known Subinterfaces:
`ExecutorService ， ScheduledExecutorService`
所有已知实现类：
`AbstractExecutorService ， ForkJoinPool ， ScheduledThreadPoolExecutor ， ThreadPoolExecutor`

+ ExecutorService接口

ExecutorService扩展了Executor接口并添加了一些生命周期管理的方法，一个Executor有运行、关闭终止三种状态。它是真正的线程池管理者。

>Executor创建时处于运行状态。当调用ExecutorService.shutdown()后，处于关闭状态，isShutdown()方法返回true。这时，不应该再向Executor中添加任务，所有已添加的任务执行完毕后，Executor处于终止状态，isTerminated()返回true。如果Executor处于关闭状态，往Executor提交任务会抛出unchecked exception RejectedExecutionException


```
public interface ExecutorService extends Executor {

    void shutdown();

    List<Runnable> shutdownNow();

    boolean isShutdown();

    boolean isTerminated();

    boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException;

    <T> Future<T> submit(Callable<T> task);

    <T> Future<T> submit(Runnable task, T result);

    Future<?> submit(Runnable task);

    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
        throws InterruptedException;

    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
        throws InterruptedException;

    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
        throws InterruptedException, ExecutionException;

    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}

```

它的父类子类和已经实现的类有：
`All Superinterfaces:
Executor
All Known Subinterfaces:
ScheduledExecutorService
所有已知实现类：
AbstractExecutorService ， ForkJoinPool ， ScheduledThreadPoolExecutor ， ThreadPoolExecutor`

+ ScheduledExecutorService接口
支持多线程的线程调度
接口中的方法：
```
public interface ScheduledExecutorService extends ExecutorService {

    /**
     * 带延迟时间的调度，只执行一次
     * 调度之后可通过Future.get()阻塞直至任务执行完毕
     */
    public ScheduledFuture<?> schedule(Runnable command,
                                       long delay, TimeUnit unit);

/**
 * 带延迟时间的调度，只执行一次
 * 调度之后可通过Future.get()阻塞直至任务执行完毕，并且可以获取执行结果
 */
public <V> ScheduledFuture<V> schedule(Callable<V> callable,
                                           long delay, TimeUnit unit);

/**
 * 带延迟时间的调度，循环执行，固定频率
 */
  public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit);


/**
 * 带延迟时间的调度，循环执行，固定延迟
 */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                     long initialDelay,
                                                     long delay,
                                                     TimeUnit unit);

}
```
子类和实现类：
```
All Superinterfaces:
Executor ， ExecutorService
所有已知实现类：
ScheduledThreadPoolExecutor
```

+ AbstractExecutorService抽象类

类之间的关系：
```
All Implemented Interfaces:
Executor ， ExecutorService
已知直接子类：
ForkJoinPool ， ThreadPoolExecutor
```
类的方法：
```
public abstract class AbstractExecutorService implements ExecutorService {
//返回给定可调用任务的 RunnableFuture 。
protected <T> RunnableFuture<T>	newTaskFor(Callable<T> callable){};
}
```

# VM-centos网络配置：
从虚拟机的编辑->虚拟网络编辑器中查看网关地址：192.168.95.2
编辑文件：vi /etc/sysconfig/network-scripts/ifcfg-eth0
设置网络:
     DEVICE=eth0
     TYPE=Ethernet
     ONBOOT=yes
     NM_CONTROLLED=yes
     BOOTPROTO=static
     IPADDR=192.168.95.10
     NATMASK=255.255.255.0
     GATEWAY=192.168.95.2
service network restart 重启网络。此时可以检测网络
ping 192.168.95.2;
ping 192.168.95.10;
设置dns:
vi /etc/resolv.conf
nameserver 114.114.114.114
检测是否成功：ping www.baidu.com

1、克隆之后的操作系统需要重新分配物理地址
a、删除/etc/sysconfig/network-scripts/ifcfg-eth0 文件中的物理地址
删除两行：UUID 和物理地址
b、删除文件/etc/udev/rules.d/70-persistent-net.rules
rm -rf /etc/udev/rules.d/70-persistent-net.rules
然后修改主机名，重新配置网络，init6重启

1.	安装mysql 数据库
sudo yum install -y mysql-server
2.	数据库字符集的设置（命令：sudo vi /etc/my.cnf）
在/etc/my.cnf文件中最后加入default-character-set=utf8 
3.	启动 mysql 服务
sudo service mysqld start
sudo service mysqld status  //查看mysql是否启动
sudo chkconfig mysqld on  //设置mysql开机自动启动
4.	设置 mysql 的 root 密码为123456
mysql -uroot -p
Enter password:           //默认密码为空，输入回车即可
mysql>set password for root@localhost=password('inspur');  //密码设置为pwd
mysql>quit

mysql不允许外部主机连接解决方法：
登陆数据库：mysql -uroot -p 
默认密码为空。use mysql;
update user set host='%' where user='root';
service mysqld restart即可解决

卸载mysql:
查找已经安装的mysql：rpm -qa|grep mysql
yum -y remove XX 已经安装的mysql

centos6.5安装mysql5.7过程
-------------
wget dev.mysql.com/get/mysql-community-release-el6-5.noarch.rpm
yum install mysql-community-release-el6-5.noarch.rpm
安装成功后，我们可以看到/etc/yum.repos.d/目录下增加了以下两个文件
mysql-community.repo、mysql-community-source.repo


查看mysql57的安装源是否可用，如不可用请自行修改配置文件（/etc/yum.repos.d/mysql-community.repo）使mysql57下面的enable=1
yum repolist enabled | grep mysql
安装mysql5.7:
yum install mysql-community-server



Linux文件系统：
      |---/bin 存放二进制可执行文件(ls,cat,mkdir),常用的一些命令都在这里。
      |---/etc 存放系统管理和配置文件
root--|---/home 用户文件根目录
      |---/usr 用户存放系统应用程序
      |---/opt 额外安装的可选安装应用程序包
      |---/proc 虚拟文件系统目录，是系统内存的映射，可直接访问这个文件获取系统信息
      |---/root 超级用户的主目录
      |---/sbin 存放二进制可执行文件，只有root才能访问，系统管理员使用的系统级别的命令和程序
      |---/dev 用于存放设备文件
      |---/mnt 系统管理员安装临时文件系统的安装点，系统提供这个目录是让用户临时挂载其他的文件系统。
      |---/boot 存放用于系统引导时使用的各种文件
      |---/lib 存放着和系统运行相关的库文件
      |---/tmp 用于存放各种临时文件，是公用的临时文件存储点
      |---/lib 用于存放运行时需要改变数据的文件，也是某些大文件的溢出区，比方说各种服务的日志文件（系统启动日志等。）等
      |---/lost+found 这个目录平时是空的，系统非正常关机而留下“无家可归”的文件（windows下叫什么.chk）就在这里。
      
centos yum 安装JDK：
---------
1.yum search java| grep jdk
2.yum install java-1.8.0-openjdk

kafka安装：
下载：
https://www.apache.org/dyn/closer.cgi?path=/kafka/2.5.0/kafka_2.12-2.5.0.tgz
解压：
tar -zxvf kafka_2.12-2.5.0.tgz

启动zookeeper: 
./bin/zookeeper-server-start.sh ./config/zookeeper.properties
开另一窗口启动kafka:
./bin/kafka-server-start.sh ./config/server.properties

netstat -ntlp 命令查看端口使用情况，zookeeper默认的2181端口和kafka默认的9092端口。
lsof命令不可用：yum install lsof安装

