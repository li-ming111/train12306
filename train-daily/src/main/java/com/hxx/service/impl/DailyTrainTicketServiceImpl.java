package com.hxx.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.EnumUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.DailyTrainEntity;
import com.hxx.entity.DailyTrainTicketEntity;
import com.hxx.entity.TrainStationEntity;
import com.hxx.enums.SeatTypeEnum;
import com.hxx.enums.TrainTypeEnum;
import com.hxx.mapper.DailyTrainTicketMapper;
import com.hxx.service.DailyTrainSeatService;
import com.hxx.service.DailyTrainTicketService;
import com.hxx.service.TrainFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class DailyTrainTicketServiceImpl extends ServiceImpl<DailyTrainTicketMapper, DailyTrainTicketEntity>
implements DailyTrainTicketService {

    @Autowired
    private DailyTrainTicketMapper dailyTrainTicketMapper;

    @Autowired
    private DailyTrainSeatService dailyTrainSeatService;

    @Autowired
    private TrainFeign trainFeign;

    @Override
    public Page<DailyTrainTicketEntity> loadPage(Page page, DailyTrainTicketEntity dailyTrainTicketEntity) {
        return dailyTrainTicketMapper.selectPage(page,new LambdaQueryWrapper<DailyTrainTicketEntity>()
                .eq(dailyTrainTicketEntity.getTrainCode()!=null,DailyTrainTicketEntity::getTrainCode,dailyTrainTicketEntity.getTrainCode())
                .eq(dailyTrainTicketEntity.getDate()!=null,DailyTrainTicketEntity::getDate,dailyTrainTicketEntity.getDate())
                .eq(dailyTrainTicketEntity.getStart()!=null,DailyTrainTicketEntity::getStart,dailyTrainTicketEntity.getStart())
                .eq(dailyTrainTicketEntity.getEnd()!=null,DailyTrainTicketEntity::getEnd,dailyTrainTicketEntity.getEnd())
        );
    }

    @Override
    @Transactional
    public void genDaily(DailyTrainEntity dailyTrainEntity, Date date, String trainCode) {
        // 删除某日某车次的车票数据
        dailyTrainTicketMapper.delete(new LambdaQueryWrapper<DailyTrainTicketEntity>()
                .eq(DailyTrainTicketEntity::getDate,date)
                .eq(DailyTrainTicketEntity::getTrainCode,trainCode)
        );
        //查询某车次的所有车站数据
        List<TrainStationEntity> stationList = trainFeign.trainCodeStation(trainCode);
        if (CollUtil.isEmpty(stationList)) {
            throw new RuntimeException("该车次没有车站数据");
        }
        //循环生成该车次的所有车票数据:这是一个嵌套循环
        for (int i = 0; i < stationList.size(); i++) {
            // 开始站
            TrainStationEntity startStation = stationList.get(i);
            BigDecimal sunKM = BigDecimal.ZERO;
            for (int j = i + 1; j < stationList.size(); j++) {
                // 终点站
                TrainStationEntity endStation = stationList.get(j);
                sunKM = sunKM.add(endStation.getKm());
                // 生成车票数据
                DailyTrainTicketEntity dailyTrainTicketEntity = new DailyTrainTicketEntity();
                dailyTrainTicketEntity.setDate(date);
                dailyTrainTicketEntity.setTrainCode(trainCode);
                dailyTrainTicketEntity.setStart(startStation.getName());
                dailyTrainTicketEntity.setStartPinyin(startStation.getNamePinyin());
                dailyTrainTicketEntity.setStartIndex(startStation.getIndex());
                dailyTrainTicketEntity.setStartTime(startStation.getOutTime());
                dailyTrainTicketEntity.setEnd(endStation.getName());
                dailyTrainTicketEntity.setEndPinyin(endStation.getNamePinyin());
                dailyTrainTicketEntity.setEndIndex(endStation.getIndex());
                dailyTrainTicketEntity.setEndTime(endStation.getInTime());
                //获取座位数量
                //一等座
                Integer ydz = dailyTrainSeatService.getSeatCount(date,trainCode, SeatTypeEnum.YDZ.getCode());
                //二等座
                Integer edz = dailyTrainSeatService.getSeatCount(date,trainCode, SeatTypeEnum.EDZ.getCode());
                //硬卧
                Integer yw = dailyTrainSeatService.getSeatCount(date,trainCode, SeatTypeEnum.YW.getCode());
                //软卧
                Integer rw = dailyTrainSeatService.getSeatCount(date,trainCode, SeatTypeEnum.RW.getCode());

                //添加信息数据
                dailyTrainTicketEntity.setYdz(ydz);
                dailyTrainTicketEntity.setEdz(edz);
                dailyTrainTicketEntity.setYw(yw);
                dailyTrainTicketEntity.setRw(rw);

                //获取车票价格:票价=里程和*座位票价格*车次类型系数
                String trainType = dailyTrainEntity.getType();
                //获取车票系数
                BigDecimal priceRate = EnumUtil.getFieldBy(TrainTypeEnum::getPriceRate, TrainTypeEnum::getCode, trainType);
                //一等座
                BigDecimal ydzPrice = sunKM.multiply(SeatTypeEnum.YDZ.getPrice()).multiply(priceRate).setScale(2, RoundingMode.HALF_UP);
                //二等座
                BigDecimal edzPrice = sunKM.multiply(SeatTypeEnum.EDZ.getPrice()).multiply(priceRate).setScale(2, RoundingMode.HALF_UP);
                //硬卧
                BigDecimal ywPrice = sunKM.multiply(SeatTypeEnum.YW.getPrice()).multiply(priceRate).setScale(2, RoundingMode.HALF_UP);
                //软卧
                BigDecimal rwPrice = sunKM.multiply(SeatTypeEnum.RW.getPrice()).multiply(priceRate).setScale(2, RoundingMode.HALF_UP);

                //添加信息数据
                dailyTrainTicketEntity.setYdzPrice(ydzPrice);
                dailyTrainTicketEntity.setEdzPrice(edzPrice);
                dailyTrainTicketEntity.setYwPrice(ywPrice);
                dailyTrainTicketEntity.setRwPrice(rwPrice);
                dailyTrainTicketMapper.insert(dailyTrainTicketEntity);
            }
        }


    }
}
