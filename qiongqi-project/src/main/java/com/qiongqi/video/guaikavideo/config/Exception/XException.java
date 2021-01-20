package com.qiongqi.video.guaikavideo.config.Exception;

import com.qiongqi.video.guaikavideo.utils.response.code.ResponseCode;

/**
 * @description: 普通操作异常
 * @author: ※狗尾巴草
 * @date: 2020-12-01 18:36
 **/
public class XException extends RuntimeException {

    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public XException() {
        super();
    }

    public XException(int code, String message) {
        super(message);
        this.setCode(code);
    }
    public XException( String message) {
        super(message);
        this.setCode(500); //自定义状态
    }

    public XException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.setCode(responseCode.getCode()); //自定义状态
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
