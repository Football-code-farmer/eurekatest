package com.meikinfo.erukatestsecurity.erukatestsecurity.utils;


import com.meikinfo.erukatestsecurity.erukatestsecurity.Enum.Message;

/**
 * 返回值工具类
 *
 * @author swh
 * @create: 2019-12-26 15:47
 */
public class ResultMassage<T> {

    private long code;

    private String msg;

    private T data;

    public ResultMassage(){};

    public ResultMassage(long code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @date 2019/12/26 15:52
     * @author swh
     */
    public ResultMassage success(T data){
        return new ResultMassage(Message.SUCCESS.getCode(),null,data);
    }

    /**
     *
     * @date 2019/12/26 15:58
     * @author swh
     */
    public ResultMassage success(T data,String msg){
        return new ResultMassage(Message.SUCCESS.getCode(),msg,data);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ResultMassage<T> unauthorized(T data) {
        return new ResultMassage<T>(Message.UNAUTHORIZED.getCode(), Message.UNAUTHORIZED.getMsg(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ResultMassage<T> forbidden(T data) {
        return new ResultMassage<T>(Message.FORBIDDEN.getCode(), Message.FORBIDDEN.getMsg(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
