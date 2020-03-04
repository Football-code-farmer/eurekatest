package com.meikinfo.erukatestsecurity.erukatestsecurity.dao;


import com.meikinfo.erukatestsecurity.erukatestsecurity.entity.SysUser;
/**
 * @author swh
 * @create: 2020-03-03 16:00
 */
public interface UserDao {
    SysUser loadByUserName(String username);
}
