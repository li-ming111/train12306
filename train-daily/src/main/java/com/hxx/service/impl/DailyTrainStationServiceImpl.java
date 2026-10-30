package com.hxx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.DailyTrainStationEntity;
import com.hxx.entity.TrainStationEntity;
import com.hxx.mapper.DailyTrainStationMapper;
import com.hxx.service.TrainFeign;
import com.hxx.util.SnowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainStationServiceImpl extends ServiceImpl<DailyTrainStationMapper, DailyTrainStationEntity>
implements com.hxx.service.DailyTrainStationService{
    @Autowired
    private DailyTrainStationMapper dailyTrainStationMapper;

    @Autowired
    private TrainFeign trainFeign;

    @Override
    public Page<DailyTrainStationEntity> getDailyTrainStationList(Page page, DailyTrainStationEntity dailyTrainStationEntity) {
        return dailyTrainStationMapper.selectPage(page,new LambdaQueryWrapper<DailyTrainStationEntity>()
                .eq(dailyTrainStationEntity.getTrainCode()!=null,DailyTrainStationEntity::getTrainCode,dailyTrainStationEntity.getTrainCode())
                .eq(dailyTrainStationEntity.getDate()!=null,DailyTrainStationEntity::getDate,dailyTrainStationEntity.getDate())
        );
    }

    @Override
    public void genDaily(Date date, String trainCode) {
        // 删除某日某车次的车站数据
        dailyTrainStationMapper.delete(new LambdaQueryWrapper<DailyTrainStationEntity>()
                .eq(DailyTrainStationEntity::getDate,date)
                .eq(DailyTrainStationEntity::getTrainCode,trainCode)
        );
        // 查询所有的车站数据
        List<TrainStationEntity> stationList = trainFeign.trainCodeStation(trainCode);
        if (CollUtil.isEmpty(stationList)) {
            System.out.println("---------trainCode------------>>>>"+trainCode);
            throw new RuntimeException("未查询到车站数据");
        }
        stationList.forEach(trainStationEntity -> {
            DailyTrainStationEntity dailyTrainStationEntity = BeanUtil.copyProperties(trainStationEntity, DailyTrainStationEntity.class);
            dailyTrainStationEntity.setDate(date);
            dailyTrainStationEntity.setId(SnowUtil.getSnowflakeId());
            dailyTrainStationMapper.insert(dailyTrainStationEntity);
        });
    }
}
