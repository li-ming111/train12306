package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.TrainSeatEntity;

public interface SeatService extends IService<TrainSeatEntity> {
    Page<TrainSeatEntity> getTrainList(Page<TrainSeatEntity> page, TrainSeatEntity trainSeatEntity);

    void genTrainSeat(String trainCode);
}
