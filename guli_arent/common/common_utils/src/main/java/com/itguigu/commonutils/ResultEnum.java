package com.itguigu.commonutils;

import lombok.Getter;

public enum ResultEnum {
    SUCCESS_CODE(20000, "成功"),
    FAIL_CODE(20001, "失败"),
    ON_LOGIN_CODE(50014, "未登录");
    @Getter
    private int code;
    @Getter
    private String stateInfo;
    ResultEnum(int code, String stateInfo) {
        this.code=code;
        this.stateInfo=stateInfo;
    }

    public static ResultEnum getCode(ResultEnum e){
        for (ResultEnum r:values()){
            return r;
        }
        return null;
    }
    public static ResultEnum getStateInfo(ResultEnum e){
        for (ResultEnum r:values()){
            return r;
        }
        return null;
    }
}
