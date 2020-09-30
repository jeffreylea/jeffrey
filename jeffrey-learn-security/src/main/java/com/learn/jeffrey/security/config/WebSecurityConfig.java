package com.learn.jeffrey.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/17 10:08
 **/
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/hello").hasAuthority("p1")
                .antMatchers("/hello2","/oauth/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic().and()
        ;

    }

    // authentication对象在OAuth2认证服务中要是用，提前放入IOC容器中
    @Bean
    AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
