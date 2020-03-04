package com.meikinfo.erukatestsecurity.erukatestsecurity.config;

import com.meikinfo.erukatestsecurity.erukatestsecurity.security.JwtAuthenticationEntryPoint;
import com.meikinfo.erukatestsecurity.erukatestsecurity.security.JwtAuthorizationTokenFilter;
import com.meikinfo.erukatestsecurity.erukatestsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author swh
 * @create: 2019-12-27 10:50
 */
@Configuration
@EnableWebSecurity// 这个注解必须加，开启Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//保证post之前的注解可以使用
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    /**
     * 注册UserDetailsService 的bean
     * 获取用户信息
     * @return
     */
    @Bean
    UserDetailsService customUserService(){
        return username -> userService.loadUserByUsername(username);
    }

    /**
     *装载BCrypt密码编码器
     */
    @Bean
    public PasswordEncoder createPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 对用户信息进行验证
     * 设置密码验证格式
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置UserDetailsService
        auth.userDetailsService(customUserService())
        //使用BCrypt进行密码的hash
        .passwordEncoder(createPasswordEncoder());

    }

    /**
     * 忽略路径
     * @return
     */
    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig().getUrl()) {
            http.authorizeRequests().antMatchers(url).permitAll();
            //忽略登录页面拦截
            //.antMatchers("/index").permitAll()
            //设置角色权限访问
            //.antMatchers("/login").permitAll()//指定自定义form表单请求的路径
        }
//        拦截根目录
        http.authorizeRequests()
                //允许跨域请求的OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/delete").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/update").hasAnyAuthority("ROLE_USER")
                .antMatchers("/**")
                .fullyAuthenticated()
                .and()
                //使用表单验证，默认的
                .formLogin()
                //自定义登录页面
                .loginPage("/index")
                //.loginProcessingUrl("/doLogin").
                //failureForwardUrl("/sys/loginFail").
                //使用forward的方式，能拿到具体失败的原因,并且会将错误信息以SPRING_SECURITY_LAST_EXCEPTION的key的形式将AuthenticationException对象保存到request域中
                //defaultSuccessUrl("/public/login/ok.html").
                //如果直接访问登录页面，则登录成功后重定向到这个页面，否则跳转到之前想要访问的页面

                // 关闭跨站请求防护及， 由于使用的是JWT，我们这里不需要csrf
                .and()
                .csrf()
                .disable()
                //禁用了session,基于token，所以不需要session每次访问需要传入token值，否则token返回信息为未登录
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        http.headers().cacheControl();

        //开启自动配置的注销功能,自定义
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/out")
                //该接口访问controller
                .invalidateHttpSession(true);


        //logout默认的url是 /logout,如果csrf启用，则请求方式是POST，否则请求方式是GET、POST、PUT、DELETE
        //http.logout();

    }

}
