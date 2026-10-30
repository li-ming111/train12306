package com.hxx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.DailyTrainSeatEntity;
import com.hxx.entity.TrainSeatEntity;
import com.hxx.mapper.DailyTrainSeatMapper;
import com.hxx.service.TrainFeign;
import com.hxx.util.SnowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainSeatServiceImpl extends ServiceImpl<DailyTrainSeatMapper, DailyTrainSeatEntity>
implements com.hxx.service.DailyTrainSeatService{
    @Autowired
    private DailyTrainSeatMapper dailyTrainSeatMapper;

    @Autowired
    private TrainFeign trainFeign;
    @Override
    public Page<DailyTrainSeatEntity> getDailyTrainSeatList(Page page, DailyTrainSeatEntity dailyTrainSeatEntity) {
        return dailyTrainSeatMapper.selectPage(page,new LambdaQueryWrapper<DailyTrainSeatEntity>()
                .eq(dailyTrainSeatEntity.getTrainCode()!=null,DailyTrainSeatEntity::getTrainCode,dailyTrainSeatEntity.getTrainCode())
                .eq(dailyTrainSeatEntity.getDate()!=null,DailyTrainSeatEntity::getDate,dailyTrainSeatEntity.getDate())
        );
    }

    @Override
    public void genDaily(Date date, String trainCode, String sell) {
        //按照车次和日期删除数据
        dailyTrainSeatMapper.delete(new LambdaQueryWrapper<DailyTrainSeatEntity>()
                .eq(DailyTrainSeatEntity::getTrainCode,trainCode)
                .eq(DailyTrainSeatEntity::getDate,date)
        );
        //获取车次的所有座位
        List<TrainSeatEntity> seatList = trainFeign.load(trainCode);
        if (ObjUtil.isNull(seatList)) {
            throw new RuntimeException("没有座位数据，任务结束");
        }
        for (TrainSeatEntity seat : seatList) {
            DailyTrainSeatEntity dailyTrainSeatEntity = BeanUtil.copyProperties(seat,DailyTrainSeatEntity.class);
            dailyTrainSeatEntity.setId(SnowUtil.getSnowflakeId());
            dailyTrainSeatEntity.setDate(date);
            dailyTrainSeatEntity.setTrainCode(trainCode);
            dailyTrainSeatEntity.setSell(sell);
            dailyTrainSeatMapper.insert(dailyTrainSeatEntity);
        }
    }

    /**
     * 根据日期、车次代码和座位类型获取指定列车座位数量。
     *
     * @param date 乘车日期，用于查询特定日期的列车座位信息。
     * @param trainCode 车次代码，用于查询特定车次的列车座位信息。
     * @param seatType 座位类型，可选参数，用于查询特定类型的座位信息。如果为null，则不筛选座位类型。
     * @return 返回指定日期、车次和座位类型下的座位数量。如果不存在匹配的座位信息，则返回0。
     */
    @Override
    public Integer getSeatCount(Date date, String trainCode, String seatType) {
        // 使用LambdaQueryWrapper构建查询条件，查询指定日期、车次和（可选的）座位类型的座位数量
        return dailyTrainSeatMapper.selectList(new LambdaQueryWrapper<DailyTrainSeatEntity>()
                .eq(DailyTrainSeatEntity::getTrainCode, trainCode) // 筛选车次代码匹配的座位信息
                .eq(DailyTrainSeatEntity::getDate, date) // 筛选日期匹配的座位信息
                .eq(seatType != null, DailyTrainSeatEntity::getSeatType, seatType) // 如果seatType不为null，则筛选座位类型匹配的座位信息
        ).size();
    }
}
