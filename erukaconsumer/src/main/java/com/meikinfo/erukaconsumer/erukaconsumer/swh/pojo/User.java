package com.meikinfo.erukaconsumer.erukaconsumer.swh.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author swh
 * @create: 2020-03-03 13:15
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8466328729942187138L;
    private String username;
    private String password;
}
