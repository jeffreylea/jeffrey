+ **可变长参数**

  方法test(String... strings),可以接受多个字符串，参数类型相当于字符串数组。当不传参数时，字符串数组为空。传参数时可以直接使用逗号分隔的方式传入多个参数，也可以已数组的形式传入。

+ **spring的default-lazy-init参数** 

  在[spring](http://lib.csdn.net/base/javaee)的配置中的根节点上有个  default-lazy-init="true"配置；此参数表示延时加载，即在项目启动时不会实例化注解的bean，除非启动项目时需要用到，未实例化的注解对象在程序实际访问调用时才注入调用；pring在启动的时候，default-lazy-init参数默认为false，会默认加载整个对象实例图，从初始化ACTION配置、到 service配置到dao配置、乃至到[数据库](http://lib.csdn.net/base/mysql)连接、事务等等。这样可以减少web服务器在运行时的负担，但是对于开发者来说无疑是效率极低的一个设置了。 实际开发中可将default-lazy-init属性设置为true，可大大减少项目启动时间

+ 循环依赖

  三级缓存->defalutSingleton-

  set-可以解决，构造方法-没有办法解决循环依赖问题；实例化和初始化分开处理，提前暴露对象
  
+ java安全编码网站

  https://wiki.sei.cmu.edu/confluence/display/java/SEI+CERT+Oracle+Coding+Standard+for+Java

