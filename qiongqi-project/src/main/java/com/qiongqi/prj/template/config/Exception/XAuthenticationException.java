package com.qiongqi.prj.template.config.Exception;

import com.qiongqi.prj.template.utils.response.code.ResponseCode;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * @description: 验证操作异常
 * @author: ※狗尾巴草
 * @date: 2020-12-01 18:36
 **/
@Data
public class XAuthenticationException extends AuthenticationException {

    private Integer code;

    public XAuthenticationException(String msg) {
        super(msg);
    }


    public XAuthenticationException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code=responseCode.getCode();
    }
}
