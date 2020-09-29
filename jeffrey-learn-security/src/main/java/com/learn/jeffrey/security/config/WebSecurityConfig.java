package com.learn.jeffrey.security.config;

import com.learn.jeffrey.security.repo.mapper.UserMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/17 10:08
 **/
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserMapper userMapper;
    private Log log = LogFactory.getLog(getClass());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/hello").hasAuthority("p1")
                .antMatchers("/hello2").permitAll()
                //.antMatchers("/hello2").denyAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic().and()
        ;

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        //manager.createUser(User.withUsername("jeffrey").password(new BCryptPasswordEncoder().encode("admin")).authorities("p1").build());
        //manager.createUser(User.withUsername("jeffrey").password("admin").authorities("user").build());
        return new DBUserDetailsService();
    }

    public class DBUserDetailsService implements UserDetailsService{
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            com.learn.jeffrey.security.repo.po.User user = userMapper.selectByPrimaryKey(1);
            return new User(user.getUsername(),user.getPassword(),true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
        }
    }

    /**
     * 密码加密器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder();
    }

    private class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

        @Override
        public String encode(CharSequence rawPassword) {
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            if (!rawPassword.equals(encodedPassword)) {
                log.info("密码不正确");
                return false;
            }
            return true;
        }
    }

}
