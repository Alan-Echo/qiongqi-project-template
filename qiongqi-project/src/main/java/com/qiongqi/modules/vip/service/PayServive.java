package com.qiongqi.modules.vip.service;

import com.alibaba.fastjson.JSONObject;

public interface PayServive {

    JSONObject AliPay(Long id, Integer num, Long userId);


}
