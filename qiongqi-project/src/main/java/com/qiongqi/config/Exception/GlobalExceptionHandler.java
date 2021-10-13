package com.qiongqi.config.Exception;

import com.qiongqi.utils.response.code.ResponseCode;
import com.qiongqi.utils.response.data.ResponseData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    /**
     * @description: 自定义异常捕获
     * @param e 自定义异常类
     * @return:
     * @author: 小谭
     * @time: 2021-01-20 15:35
     */
    @ExceptionHandler(XException.class)
    public ResponseData XExceptionHandler(XException e) {
        return new ResponseData().fail(e.getCode(),e.getMessage());
    }
    /**
     * 通用的接口映射异常处理方法-参数处理
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        404 处理
        if (ex instanceof NoHandlerFoundException) {
            System.out.println("404 处理");
            return new ResponseEntity<>(new ResponseData().fail( ResponseCode.Error404),HttpStatus.OK);
        }
        if (ex instanceof MethodArgumentNotValidException) {   //方法参数无效
            System.out.println("方法参数无效: "+status.value());
//            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            //exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()
            return new ResponseEntity<>(new ResponseData().fail( ResponseCode.parameterError), HttpStatus.OK);
        }
//        方法参数类型匹配异常
        if (ex instanceof MethodArgumentTypeMismatchException) {
            System.out.println("方法参数转换异常");
//            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            return new ResponseEntity<>(new ResponseData().fail(ResponseCode.parameterError), HttpStatus.OK);
        }
        //org.springframework.web.bind.MissingServletRequestParameterException: Required Integer parameter 'type' is not present
        if(ex instanceof MissingServletRequestParameterException){
            return new ResponseEntity<>(new ResponseData().fail(ResponseCode.parameterError), HttpStatus.OK);
        }
//         接口请求方式/方式错误
        if(ex instanceof HttpRequestMethodNotSupportedException){
            return new ResponseEntity<>(new ResponseData().fail( ResponseCode.Error404),HttpStatus.OK);
        }
        System.out.println("其他异常: "+status.value());
        return new ResponseEntity<>(new ResponseData().fail(ResponseCode.serveError), HttpStatus.OK);
    }

}

