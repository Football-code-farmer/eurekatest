package com.eurekatest.commoninfo.exception;

/**
 * @Classname ParamException
 * @Description 参数异常类
 * @Date 2019-03-14 22:28
 * @Created by fengjingxing
 */
public class ParamException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ParamException(Object Obj) {
        super(Obj.toString());
    }
}
