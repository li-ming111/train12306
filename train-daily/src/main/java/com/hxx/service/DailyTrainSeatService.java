package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.DailyTrainSeatEntity;

import java.util.Date;

public interface DailyTrainSeatService extends IService<DailyTrainSeatEntity> {

    Page<DailyTrainSeatEntity> getDailyTrainSeatList(Page page, DailyTrainSeatEntity dailyTrainSeatEntity);

    /**
     *
     * @param date  日期
     * @param trainCode 车次
     * @param sell 售卖情况，默认=0
     */
    void genDaily(Date date, String trainCode,String sell);

    //根据日期和车次获取座位数量
    Integer getSeatCount(Date date, String trainCode, String seatType);

}
