package com.qiongqi.prj.template.utils.response.code;

public enum ResponseCode {
    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "请先登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_MUTE(2006, "账号已被禁言"),
    USER_ACCOUNT_LOCKED(2007, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2008, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2009, "账号已存在"),
    Logout_Success(2010,"退出成功"),
    USER_ACCOUNT_USE_BY_OTHERS(2011, "账号下线"),
    USER_LOGIN_FAIL(2012, "登录失败"),


    /* 业务错误 */
    NO_PERMISSION(3001, "无操作权限"),
    SEND_MSG_ERROR(3002, "发送失败"),
    SEND_MSG_SUCCESS(3016, "发送成功"),
    VER_CODE_ERROR(3003, "验证码错误"),
    JWT_ERRCODE_NULL(3004,"非法token"),
    JWT_ERRCODE_FAIL(3005,"token校验失败"),
    JWT_ERRCODE_EXPIRE(3006,"token过期"),
    parameterError(3007,"参数异常"),
    Data_Already_Exists(3008,"数据已存在" ),
    Data_Not_Exists(3009,"数据不存在" ),
    Operation_Failed(3010, "操作失败"),
    Data_Not_Null(3011, "数据不能为空"),
    Hash_Next(3012, "含有下级,操作失败"),
    Data_Used(3013 ,"数据使用中" ),
    Upload_Error(3014,"上传失败" ),
    Operation_SUCCESS(3015, "操作成功"),

    Error404(404, "404 Not Found"),

    serveError(500, "服务器异常");

    /** token */
    public static final Long TokenExp = 2592000000L; //一个月有效期
    public static final String TokenKey="X-Token";
    public static final String ParamterUserIdKey="userId";
    public static final String Mail_Code="mailCode_";
    public static final String VER_CODE = "verCode";
    public static long VerImgVaildTime= 180;

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMessageByCode(Integer code) {
        for (ResponseCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}

