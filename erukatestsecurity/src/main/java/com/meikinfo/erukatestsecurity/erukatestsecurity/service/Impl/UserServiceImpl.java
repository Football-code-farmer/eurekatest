package com.meikinfo.erukatestsecurity.erukatestsecurity.service.Impl;


import com.meikinfo.erukatestsecurity.erukatestsecurity.dao.UserDao;
import com.meikinfo.erukatestsecurity.erukatestsecurity.entity.SysUser;
import com.meikinfo.erukatestsecurity.erukatestsecurity.pojo.User;
import com.meikinfo.erukatestsecurity.erukatestsecurity.security.JwtTokenUtil;
import com.meikinfo.erukatestsecurity.erukatestsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author swh
 * @create: 2019-12-27 10:59
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = userDao.loadByUserName(username);
        if (sysUser !=null){
            return  sysUser;
        }
       throw new UsernameNotFoundException("用户名不存在");
    }



    @Override
    public String login(User user){
        UserDetails userDetails = loadUserByUsername(user.getUsername());
        if(!passwordEncoder.matches(user.getPassword(),userDetails.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
}
