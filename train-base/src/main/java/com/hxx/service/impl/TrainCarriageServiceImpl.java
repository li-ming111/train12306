package com.hxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.TrainCarriageEntity;
import com.hxx.mapper.TrainCarriageMapper;
import com.hxx.service.TrainCarriageSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainCarriageServiceImpl extends ServiceImpl<TrainCarriageMapper, TrainCarriageEntity>
implements TrainCarriageSerivce {
    @Autowired
    private TrainCarriageMapper trainCarriageMapper;
    @Override
    public Page<TrainCarriageEntity> getTrainCarriageList(Page page, TrainCarriageEntity trainCarriageEntity) {
        return trainCarriageMapper.selectPage(page,new LambdaQueryWrapper<TrainCarriageEntity>()
                .eq(trainCarriageEntity.getTrainCode()!=null,TrainCarriageEntity::getTrainCode,trainCarriageEntity.getTrainCode())
        );
    }
}
