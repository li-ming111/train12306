package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.TrainStationEntity;

public interface TrainStationService extends IService<TrainStationEntity> {

    Page<TrainStationEntity> getTrainStationList(Page<TrainStationEntity> page, TrainStationEntity trainStationEntity);
}
