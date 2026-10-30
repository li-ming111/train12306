package com.hxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxx.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<MemberEntity> {
}
