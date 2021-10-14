package com.qiongqi.modules.vip.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.qiongqi.config.Exception.XException;
import com.qiongqi.modules.order.model.dto.VOrderDTO;
import com.qiongqi.modules.order.model.entity.VOrderEntity;
import com.qiongqi.modules.order.service.VOrderService;
import com.qiongqi.modules.payconfig.model.dto.VPayconfigDTO;
import com.qiongqi.modules.payconfig.service.VPayconfigService;
import com.qiongqi.modules.qiniu.model.dto.VQiniuDTO;
import com.qiongqi.modules.qiniu.service.VQiniuService;
import com.qiongqi.modules.user.model.dto.VUserDTO;
import com.qiongqi.modules.user.service.VUserService;
import com.qiongqi.modules.vip.model.dto.VVipDTO;
import com.qiongqi.utils.QR.QRCode;
import com.qiongqi.utils.Utils;
import com.qiongqi.utils.qiniu.QiniuUtil;
import com.qiongqi.utils.response.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public class PayServiveImpl implements PayServive {
    @Autowired
    private VVipService vVipService;
    @Autowired
    private VPayconfigService vPayconfigService;
    @Autowired
    private VUserService userService;
    @Autowired
    private VOrderService orderService;
    @Autowired
    private VPayconfigService payconfigService;
    @Autowired
    private VQiniuService vQiniuService;

    // 支付宝网关
    private String Ali_GatewayUrl = "https://openapi.alipay.com/gateway.do";


    @Override
    public JSONObject AliPay(Long id, Integer num, Long userId) {
        VVipDTO vVipDTO = vVipService.get(id);
        List<VPayconfigDTO> configs = vPayconfigService.list(new HashMap<String, Object>());
        if(configs.size()==0){
            throw new XException(ResponseCode.PAY_CONFIG_FAIL);
        }
        VUserDTO vUserDTO = userService.get(userId);
        if(vUserDTO==null){
            throw new XException(ResponseCode.USER_NOT_LOGIN);
        }
        //生成订单
        VOrderEntity userOrder = orderService.save(new VOrderDTO(
                vVipDTO.getTotal(),
                userId,
                vVipDTO.getId(),
                0,
                vUserDTO.getUserName(),
                vVipDTO.getName(),
                vVipDTO.getType(),
                num,
                1
        ));
        //提交订单到支付宝
        List<VPayconfigDTO> list = payconfigService.list(new HashMap<>());
        if(list.size()==0){
            throw new XException(ResponseCode.PAY_CONFIG_FAIL);
        }
        VPayconfigDTO vPayconfigDTO = list.get(0);

        // 获取初始化的AliPayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                Ali_GatewayUrl,
                vPayconfigDTO.getAliAppId(),
                vPayconfigDTO.getAliMerchantPrivateKey(),
                "json",
                "utf-8",
                vPayconfigDTO.getAliPublicKey(),
                "RSA2");
        // 设置请求参数
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        String notify_url = vPayconfigDTO.getNotifyUrl() + userOrder.getId();
        request.setReturnUrl(vPayconfigDTO.getNotifyUrl()+userOrder.getId());
        request.setNotifyUrl(vPayconfigDTO.getNotifyUrl()+userOrder.getId());
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = Utils.getOrderNoByTime();
        //商品描述，可空
        String body = userOrder.getGoodsName()+"会员开通";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out_trade_no",out_trade_no);
        jsonObject.put("total_amount",userOrder.getTotal());
        jsonObject.put("subject",userOrder.getGoodsName());
        jsonObject.put("timeout_express",vPayconfigDTO.getPayExTime()+"m");
        jsonObject.put("body",body);
        jsonObject.put("notify_url",vPayconfigDTO.getNotifyUrl()+"/"+userOrder.getId());
//        jsonObject.put("product_code","FACE_TO_FACE_PAYMENT");
//        jsonObject.put("qr_pay_mode","4");
        request.setBizContent(jsonObject.toJSONString());

        //请求
        AlipayTradePrecreateResponse response = null;
        String result = null;
        JSONObject object = new JSONObject();
        try {
            response = alipayClient.execute(request);
            if(response.isSuccess()){//下单成功
                MultipartFile qrCodeFile = QRCode.getQrCode(response.getQrCode());
                VQiniuDTO qiniuDTO = vQiniuService.getByStatus(1);
                QiniuUtil qiniuUtil = new QiniuUtil(qiniuDTO.getAk(),qiniuDTO.getSk(),qiniuDTO.getBucketName(),qiniuDTO.getZone(),qiniuDTO.getDoMain());
                object.put("qrCode",qiniuUtil.uploadImage(qrCodeFile));
                object.put("payExTime",vPayconfigDTO.getPayExTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XException(ResponseCode.PAY_FAIL);
        }
//        userOrder.setPaymentUrl(result);
//        orderService.updateById(userOrder);
        return object;
    }



}
