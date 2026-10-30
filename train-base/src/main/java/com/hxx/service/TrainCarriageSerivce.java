package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.TrainCarriageEntity;

public interface TrainCarriageSerivce extends IService<TrainCarriageEntity> {

    Page<TrainCarriageEntity> getTrainCarriageList(Page page, TrainCarriageEntity trainCarriageEntity);
}
