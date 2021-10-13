package com.qiongqi.utils.ImgCode;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 说明
 * @author: ※狗尾巴草
 * @date: 2020-08-20 23:25
 **/
@Data
public class CaptchaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Base64Str;

    private String value;

    private CaptchaDto() {

    }

    private static class CaptchaHolder {
        private static final CaptchaDto INSTANCE = new CaptchaDto();
    }

    public static CaptchaDto getInstance() {
        return CaptchaHolder.INSTANCE;
    }
}
