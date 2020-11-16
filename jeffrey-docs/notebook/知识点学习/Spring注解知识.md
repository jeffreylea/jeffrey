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
  
  