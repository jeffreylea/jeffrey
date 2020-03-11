错误：A attempt was made to call the method reactor.retry.Retry.retryMax(I)Lreactor/ret)
原因：spring-boot-admin版本不对应，需要和引入的spring-boot版本一致。
问题：spring-admin-server启动之后，打开网页看不到任何内容，静态资源加载不到。
原因：spring-boot-admin 修改了 starter-web 的默认设置, classpath:/static 是没有作为静态资源导出的，不要把 spring-boot-admin 和你的 其他服务混在一个程序里