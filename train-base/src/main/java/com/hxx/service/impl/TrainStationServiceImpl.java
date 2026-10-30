package com.hxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.TrainStationEntity;
import com.hxx.mapper.TrainSationMapper;
import com.hxx.service.TrainStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainStationServiceImpl extends ServiceImpl<TrainSationMapper, TrainStationEntity>
 implements TrainStationService {
    @Autowired
    private TrainSationMapper trainSationMapper;
    @Override
    public Page<TrainStationEntity> getTrainStationList(Page<TrainStationEntity> page,
                                                        TrainStationEntity trainStationEntity) {
        return trainSationMapper.selectPage(page,new LambdaQueryWrapper<TrainStationEntity>()
                .eq(trainStationEntity.getTrainCode()!=null,TrainStationEntity::getTrainCode,trainStationEntity.getTrainCode())
        );
    }
}
