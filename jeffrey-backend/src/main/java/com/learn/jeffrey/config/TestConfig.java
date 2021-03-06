package com.learn.jeffrey.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author lijianfei
 *         <p>
 *         配置类测试
 *         </p>
 *         2020年3月2日
 */
@Configuration
@Slf4j
public class TestConfig {

	public TestConfig() {
		log.info("TestConfiguration容器启动初始化");
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		// 直接初始化HikariDataSource
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		ds.setPassword("inspur123!@#");
		return ds;
	}

}
