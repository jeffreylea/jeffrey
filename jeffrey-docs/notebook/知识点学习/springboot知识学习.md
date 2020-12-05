+ springboot中启动时增加配置类
  在程序启动时就应该执行的一些操作可以增加一个配置类，方便程序运行。注解@configuration用来定义配置类，配置spring容器上下文。该注解是配置在类上，具体使用如下面这个例子，在程序启动时就会执行。

```
@Configuration
@Slf4j
public class TestConfig {
    public TestConfig() {
        log.info("TestConfiguration容器启动初始化");
    }
}
```

@Bean 注解标记在方法上，方法的返回值相当于向容器中注入一个bean。

```
@Bean
public DataSource DataSource() {
    // 直接初始化HikariDataSource
    HikariDataSource ds = new HikariDataSource();
    ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
    ds.setUsername("root");
    ds.setPassword("admin");
    return ds;
}
```

在使用时直接注入就可以使用了

```
@Autowired
private DataSource DataSource;
```

+ springboot 激活profile的几种方式

  