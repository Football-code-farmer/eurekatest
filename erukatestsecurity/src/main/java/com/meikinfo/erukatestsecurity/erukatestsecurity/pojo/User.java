package com.meikinfo.erukatestsecurity.erukatestsecurity.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author swh
 * @create: 2020-03-03 09:31
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 7528042550988052239L;
    private String username;

    private String password;

}
