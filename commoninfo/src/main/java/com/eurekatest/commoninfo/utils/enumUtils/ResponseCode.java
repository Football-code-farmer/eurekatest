package com.eurekatest.commoninfo.utils.enumUtils;

/**
 * description:
 * 返回值code枚举类
 *
 * @author hongjw
 * @create 2020-09-10 16:42
 */
public enum ResponseCode {
    SUCCESS(200, "成功(๑*◡*๑)"),
    ERROR(500, "不好了，系统出错了o(╥﹏╥)o"),
    NEED_LOGIN(400, "请先登录亲ヽ(･ω･´ﾒ)"),
    INVALID_PARAMETERS(100, "参数异常(〝▼皿▼)");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
