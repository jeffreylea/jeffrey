package com.learn.jeffrey.security.config;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/30 9:06
 **/

import com.learn.jeffrey.security.repo.mapper.SysUserMapper;
import com.learn.jeffrey.security.repo.po.SysUser;
import com.learn.jeffrey.security.repo.po.SysUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 适配用户系统
 */
@Component
public class DBUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andAccountEqualTo(username);
        List<SysUser> userList = userMapper.selectByExample(sysUserExample);
        if (CollectionUtils.isEmpty(userList)) {
            return new User(username, "", AuthorityUtils.NO_AUTHORITIES);
        }
        return new User(userList.get(0).getUserName(), userList.get(0).getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}
