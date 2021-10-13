package com.qiongqi.utils.response.data;

import com.qiongqi.utils.response.code.ResponseCode;

import java.io.Serializable;


/** 页面响应数据 */
public class ResponseData<T> implements Serializable {

    /** 请求状态 */
    private int status= ResponseCode.SUCCESS.getCode();

    /** 结果数据集 */
    private T data;

    /** 响应信息 */
    private String msg=ResponseCode.SUCCESS.getMessage();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    /** 成功数据集,接受数据 */
    public ResponseData<T> success(T  dataList){
        this.setData(dataList);
        return this;
    }

    /** 成功数据集,接受数据 */
    public ResponseData<T> success(T  dataList,int total,int index){
        this.setData(dataList);
        return this;
    }

    /** 成功数据集,默认成功消息 */
    public ResponseData success(){
        return this;
    }

    /** 成功数据集,接受成功消息 */
    public ResponseData success(String msg){
        this.setMsg(msg);
        return this;
    }

    /** 成功数据集,接受成功消息 */
    public ResponseData success(String msg,T dataList){
        this.setMsg(msg);
        this.setData(dataList);
        return this;
    }

    /** 失败数据集 */
    public ResponseData fail(int ststus){
        this.setStatus(ststus);
        this.setMsg("fail");
        return this;
    }


    /** 失败数据集 */
    public ResponseData fail(String msg){
        this.setStatus(ResponseCode.COMMON_FAIL.getCode());
        this.setMsg(msg);
        return this;
    }

    /** 失败数据集 */
    public ResponseData<T> fail(ResponseCode code){
        this.setStatus(code.getCode());
        this.setMsg(code.getMessage());
        return this;
    }

    /** 失败数据集 */
    public ResponseData<T> fail(){
        this.setStatus(ResponseCode.COMMON_FAIL.getCode());
        this.setMsg(ResponseCode.COMMON_FAIL.getMessage());
        return this;
    }

    /** 异常数据/请求捕获 */
    public ResponseData<T> fail(int code,String msg){
        this.setMsg(msg);
        this.setStatus(code);
        return this;
    }


}
