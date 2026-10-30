package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.PassengerEntity;

public interface PassengerService extends IService<PassengerEntity> {
    Page<PassengerEntity> getPassengerList(Page<PassengerEntity> page, PassengerEntity passengerEntity);
}
