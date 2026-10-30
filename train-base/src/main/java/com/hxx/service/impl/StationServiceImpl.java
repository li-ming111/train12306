package com.hxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.StationEntity;
import com.hxx.mapper.StationMapper;
import com.hxx.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, StationEntity>
implements StationService {

    @Autowired
    private StationMapper stationMapper;
    @Override
    public Page<StationEntity> getStationList(Page<StationEntity> page, StationEntity stationEntity) {
        return stationMapper.selectPage(page,new LambdaQueryWrapper<StationEntity>()
                .like(stationEntity.getName()!=null,StationEntity::getName,stationEntity.getName())
        );
    }
}
