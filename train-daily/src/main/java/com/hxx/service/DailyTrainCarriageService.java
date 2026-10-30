package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.DailyTrainCarriageEntity;

import java.util.Date;
import java.util.List;

public interface DailyTrainCarriageService extends IService<DailyTrainCarriageEntity> {

    Page<DailyTrainCarriageEntity> getDailyTrainCarriageList(Page page, DailyTrainCarriageEntity dailyTrainCarriageEntity);

    void saveCarriage(DailyTrainCarriageEntity dailyTrainCarriageEntity);

    //按车次获取车厢
    List<DailyTrainCarriageEntity> loadByTrainCode(String trainCode);

    void genDaily(Date date, String trainCode);
}
