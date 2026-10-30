package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.DailyTrainEntity;
import com.hxx.entity.DailyTrainTicketEntity;

import java.util.Date;

public interface DailyTrainTicketService extends IService<DailyTrainTicketEntity> {

    Page<DailyTrainTicketEntity> loadPage(Page page, DailyTrainTicketEntity dailyTrainTicketEntity);

    //根据火车车次、日期、车票类型生成车票
    void genDaily(DailyTrainEntity dailyTrainEntity, Date date,String trainCode) ;
}
