package com.learn.jeffrey.security.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/30 9:13
 **/
@Component
public class DBPasswordEncoder implements PasswordEncoder {

    private Log log = LogFactory.getLog(getClass());

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
