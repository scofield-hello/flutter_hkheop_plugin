package com.chuangdun.flutter.plugin.HkHeop.libs.result;

/**
 * @author renjianan
 * @describe:
 * @date on 2019/9/19 14:13
 **/
public class ApiResult<T>{

    private T data;
    private int code;
    private Boolean success;
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}