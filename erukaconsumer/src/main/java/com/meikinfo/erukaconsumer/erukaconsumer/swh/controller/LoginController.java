package com.meikinfo.erukaconsumer.erukaconsumer.swh.controller;

import com.meikinfo.erukaconsumer.erukaconsumer.swh.pojo.User;
import com.meikinfo.erukaconsumer.erukaconsumer.swh.server.LoginInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户登录
 *
 * @author swh
 * @create: 2020-03-03 11:27
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    LoginInterface loginInterface;

    @PostMapping("/login")
    public String login(User user){
        log.info("姓名：{}密码:"+user.getUsername(),user.getPassword());
        String token =  loginInterface.login(user);
        log.info("token值："+token);
        return token;
    }

    @GetMapping("/user/test")
    public User test(){
        return loginInterface.test();
    }
}
