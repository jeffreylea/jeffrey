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

+ BeanFactoryPostProcessor

  初始化bean时对外暴露的接口

+ spring 钩子方法和接口



+ 钩子方法

  钩子方法源于设计模式中模板方法（Template Method）模式，模板方法模式的概念为：在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的某些步骤。其主要分为两大类：模版方法和基本方法，而基本方法又分为：抽象方法（Abstract Method），具体方法（Concrete Method），钩子方法（Hook Method）。

  由抽象类声明并且实现，子类也可以选择加以扩展。通常抽象类会给出一个空的钩子方法，也就是没有实现的扩展。它和具体方法在代码上没有区别，不过是一种意识的区别；而它和抽象方法有时候也是没有区别的，就是在子类都需要将其实现的时候。而不同的是抽象方法必须实现，而钩子方法可以不识闲。也就是说钩子方法为你在实现某一个抽象类的时候提供了可选项，相当于预先提供了一个默认配置。

  参考：http://www.mamicode.com/info-detail-2581714.html


