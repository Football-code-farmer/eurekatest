package com.meikinfo.erukatestsecurity.erukatestsecurity.Enum;

/**
 * 枚举类
 *
 * @author swh
 * @create: 2019-12-26 15:32
 */
public enum Message {

    SUCCESS(200,"成功"),
    FAIL(500,"失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String msg;
    private Message(long code, String msg){
        this.code = code;
        this.msg = msg;
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
}
