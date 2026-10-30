package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.DailyTrainEntity;
import com.hxx.entity.TrainEntity;

import java.util.Date;

public interface DailyTrainService extends IService<DailyTrainEntity> {

    Page<DailyTrainEntity> getDailyTrainList(Page page, DailyTrainEntity dailyTrainEntity);

    ///生成指定日期所有车次的数据
    void genDaily(Date date);

    //生成指定日期指定车次的数据
    void genDailyTrain(Date date, TrainEntity trainEntity);
}
