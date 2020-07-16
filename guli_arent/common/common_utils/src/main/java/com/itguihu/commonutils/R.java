package com.itguihu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //把构造方法私有
    private R() {}

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultEnum.SUCCESS_CODE.getCode());
        r.setMessage(ResultEnum.SUCCESS_CODE.getStateInfo());
        return r;
    }

    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultEnum.FAIL_CODE.getCode());
        r.setMessage(ResultEnum.FAIL_CODE.getStateInfo());
        return r;
    }

    //未登录静态方法
    public static R noLogin() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultEnum.ON_LOGIN_CODE.getCode());
        r.setMessage(ResultEnum.ON_LOGIN_CODE.getStateInfo());
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
