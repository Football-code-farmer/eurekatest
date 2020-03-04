package com.meikinfo.erukatestsecurity.erukatestsecurity.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 用户登录保存处理
 * @author swh
 * @create: 2019-12-29 22:06
 */
@Configuration
public class MyErrorPage implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        //设施错误页面跳转
        ErrorPage errorPage = new ErrorPage(HttpStatus.FORBIDDEN,"/403");
        registry.addErrorPages(errorPage);
    }
}
