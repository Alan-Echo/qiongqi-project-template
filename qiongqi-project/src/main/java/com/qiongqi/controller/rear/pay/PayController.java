package com.qiongqi.controller.rear.pay;

import com.qiongqi.modules.log_moneys.model.dto.VLogMoneysDTO;
import com.qiongqi.modules.log_moneys.service.VLogMoneysService;
import com.qiongqi.modules.order.model.dto.VOrderDTO;
import com.qiongqi.modules.order.service.VOrderService;
import com.qiongqi.modules.user.model.dto.VUserDTO;
import com.qiongqi.modules.user.service.VUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("pay")
public class PayController {

    @Autowired
    private VOrderService orderService;
    @Autowired
    private VUserService vUserService;
    @Autowired
    private VLogMoneysService vLogMoneysService;

    @RequestMapping("/ali/notify/{orderId}")
    public void AliPayNotify(@PathVariable("orderId") Long orderId){
        //更新订单
        VOrderDTO vOrderDTO = orderService.get(orderId);
        vOrderDTO.setIsPay(1);//已支付
        orderService.update(vOrderDTO);
        //更新会员信息
        VUserDTO vUserDTO = vUserService.get(vOrderDTO.getUserId());
        vUserDTO.setIsVip(1);
        Date expiredTime = vUserDTO.getExpiredTime();
        Integer orderType = vOrderDTO.getOrderType();

        if(expiredTime.getTime()<new Date().getTime()){
            //会员过期
            Calendar instance = addTime(orderType, vOrderDTO);
            vUserDTO.setExpiredTime(instance.getTime());
        }else { //未过期
            //剩余时间
            long tims = expiredTime.getTime() - new Date().getTime();
            Calendar instance = addTime(orderType, vOrderDTO);
            long allTimes = instance.getTime().getTime() + tims;
            vUserDTO.setExpiredTime(new Date(allTimes));
        }
        vUserService.update(vUserDTO);
        //订单流水
        VLogMoneysDTO vLogMoneysDTO = new VLogMoneysDTO();
        vLogMoneysDTO.setDataFlag(1);
        vLogMoneysDTO.setDataSrc(1);
        vLogMoneysDTO.setGoodsId(vOrderDTO.getGoodsId());
        vLogMoneysDTO.setMoney(vOrderDTO.getTotal());
        vLogMoneysDTO.setMoneyType(1);
        vLogMoneysDTO.setOrderId(orderId);
        vLogMoneysDTO.setPayType(vOrderDTO.getPayType());
        vLogMoneysDTO.setRemark(vOrderDTO.getGoodsName()+"会员购买");
        vLogMoneysService.save(vLogMoneysDTO);
    }


    private Calendar addTime(Integer orderType, VOrderDTO vOrderDTO){
        Calendar instance = Calendar.getInstance();
        if(orderType==1){//周卡
            instance.add(Calendar.WEEK_OF_MONTH,vOrderDTO.getOrderNum());
        }else if(orderType==2){ //月卡
            instance.add(Calendar.MONTH,vOrderDTO.getOrderNum()-1);
        }else if(orderType==3){ //年卡
            instance.add(Calendar.YEAR,vOrderDTO.getOrderNum());
        }
        return instance;
    }




}
