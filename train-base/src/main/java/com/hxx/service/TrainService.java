package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.TrainEntity;

public interface TrainService extends IService<TrainEntity> {
    Page<TrainEntity> getTrainList(Page<TrainEntity> page, TrainEntity trainEntity);
}
