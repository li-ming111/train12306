package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.DailyTrainStationEntity;

import java.util.Date;

public interface DailyTrainStationService extends IService<DailyTrainStationEntity> {

    Page<DailyTrainStationEntity> getDailyTrainStationList(Page page, DailyTrainStationEntity dailyTrainStationEntity);

    void genDaily(Date date, String trainCode);

}
