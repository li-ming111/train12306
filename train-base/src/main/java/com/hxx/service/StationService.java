package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.StationEntity;

public interface StationService extends IService<StationEntity> {

    Page<StationEntity> getStationList(Page<StationEntity> page, StationEntity stationEntity);
}
