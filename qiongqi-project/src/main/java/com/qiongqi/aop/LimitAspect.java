package com.qiongqi.aop;

import com.qiongqi.config.Redis.RedisUtil;
import com.qiongqi.utils.Utils;
import com.qiongqi.utils.response.data.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Order
@Aspect
public class LimitAspect {

    @Autowired
    private RedisUtil redisUtil;


    @Pointcut("@annotation(com.qiongqi.aop.limit)")
    public void limitPointcut() {}

    @Around(value = "limitPointcut()")
    public Object runLimit(ProceedingJoinPoint   joinpoint)  {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddr = Utils.getIpAddr(request);
        Object result = null;
        if(ipAddr==null){
            result=new ResponseData<>().fail(301,"拒绝访问");
        }else {
            ipAddr=ipAddr.replaceAll("\\.","-").replaceAll(":","-");
            String lockedKey = ipAddr + "--locked";
            Object isLOcked = redisUtil.get(lockedKey);
            if(isLOcked!=null){
                long expire = redisUtil.getExpire(lockedKey);
                String msg = "操作太快了,"+ Utils.getTimeStrName(expire)+"后再试吧";
                result=new ResponseData<>().fail(301,msg);
            }else {
                MethodSignature signature = (MethodSignature) joinpoint.getSignature();
                Method method = signature.getMethod(); //获取被拦截的方法
                limit limitKey = method.getAnnotation(limit.class);
                Object red_num = redisUtil.get(ipAddr); //已保存的访问次数
                if(red_num!=null){
                    Integer integer = Integer.valueOf(red_num.toString());
                    if(integer>=limitKey.count()){ //次数到达阈值
                        long locked = limitKey.locked();
                        redisUtil.set(lockedKey,1,locked); //设置锁定时间
                        redisUtil.del(ipAddr);
                        String msg = "操作太快了,"+ Utils.getTimeStrName(locked)+"后再试吧";
                        result=new ResponseData<>().fail(301,msg);
                    }else{
                        redisUtil.incr(ipAddr,1); // redis 次数递增1
                    }
                }else {
                    redisUtil.set(ipAddr,1,limitKey.timeout()); //保存访问次数
                }
                try {
                    result = joinpoint.proceed();  //执行被注解的方法
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    result = new ResponseData().fail(500,"服务器繁忙,请稍后再试");
                }
            }
        }
        return result;
    }


}
