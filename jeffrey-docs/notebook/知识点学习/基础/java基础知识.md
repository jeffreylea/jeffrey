+ 

+ 可变长参数**

  方法test(String... strings),可以接受多个字符串，参数类型相当于字符串数组。当不传参数时，字符串数组为空。传参数时可以直接使用逗号分隔的方式传入多个参数，也可以已数组的形式传入。

+ **spring的default-lazy-init参数** 

  在[spring](http://lib.csdn.net/base/javaee)的配置中的根节点上有个  default-lazy-init="true"配置；此参数表示延时加载，即在项目启动时不会实例化注解的bean，除非启动项目时需要用到，未实例化的注解对象在程序实际访问调用时才注入调用；pring在启动的时候，default-lazy-init参数默认为false，会默认加载整个对象实例图，从初始化ACTION配置、到 service配置到dao配置、乃至到[数据库](http://lib.csdn.net/base/mysql)连接、事务等等。这样可以减少web服务器在运行时的负担，但是对于开发者来说无疑是效率极低的一个设置了。 实际开发中可将default-lazy-init属性设置为true，可大大减少项目启动时间

+ 循环依赖

  三级缓存->defalutSingleton-

  set-可以解决，构造方法-没有办法解决循环依赖问题；实例化和初始化分开处理，提前暴露对象
  
+ java安全编码网站

  https://wiki.sei.cmu.edu/confluence/display/java/SEI+CERT+Oracle+Coding+Standard+for+Java

+ **注解**

4个元注解，用来对注解类型进行注解的注解类

@Target：描述注解的使用范围，被该注解修饰的注解可以使用在什么地方



@Retention:描述注解保留的时间范围，被修饰的注解在在它所修饰的类中可以保留到什么时候



@Documented:在使用javadoc工具为类生成帮助文档时是否保留注解信息



@Inherited:被它修饰的注解具有继承性，某个类使用了该注解修饰的注解，子类自动具有该注解



+ Map

  ```
  int size();boolean isEmpty();boolean containsKey(Object key);boolean containsValue(Object value);V get(Object key);V put(K key, V value);V remove(Object key);void putAll(Map<? extends K, ? extends V> m);void clear();Set<K> keySet(); Collection<V> values();Set<Map.Entry<K, V>> entrySet();
  interface Entry<K,V>{
  K getKey();V getValue();V setValue(V value); boolean equals(Object o);int hashCode();
  }
  用键值对存储数据
  ```

+ AbstractMap

  ```
  抽象类，Map接口的骨架实现，最小化实现了此接口提供的抽象函数；骨架实现在实现类和接口之间构建了一层抽象，其目的就是为了复用一些通用的函数和方便扩展；Java的Collection框架中基本都遵循了这一规定，例如List接口拥有骨架实现AbstractList、Set接口拥有骨架实现AbstractSet等
  ```

+ Collection

  ```
  集合顶层接口；int size();boolean isEmpty();boolean contains(Object o);Iterator<E> iterator();Object[] toArray();boolean add(E e);boolean remove(Object o);boolean containsAll(Collection<?> c);void clear();boolean equals(Object o);int hashCode();
  ```

  

+ AbstractCollection

  ```
  抽象接口；Collection接口的抽象实现。骨架实现，最小化实现了Collection接口。
  ```

  

+ List

  ```
  list接口，继承Collection接口，是Collection接口的子接口；int size();E get(int index);E set(int index, E element);
  List 是一个有序集合，允许元素重复
  List是集合类的接口，子类有ArraryList 和LinkList
  ```

+ Set

  ```
  Set接口；继承Collection接口，是collection接口的子接口；Set 是一个无序集合；
  list和set有共同的父类Collection；set中不能重复，list可以重复；Set具有与Collection完全一样的接口，因此没有任何额外的功能；不保存重复的元素
  ```

+ Queue

```
 queue接口;继承Collection接口，是collection接口的子接口；
 Queue用于模拟"队列"这种数据结构；模拟队列这种元素，FIFO的特点；队列不允许随机访问。
 在尾部添加：boolean add(E e);添加失败时报错boolean offer(E e);添加失败时返回false
 删除并返回头部：E remove();E poll();
 获取头部元素: E element();E peek();
```

+ AbstractList

  ```
  抽象类，list接口的抽象实现，继承了AbstractCollection类；list接口的骨架实现；
  ```

+ ArrayList

  ```
  继承了AbstractList，实现了list接口
  ```

+ AbstractSet

  ```
  抽象类，继承了AbstractCollection类，实现了set接口，
  ```

+ AbstractQueue

   ```
  抽象类，继承了AbstractCollection类，实现了queue接口
  ```

+ HashSet

  ```
  继承AbstractSet，实现Set接口；
  ```

+ java反射的作用与原理

```
在运行时，对于任意一个类，都能够知道这个类的所有属性和方法，给定类的名字，就能通过反射获得类的所有信息，动态获取信息以及动态调用方法的功能就是java中的反射机制。Jdbc就是典型的反射，Class.forName('com.mysql.jdbc.Driver.class')
反射的实现方式：
获取class对象，Class.forName(“类的路径”)；类名.class，对象名.getClass() ，基本类型的包装类，可以调用包装类的Type属性来获得该包装类的Class对象	
实现java反射的类：
Class：表示正在运行的java应用程序中类和接口，所有获取对象的信息都要Class类来实现，
Filed：提供有关类和接口属性信息，以及对他的动态访问权限
Constructor：提供关于类的单个构造方法的信息以及它的访问权限
Method：提供类或接口中某个方法的信息
Class：
getDeclaredClasses()；Class.getModifiers()：获取当前Class类的修饰符编码(int 类型)；getDeclaredFields()
反射机制优缺点：
能够运行时获取类的实例，提高灵活性；与动态编译结合
使用反射性能低，需要解析字节码，将内存中的对象进行解析；解决方案：通过setAccessible(true)
关闭JDK的安全检查来提升反射速度；多次创建一个类的实例时，有缓存会快很多；ReflectASM工具类，通过字节码生成的方式加快反射速度；相对不安全，破坏了封装性

```

+ 创建对象的几种方式

  ```
  new 创建
  通过反射机制创建：newInstance()
  clone
  通过序列化机制
  ```

+ List、Set/map的区别

  ```
  List(对付顺序的好帮手)： List接口存储一组不唯一（可以有多个元素引用相同的对象），有序
  的对象
  Set(注重独一无二的性质): 不允许重复的集合。不会有多个元素引用相同的对象。
  Map(用Key来搜索的专家): 使用键值对存储。Map会维护与Key有关联的值。两个Key可以引
  用相同的对象，但Key不能重复，典型的Key是String类型，但也可以是任何对象。
  ```

+ Object的常用的方法

  ```
  clone（）：
  保护方法，实现对象的浅复制，只有实现了 Cloneable 接口才可以调用该方法，否则抛出
  CloneNotSupportedException 异常，深拷贝也需要实现 Cloneable，同时其成员变量为引用类型
  的也需要实现 Cloneable，然后重写 clone 方法
  
  ```

+ ThreadLocal

  ```
  set(T value)设置当前线程的线程局部变量的值。get()该方法返回当前线程所对应的线程局部变量。public void remove()将当前线程局部变量的值删除，目的是为了减少内存的占用 initialValue()返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的
  ThreadLocal如何做到为每一个线程维护变量的副本呢？
  在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本
  
  ```

+ ArrayList

  ```
  ArrayList是动态数组；我们常说的数组是固定长度的数组；
  java集合框架中的存放相同类型的数组元素，是一种变长的集合类，基于定长数组实现，当加
  入数据达到一定程度后，会实行自动扩容，即扩大数组大小
  底层是使用数组实现，添加元素
  高并发的情况下，线程不安全
  多个线程同时操作 ArrayList，会引发不可预知的异常或错误
  ```

+ hashTable和hashmap

  ```
  HashTable继承了Dictionary，HashMap继承了AbstractMap，他们都实现了Map接口;HashTable中的大部门修饰方法使用synchronized修饰，是线程安全的；hashtable中的key和value不能为null，hashMap允许；HashTable初始长度为11，2n+1增长，HashMap初始长度为16，
  HashMap 与 ConcurrentHashMap 的异同
  都是 key-value 形式的存储数据
  HashMap 是线程不安全的，ConcurrentHashMap 是 JUC 下的线程安全的
  红黑树的特征
  每个节点是黑色或红色，根节点是黑色
  
  ```

+  Excption与Error包结构

  ```
  检查类异常，运行时异常，错误
  exception中除了运行时异常
  ```

+ 实现多线程的几种方式

  ```
  1、继承Thread类
  2、runable接口
  3、线程池的方式创建
  停止线程：停止标志、使用stop强制退出，interrupt方法中断线程
  notify可能导致死锁，notifyAll不会，任何时候只有一个线程
  start()方法被用来启动新创建的线程，而且start()内部调用了run()方法，这和直接调用run()方法的
  效果不一样。当你调用run()方法的时候，只会是在原来的线程中调用，没有新的线程启动，start()
  方法才会启动新线程。
  ```

+ volatile

  ```
  保证不同线程对这个变量操作时的可见性，即一个线程修改了变量的值，其他线程都可见；volatile关键字会强制将修改的值立即写入主存；禁止进行指令重排序；volatile 不是原子性操作；使用volatile 一般用于 状态标记量 和 单例模式的双检锁
  ```

+ SynchronizedMap和ConcurrentHashMap有什么区别

```
SynchronizedMap()和Hashtable一样，实现上在调用map所有方法时，都对整个map进行同步。
而ConcurrentHashMap的实现却更加精细，它对map中的所有桶加了锁。所以，只要有一个线程
访问map，其他线程就无法进入map，而如果一个线程在访问ConcurrentHashMap某个桶时，其
他线程，仍然可以对map执行某些操作。
所以，ConcurrentHashMap在性能以及安全性方面，明显比Collections.synchronizedMap()更加
有优势。同时，同步操作精确控制到桶，这样，即使在遍历map时，如果其他线程试图对map进行
数据修改，也不会抛出ConcurrentModificationException。
```

+ CAS 的原理

  ```
  CompareAndSwap，比较并交换，主要通过处理器的指令来保证操作的原子性，包含三个操作数：变量内存地址、旧的预期值、准备设置的新值，当执行CAS指令时，只有当V等于A时，才会用B去更新V的值，否则就不会执行更新操作。。
  ```

+ JUC并发包

  ```
  JUC是java.util.concurrent的简称，是java提供的并发包；java.util.concurrent在这个包下面主要是线程池、并发集合以及一些并发工具类；线程池相关主要围绕Executor框架来构建；java.util.concurrent.atomic这个包下面是一些原子操作类，算是并发辅助工具类，基本实现依赖于CAS；java.util.concurrent.locks这个包下面提供了锁相关的类。
  Future:提供了可以获取异步执行结果的方法。
  boolean cancel(boolean mayInterruptIfRunning);//取消
  boolean isCancelled();
  boolean isDone();
  V get() throws InterruptedException, ExecutionException;
  V get(long timeout, TimeUnit unit)
  Callable
  声明了一个名称为call()的方法，同时这个方法可以有返回值V，也可以抛出异常
  关于Callable和Future的使用一般情况下都是结合我们的线程池来使用的。
  Executor接口：
  Executor接口是线程池实现的顶级接口，它和spring中的Beanfactory所承担的角色差不多，就是提供顶级的功能约束，具体实现交于不同的子类实现。
  void execute(Runnable command);
  ExecutorService接口：
  继承于executor接口，
  void shutdown();List<Runnable> shutdownNow();boolean isShutdown(); boolean isTerminated();boolean awaitTermination(long timeout, TimeUnit unit)
          throws InterruptedException;<T> Future<T> submit(Callable<T> task);<T> Future<T> submit(Runnable task, T result) Future<?> submit(Runnable task);
  Executors工具类提供了创建四种类型的线程池：
  public static ExecutorService newFixedThreadPool(int nThreads);创建一个定长线程池，可控制最大并发数，超出的线程会在队列中等待，
  public static ExecutorService newCachedThreadPool();创建一个可缓存线程池，线程池为无限大，当线程池长度超过处理需要，可灵活回收空闲线程，
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize)；创建一个定长线程池，支持定时及周期性任务执行。
  public static ExecutorService newSingleThreadExecutor（）；创建一个单线程的线程池，它只会用唯一的工作线程执行任务，保证所有任务按照指定顺序执行。
  ThreadPoolExecutor：继承了AbstractExecutorService，AbstractExecutorService实现了ExecutorService接口，也就是ThreadPoolExecutor实现了ExecutorService接口；
  在《阿里巴巴java开发手册》中指出：线程资源必须通过线程池提供，不允许在应用中自行显示的创建线程，一方面是线程创建更加规范，另一方面，线程的细节管理交给线程池，优化资源开销，线程池不允许使用executors创建，必须通过ThreadPoolExecutor方式，，这一方面是由于jdk中Executor框架虽然提供了如newFixedThreadPool()、newSingleThreadExecutor()、newCachedThreadPool()等创建线程池的方法，但都有其局限性，不够灵活；另外由于前面几种方法内部也是通过ThreadPoolExecutor方式实现，使用ThreadPoolExecutor有助于大家明确线程池的运行规则，创建符合自己的业务场景需要的线程池，避免资源耗尽的风险。
  ThreadPoolExecutor构造函数：
      public ThreadPoolExecutor(int corePoolSize,
                                int maximumPoolSize,
                                long keepAliveTime,
                                TimeUnit unit,
                                BlockingQueue<Runnable> workQueue,
                                ThreadFactory threadFactory,
                                RejectedExecutionHandler handler)
  corePoolSize：指定线程池中的线程数量，他的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列去；
  maximumPoolSize：指定了线程池中最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
  keepAliveTime：当线程池中空闲线程数量超过corePoolSize，多余的线程在多长时间
  unit:keepAliveTime的单位
  workQueue：任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
  threadFactory:线程工厂，用于创建线程，一般用默认即可；
  ```
  
  + ThreadFactory接口
  
  ```
  在juc包下，这个接口只有一个方法，Thread newThread(Runnable r);
  ```

