package com.qiongqi.utils.JWT.TokenResult;


import com.qiongqi.utils.response.code.ResponseCode;
import io.jsonwebtoken.Claims;

public class TokenResult {
    private int code;

    private boolean status;  // token验证信息 状态

    private String msg;

    private Claims data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return status;
    }

    public Claims getData() {
        return data;
    }

    public void setData(Claims data) {
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TokenResult success(Claims claims){
        this.code=200;
        this.setData(claims);
        this.status=true;
        this.msg="成功";
        return this;
    }

    public TokenResult illegal(){
        this.code= ResponseCode.JWT_ERRCODE_NULL.getCode();
        this.status=false;
        this.msg=ResponseCode.JWT_ERRCODE_NULL.getMessage();
        return this;
    }

    public TokenResult fail(){
        this.code= ResponseCode.JWT_ERRCODE_FAIL.getCode();
        this.status=false;
        this.msg=ResponseCode.JWT_ERRCODE_FAIL.getMessage();
        return this;
    }

    public TokenResult Expired(){
        this.code= ResponseCode.JWT_ERRCODE_EXPIRE.getCode();
        this.status=false;
        this.msg=ResponseCode.JWT_ERRCODE_EXPIRE.getMessage();
        return this;
    }
}
