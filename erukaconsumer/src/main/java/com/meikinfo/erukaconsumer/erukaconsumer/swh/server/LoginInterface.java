package com.meikinfo.erukaconsumer.erukaconsumer.swh.server;

import com.meikinfo.erukaconsumer.erukaconsumer.swh.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户登录接口
 *
 * @author swh
 * @create: 2020-03-03 11:28
 */
@FeignClient(value = "SECURITY")
public interface LoginInterface {

    @PostMapping("/jwt/login")
    public String login(@RequestBody User user);

    @GetMapping("/test")
    public User test();
}
