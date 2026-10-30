package com.hxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxx.entity.TrainSeatEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeatMapper extends BaseMapper<TrainSeatEntity> {
}
