package com.hxx.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hxx.entity.ConfirmOrderEntity;
import com.hxx.service.ConfirmOrderService;
import com.hxx.util.SnowUtil;
import jakarta.annotation.Resource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeforeConfirmOrderService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BeforeConfirmOrderService.class);

    @Autowired
    private ConfirmOrderService confirmOrderService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void beforeConfirmOrder(ConfirmOrderEntity confirmOrderEntity) {
        //保存订单信息：确保购票无论是否能抢到锁
        JSONArray objects = JSON.parseArray(confirmOrderEntity.getTickets());
        for (Object object : objects) {
            JSONObject jsonObject = JSON.parseObject(object.toString());
            // 保存订单信息
            confirmOrderEntity.setTickets(jsonObject.toJSONString());//重新设置车票信息
            confirmOrderEntity.setId(SnowUtil.getSnowflakeId());//重新设置id防止读取缓存id重复
            confirmOrderService.save(confirmOrderEntity);
            //发送消息
            logger.info("发送消息：{}", confirmOrderEntity);
            rocketMQTemplate.convertAndSend("confirmOrder", confirmOrderEntity);
            logger.info("发送消息成功：{}", confirmOrderEntity);
        }
    }
}























