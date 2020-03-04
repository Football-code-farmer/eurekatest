package com.meikinfo.erukatestsecurity.erukatestsecurity.controller;

import com.meikinfo.erukatestsecurity.erukatestsecurity.pojo.User;
import com.meikinfo.erukatestsecurity.erukatestsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swh
 * @create: 2020-03-02 16:49
 */
@RestController
@Slf4j
public class UserLoginController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/jwt/login")
    public String login(@RequestBody User user) {
        log.info("姓名：{}密码:" + user.getPassword(),user.getUsername());
        String token = userService.login(user);
        log.info("token值：{}",token);
        return token;
    }


    @GetMapping("/test")
    public UserDetails test(String username){
        return userService.loadUserByUsername(username);
    }
}
