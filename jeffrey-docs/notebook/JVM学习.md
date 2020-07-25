# The ClassFile Structure

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