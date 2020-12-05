+ FeignClient注解

  name：指定FeignClient的名称，如果项目使用了Ribbon，name属性会作为微服务的名称，用于服务发现
  
  url:一般用户测试，name和url同时配置的话，以url为准
  
  feign调用日志打印：
  
  ```
  //feignclient配置：configuration = FeignClientConfig.class
  @Configuration
  public class FeignClientConfig {
      @Bean
      public Logger.Level feignLoggerLevel() {
          return Logger.Level.FULL;
      }
  }
  // 配置文件中添加配置
  logging:
    level:
      com.ifit.iot.devicemanager.feign.service.UAAFeignService: debug
  ```
  
+ BeanFactory

  访问Spring Bean容器的根接口

```
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 查看是否包含指定名字的Bean
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 查看此BeanFactory中包含的Bean数量
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     */
    int getBeanDefinitionCount();

    /**
     * 返回此BeanFactory中所包含的所有Bean定义的名称
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     */
    String[] getBeanDefinitionNames();

    /**
     * 返回此BeanFactory中所有指定类型的Bean的名字。判断是否是指定类型的标准有两个：a Bean定义。
     * b FactoryBean的getObjectType方法。
     * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
     * 会考虑FactoryBean创建出的Bean
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
     * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
     * 返回结果中，Bean名字的顺序应该和其定义时一样
     */
    String[] getBeanNamesForType(ResolvableType type);

    /**
     * 返回此BeanFactory中所有指定类型（或指定类型的子类型）的Bean的名字。判断是否是指定类型的标准有
     * 两个：a Bean定义，b FactoryBean的getObjectType方法。
     * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
     * 会考虑FactoryBean创建出的Bean
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
     * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
     * 返回结果中，Bean名字的顺序应该和其定义时一样
     */
    String[] getBeanNamesForType(Class<?> type);

    /**
     * 返回此BeanFactory中所有指定类型（或指定类型的子类型）的Bean的名字。判断是否是指定类型的标准有
     * 两个：a Bean定义，b FactoryBean的getObjectType方法。
     * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
     * 会考虑FactoryBean创建出的Bean
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
     * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
     * 返回结果中，Bean名字的顺序应该和其定义时一样
     * 如果Bean是通过FactoryBean创建的，那么只考虑设置了allowEagerInit标志位的Bean。如果
     * 没有设置allowEagerInit标志位，则只考虑FactoryBean的类型
     */
    String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit);

    /**
     * 返回此BeanFactory中所有指定类型（或指定类型的子类型）的Bean的名字。判断是否是指定类型的标准有
     * 两个：a Bean定义，b FactoryBean的getObjectType方法。
     * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
     * 会考虑FactoryBean创建出的Bean
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
     * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
     * 返回结果中，Bean名字的顺序应该和其定义时一样
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回此BeanFactory中所有指定类型（或指定类型的子类型）的Bean的名字。判断是否是指定类型的标准有
     * 两个：a Bean定义，b FactoryBean的getObjectType方法。
     * 只考虑最顶层的Bean，对于嵌套的Bean，即使符合类型也不予考虑
     * 会考虑FactoryBean创建出的Bean
     * 不支持向上或向下查找
     * 不支持查找非配置文件定义的单例Bean
     * 此签名的getBeanNamesForType方法会返回所有Scope类型的Bean，在大多数的实现中，其返回结果和
     * 其重载方法getBeanNamesForType(type, true, true)返回结果一致。
     * 返回结果中，Bean名字的顺序应该和其定义时一样
     * 如果Bean是通过FactoryBean创建的，那么只考虑设置了allowEagerInit标志位的Bean。如果
     * 没有设置allowEagerInit标志位，则只考虑FactoryBean的类型
     * @see BeanFactoryUtils#beansOfTypeIncludingAncestors(ListableBeanFactory, Class, boolean, boolean)
     */
    <T> Map<String, T> getBeansOfType(Class<T> type, boolean includeNonSingletons, boolean allowEagerInit)
            throws BeansException;

    /**
     * 找到所有带有指定注解类型的Bean
     */
    String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);

    /**
     * 找到所有带有指定注解的Bean，返回一个以Bean的name为键，其对应的Bean实例为值的Map
     */
    Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException;

    /**
     * 在指定name对应的Bean上找指定的注解，如果没有找到的话，去指定Bean的父类或者父接口上查找
     */
    <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
            throws NoSuchBeanDefinitionException;

}

```



+ BeanFactoryPostProcessor

  初始化bean时对外暴露的接口

+ spring 钩子方法和接口



+ 钩子方法

  钩子方法源于设计模式中模板方法（Template Method）模式，模板方法模式的概念为：在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的某些步骤。其主要分为两大类：模版方法和基本方法，而基本方法又分为：抽象方法（Abstract Method），具体方法（Concrete Method），钩子方法（Hook Method）。

  由抽象类声明并且实现，子类也可以选择加以扩展。通常抽象类会给出一个空的钩子方法，也就是没有实现的扩展。它和具体方法在代码上没有区别，不过是一种意识的区别；而它和抽象方法有时候也是没有区别的，就是在子类都需要将其实现的时候。而不同的是抽象方法必须实现，而钩子方法可以不识闲。也就是说钩子方法为你在实现某一个抽象类的时候提供了可选项，相当于预先提供了一个默认配置。

  参考：http://www.mamicode.com/info-detail-2581714.html


