package com.hxx.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.TrainCarriageEntity;
import com.hxx.entity.TrainSeatEntity;
import com.hxx.enums.SeatColEnum;
import com.hxx.mapper.SeatMapper;
import com.hxx.mapper.TrainCarriageMapper;
import com.hxx.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SeatServiceImpl extends ServiceImpl<SeatMapper, TrainSeatEntity>
implements SeatService {
    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private TrainCarriageMapper trainCarriageMapper;
    @Override
    public Page<TrainSeatEntity> getTrainList(Page<TrainSeatEntity> page, TrainSeatEntity trainSeatEntity) {
        return seatMapper.selectPage(page,new LambdaQueryWrapper<TrainSeatEntity>()
                .eq(trainSeatEntity.getTrainCode()!=null,TrainSeatEntity::getTrainCode,trainSeatEntity.getTrainCode())
                .eq(trainSeatEntity.getSeatType()!=null,TrainSeatEntity::getSeatType,trainSeatEntity.getSeatType())
               .orderByDesc(TrainSeatEntity::getId)
        );
    }

    @Transactional
    @Override
    public void genTrainSeat(String trainCode) {
        //生成数据前先按照车次编号删除对应数据，防止重复
        baseMapper.delete(new LambdaQueryWrapper<TrainSeatEntity>().eq(TrainSeatEntity::getTrainCode,trainCode));
        //查找当前车次下的所有的车厢
        List<TrainCarriageEntity> carriageEntityList = trainCarriageMapper
                .selectList(new LambdaQueryWrapper<TrainCarriageEntity>()
                        .eq(TrainCarriageEntity::getTrainCode,trainCode));
        // 循环生成每个车厢的座位
        for (TrainCarriageEntity trainCarriage : carriageEntityList) {
            Integer rowCount = trainCarriage.getRowCount();//获取总行数
            String seatType = trainCarriage.getSeatType();//获取座位类
            int seatIndex = 1;
            // 根据车厢的座位类型，筛选出所有的列，比如车箱类型是一等座，则筛选出columnList={ACDF}
            List<SeatColEnum> colEnumList = SeatColEnum.getColsByType(seatType);
            // 循环行数
            for (int row = 0; row <= rowCount; row++) {
                // 循环列数
                for (SeatColEnum seatColEnum : colEnumList) {
                    // 构造座位数据并保存数据库
                    TrainSeatEntity trainSeat = new TrainSeatEntity();
                    trainSeat.setTrainCode(trainCode);
                    trainSeat.setCarriageIndex(trainCarriage.getIndex());
                    //两位，前面补零
                    trainSeat.setRow(StrUtil.fillBefore(String.valueOf(row),'0',2));
                    trainSeat.setCol(seatColEnum.getCode());
                    trainSeat.setSeatType(seatType);
                    trainSeat.setCarriageSeatIndex(seatIndex++);
                    trainSeat.setCreateTime(new Date());
                    trainSeat.setUpdateTime(new Date());
                    //保存
                    baseMapper.insert(trainSeat);
                }
            }
        }
    }
}
