package com.hxx.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.DailyTrainCarriageEntity;
import com.hxx.entity.DailyTrainStationEntity;
import com.hxx.entity.TrainCarriageEntity;
import com.hxx.enums.SeatColEnum;
import com.hxx.mapper.DailyTrainCarriageMapper;
import com.hxx.service.TrainFeign;
import com.hxx.util.SnowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DailyTrainCarriageServiceImpl extends ServiceImpl<DailyTrainCarriageMapper, DailyTrainCarriageEntity>
implements com.hxx.service.DailyTrainCarriageService{

    @Autowired
    private DailyTrainCarriageMapper dailyTrainCarriageMapper;

    @Autowired
    private TrainFeign trainFeign;
    @Override
    public Page<DailyTrainCarriageEntity> getDailyTrainCarriageList(Page page, DailyTrainCarriageEntity dailyTrainCarriageEntity) {
        return dailyTrainCarriageMapper.selectPage(page, new LambdaQueryWrapper<DailyTrainCarriageEntity>()
                .eq(dailyTrainCarriageEntity.getTrainCode() != null, DailyTrainCarriageEntity::getTrainCode, dailyTrainCarriageEntity.getTrainCode())
                .eq(dailyTrainCarriageEntity.getDate() != null, DailyTrainCarriageEntity::getDate, dailyTrainCarriageEntity.getDate())
        );
    }

    @Override
    public List<DailyTrainCarriageEntity> loadByTrainCode(String trainCode) {
        return dailyTrainCarriageMapper.selectList(new LambdaQueryWrapper<DailyTrainCarriageEntity>()
                .eq(DailyTrainCarriageEntity::getTrainCode, trainCode));
    }

    @Override
    public void genDaily(Date date, String trainCode) {
        //删除
        dailyTrainCarriageMapper.delete(new LambdaQueryWrapper<DailyTrainCarriageEntity>()
                .eq(DailyTrainCarriageEntity::getTrainCode, trainCode)
                .eq(DailyTrainCarriageEntity::getDate, date));
        //获取车厢
        List<TrainCarriageEntity> trainCarriageEntities = trainFeign.findAll(trainCode);
        //判断
        if (ObjUtil.isNull(trainCarriageEntities)) {
            throw new RuntimeException("该车次不存在车厢");
        }
        for (TrainCarriageEntity trainCarriageEntity : trainCarriageEntities) {
            //对拷
            DailyTrainCarriageEntity dailyTrainCarriageEntity = BeanUtil.copyProperties(trainCarriageEntity, DailyTrainCarriageEntity.class);
            //设置日期
            dailyTrainCarriageEntity.setDate(date);
            dailyTrainCarriageEntity.setId(SnowUtil.getSnowflakeId());
            dailyTrainCarriageEntity.setTrainCode(trainCode);
            dailyTrainCarriageMapper.insert(dailyTrainCarriageEntity);
        }
    }

    /**
     * 每天每车次的座位是唯一的
     * @param entity
     */
    @Override
    public void saveCarriage(DailyTrainCarriageEntity entity) {
        //计算列数和总座位数
        List<SeatColEnum> seatColEnums = SeatColEnum.getColsByType(entity.getSeatType());
        entity.setColCount(seatColEnums.size());
        entity.setSeatCount(entity.getColCount() * entity.getRowCount());
        //对拷
        DailyTrainCarriageEntity trainCarriageEntity = BeanUtil.copyProperties(entity, DailyTrainCarriageEntity.class);
        //判断
        if (ObjUtil.isNull(trainCarriageEntity.getId())) {
            if (ObjUtil.isNotNull(checkUnique(entity.getTrainCode(), entity.getIndex().toString()))) {
                throw new RuntimeException("该车次编号已存在,不是唯一");
            }
            dailyTrainCarriageMapper.insert(trainCarriageEntity);
        }
    }

    //检查车次编号是否唯一
    private DailyTrainCarriageEntity checkUnique(String trainCode, String index) {
        return dailyTrainCarriageMapper.selectOne(new LambdaQueryWrapper<DailyTrainCarriageEntity>()
                .eq(DailyTrainCarriageEntity::getTrainCode, trainCode)
                .eq(DailyTrainCarriageEntity::getIndex, index));
    }
}
