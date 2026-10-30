package com.hxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.PassengerEntity;
import com.hxx.mapper.PassengerMapper;
import com.hxx.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, PassengerEntity>
        implements PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;
    @Override
    public Page<PassengerEntity> getPassengerList(Page<PassengerEntity> page, PassengerEntity passengerEntity) {
        return passengerMapper.selectPage(page,new LambdaQueryWrapper<PassengerEntity>()
                .eq(passengerEntity.getName()!=null,PassengerEntity::getName,passengerEntity.getName())
                .eq(passengerEntity.getType()!=null,PassengerEntity::getType,passengerEntity.getType())
        );
    }
}
