### java内存结构

**程序计数器：**一块内存区域，是当前线程执行字节码的行号指示器，存储的是下一个所要执行命令的地址，不会发生内存溢出错误。每个线程都有一个独立的程序计数器，是线程私有的，互不影响，独立存储。

如果线程执行 Java 方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址；如果执行的是 Native 方法，计数器值为Undefined

**虚拟机栈**为JVM执行java方法服务，**本地方法栈**为JVM执行Native方法服务。两者都是栈，每个线程有独立的栈空间，每个方法在执行时都会创建一个栈帧。栈帧中存储了局部变量、操作数栈、动态链接和方法出口。



### **JVM参数**

JVM参数分类：

标准参数（-），所有的JVM实现都必须实现这些参数的功能，而且向后兼容；

非标准参数（-X），默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容；

非Stable参数（-XX），此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用，一般JVM调优使用的即此类参数

+ “-” 开头的参数，标准参数

  -jar：指定已jar包的形式运行应用程序。使用这种方式，jar包中的mainfest文件中必须声明初始加载的Main-class，同时Main-class必须有public static void main(String[] args)方法。

  -client：设置JVM使用client模式，常用于客户端应用程序或者调试，特点是启动速度快，但运行时性能和内存效率不高。

  -server：选择 "server" VM，默认 VM 是 server,因为是在服务器类计算机上运行。

  -version: 经常使用输出产品版本并退出,

  -showversion:和-version的区别在于输出版本信息后不退出，而是继续执行。

  -classpath <目录和 zip/jar 文件的类搜索路径> ，用 : 分隔的目录, JAR 档案 和 ZIP 档案列表, 用于搜索类文件。使用-classpath后jvm将不再使用CLASSPATH中的类搜索路径，如果两者都没有设置，jvm使用当前路径(.)作为类搜索路径。jvm搜索类的方式和顺序为：Bootstrap，extension，user。bootstrap中的路径是jvm自带的jar文件或者zip文件，jvm首先搜索这些包文件，用System.getProperty("sun.boot.class.path")可得到搜索路径。Extension是位于JRE_HOME/lib/ext目录下的jar文件，jvm在搜索完Bootstrap后就搜索该目录下的jar文件，用System.getProperty("java.ext.dirs")可得到搜索路径。User搜索顺序为当前路径.、CLASSPATH、-classpath，jvm最后搜索这些目录，用System.getProperty("java.class.path")可得到搜索路径。

  -verbose:[class|gc|jni]  启用详细输出

  -verbose:class 输出jvm载入类的相关信息，当jvm报告说找不到类或者类冲突时可此进行诊断。

  -verbose:gc 输出每次GC的相关情况。

  -verbose:jni 输出native方法调用的相关情况，一般用于诊断jni调用错误信息。

   -D<名称>=<值>    设置系统属性

+ “-X” 开头的参数（-X 选项是非标准选项, 如有更改, 恕不另行通知。），一般很少使用

  ---

   -Xmixed    混合模式执行 (默认)，将解释模式与编译模式进行混合使用，由jvm自己决定，这是jvm默认的模式，也是推荐使用的模式。

   -Xint      仅解释模式执行,所有的字节码将被直接执行，而不会编译成本地码，这会降低运行速度，通常低10倍或更多。

  -Xcomp  编译模式，和-Xint相反，JVM在第一次使用时会把字节码编译成本地代码，从而带来最大程度的优化。然而很多应用在使用-Xcomp也会有一些性能损失，当然这比-X使用-Xint损失的少，原因是-Xcomp没有让JVM启用JIT编译器的全部功能。JIT编译器可以对是否需要编译做判断，如果所有代码都进行编译的话，对于一些只执行一次的代码就没有意义了

  ---

  

+ “-XX” 开头的参数，非稳定参数，也是非标准参数，主要用于jvm调优和debug操作，有两种使用方式，一种是Boolean类型，另一种是非Boolean类型。

  打印所有-XX参数:java -XX:+PrintFlagsFinal -version

  **-XX:PrintFlagsInitial**,只是展示了第三列为“=”的数据（也包括那些被设置其他值的参数）。

  **-XX:+PrintCommandLineFlags** 

  查看

  + boolean类型

    格式：-XX：[+ -]  +表示启用，-表示禁用

    如：-XX:+DisableExplicitGC 表示启用这个命令(禁用手动调用gc操作生效)，也就是说调用System.gc()无效
    DisableExplicitGC：命令表示禁用手动调用gc操作；System.gc():表示通知垃圾回收器去做垃圾回收

  + 非 boolean型

    格式：-XX：= 表示属性的值为

    如：-XX:NewRatio=1 表示新生代和老年代的比值



推荐参考资料：

[JVM基础系列](https://www.cnblogs.com/chanshuyi/p/jvm_serial_00_why_learn_jvm.html)

###  The ClassFile Structure

```
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

jps命令：显示当前所有java进程pid

**双亲委派模型**  
在类加载的时候，系统会首先判断当前类是否被加载过，已经加载的类会直接返回，否则会尝试加载，加载时首先会把该请求委派父类加载器处理，因此所有的请求都应该传到最顶层的启动类加载器，当父类加载器无法处理时，才自己处理。

**java默认提供的3个ClassLoader**   
1、启动类加载器：bootStrap classloader负责加载的jar包由以下方式得到。

```
URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }

        System.out.println(System.getProperty("sun.boot.class.path"));//系统属性sun.boot.class.path所得知
```
2、扩展类加载器：extension ClassLoader   
负责加载Java的扩展类库，默认加载JAVA_HOME/jre/lib/ext/目下的所有jar

3、应用程序加载器：Appclassloader  
负责加载应用程序classpath目录下的所有jar和class文件