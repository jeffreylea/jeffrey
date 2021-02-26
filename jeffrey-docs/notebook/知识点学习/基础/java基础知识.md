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
在运行时，对于任意一个类，都能够知道这个类的所有属性和方法，给定类的名字，就能通过反射获得类的所有信息，动态获取信息以及动态调用方法的功能就是java中的反射机制。
```

