package com.hxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.TrainEntity;
import com.hxx.mapper.TrainMapper;
import com.hxx.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainServiceImpl extends ServiceImpl<TrainMapper, TrainEntity>
implements TrainService {
    @Autowired
    private TrainMapper trainMapper;
    @Override
    public Page<TrainEntity> getTrainList(Page<TrainEntity> page, TrainEntity trainEntity) {
        return trainMapper.selectPage(page,new LambdaQueryWrapper<TrainEntity>()
                .like(trainEntity.getCode()!=null,TrainEntity::getCode,trainEntity.getCode())
                .like(trainEntity.getStart()!=null,TrainEntity::getStart,trainEntity.getStart())
                .like(trainEntity.getEnd()!=null,TrainEntity::getEnd,trainEntity.getEnd())
        );
    }
}
