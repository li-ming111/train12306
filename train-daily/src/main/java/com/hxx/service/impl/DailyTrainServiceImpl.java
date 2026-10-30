package com.hxx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.DailyTrainEntity;
import com.hxx.entity.TrainEntity;
import com.hxx.mapper.DailyTrainMapper;
import com.hxx.service.*;
import com.hxx.util.SnowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainServiceImpl extends ServiceImpl<DailyTrainMapper, DailyTrainEntity>
implements com.hxx.service.DailyTrainService{

    @Autowired
    private DailyTrainMapper dailyTrainMapper;

    @Autowired
    private TrainFeign trainFeign;

    @Autowired
    private DailyTrainStationService dailyTrainStationService;

    @Autowired
    private DailyTrainCarriageService dailyTrainCarriageService;

    @Autowired
    private DailyTrainSeatService dailyTrainSeatService;

    @Autowired
    private DailyTrainTicketService dailyTrainTicketService;

    @Override
    public Page<DailyTrainEntity> getDailyTrainList(Page page, DailyTrainEntity dailyTrainEntity) {
        return dailyTrainMapper.selectPage(page,new LambdaQueryWrapper<DailyTrainEntity>()
                .eq(dailyTrainEntity.getDate()!=null,DailyTrainEntity::getDate,dailyTrainEntity.getDate())
                .eq(dailyTrainEntity.getCode()!=null,DailyTrainEntity::getCode,dailyTrainEntity.getCode())
        );
    }

    /**
     * 生成每日训练报告
     * 本方法旨在根据指定的日期生成每日的训练报告。它首先从外部服务获取所有的训练实体列表，
     * 然后针对每个训练实体生成相应的每日训练报告。
     *
     * @param date 生成报告的日期
     * @throws RuntimeException 如果没有可用的训练数据，则抛出运行时异常
     */
    @Override
    public void genDaily(Date date) {
        // 从外部服务获取所有训练实体
        List<TrainEntity> trainList = trainFeign.trainAll();

        // 检查是否存在训练实体，如果不存在则抛出异常
        if (CollUtil.isEmpty(trainList)) {
            throw new RuntimeException("没有基础数据，查询结束");
        }

        // 遍历每个训练实体，生成每日训练报告
        for (TrainEntity train : trainList) {
            genDailyTrain(date,train);
        }
    }


    @Override
    public void genDailyTrain(Date date, TrainEntity trainEntity) {
        //删除该车次已有的数据(按照车次和日期)
        dailyTrainMapper.delete(new LambdaQueryWrapper<DailyTrainEntity>()
                .eq(DailyTrainEntity::getDate,date)
                .eq(DailyTrainEntity::getCode,trainEntity.getCode())
        );
        //生成该车次的数据
        DailyTrainEntity dailyTrainEntity = BeanUtil.copyProperties(trainEntity, DailyTrainEntity.class);
        //重新设置id，因为每日都生成数据，所以不能使用train自带的id，会重复
        dailyTrainEntity.setId(SnowUtil.getSnowflakeId());
        dailyTrainEntity.setDate(date);
        //生成每日车次
        dailyTrainMapper.insert(dailyTrainEntity);
        //调用对应的车次生成车站数据
        dailyTrainStationService.genDaily(date,trainEntity.getCode());
        // 调用车次车厢生成车次车厢数据
        dailyTrainCarriageService.genDaily(date,trainEntity.getCode());
        //根据车次获取车站的数量
        int stationCount = trainFeign.trainCodeStation(trainEntity.getCode()).size();
        String sell = StrUtil.fillBefore("", '0', stationCount - 1);
        //生成每日座位
        dailyTrainSeatService.genDaily(date,trainEntity.getCode(),sell);
        //生成每日车票
        dailyTrainTicketService.genDaily(dailyTrainEntity,date,trainEntity.getCode());
    }
}
