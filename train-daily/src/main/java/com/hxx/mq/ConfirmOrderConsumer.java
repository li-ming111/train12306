package com.hxx.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hxx.entity.ConfirmOrderEntity;
import com.hxx.entity.DailyTrainTicketEntity;
import com.hxx.enums.SeatTypeEnum;
import com.hxx.service.DailyTrainTicketService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RocketMQMessageListener(topic = "confirmOrder", consumerGroup = "confirmOrder")
public class ConfirmOrderConsumer implements RocketMQListener<MessageExt> {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ConfirmOrderConsumer.class);
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private DailyTrainTicketService dailyTrainTicketService;
    @Override
    public void onMessage(MessageExt messageExt) {
        byte[] body = messageExt.getBody();
        logger.info("---------------------------------------------->>>>接收到消息,byte[]：{}", new String(body));
        // 处理消息
        ConfirmOrderEntity confirmOrderEntity = JSON.parseObject(body, ConfirmOrderEntity.class);
        logger.info("接收到消息,confirmOrderEntity：{}",confirmOrderEntity);
        //设置key为订单日期+车次：同一天的同一个车次订单只能提交一次
        String key = confirmOrderEntity.getDate()+"-"+confirmOrderEntity.getTrainCode();
        //加锁
        RLock lock = null;
        try {
            //使用看门狗
            lock = redissonClient.getLock(key);
            //0表示不设置超时时间，看门狗会监控执行后自动释放锁
            if (lock.tryLock(0, TimeUnit.SECONDS)) {//带看门狗
                DailyTrainTicketEntity dailyTrainTicketEntity =
                        dailyTrainTicketService.getById(confirmOrderEntity.getDailyTrainTicketId());
                //根据tickets车票数量生成订单
                JSONObject jsonObject = JSON.parseObject(confirmOrderEntity.getTickets());
                switch (jsonObject.get("seatTypeCode").toString()) {
                    case "1":
                        dailyTrainTicketEntity.setYdz(dailyTrainTicketEntity.getYdz() - 1);
                        break;
                    case "2":
                        dailyTrainTicketEntity.setEdz(dailyTrainTicketEntity.getEdz() - 1);
                        break;
                    case "3":
                        dailyTrainTicketEntity.setRw(dailyTrainTicketEntity.getRw() - 1);
                    case "4":
                        dailyTrainTicketEntity.setYw(dailyTrainTicketEntity.getYw() - 1);
                }
                dailyTrainTicketService.updateById(dailyTrainTicketEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //必须是当前线程才可以释放
            if (lock!=null && lock.isHeldByCurrentThread()){
                //释放锁
                lock.unlock();
            }
        }

    }
}
