package com.meikinfo.erukatestsecurity.erukatestsecurity.service;

import com.meikinfo.erukatestsecurity.erukatestsecurity.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author swh
 * @create: 2019-12-30 10:55
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 用户登录
     * @return
     */
    String login(User user);
}
