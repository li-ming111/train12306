package com.hxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.MemberEntity;
import com.hxx.mapper.MemberMapper;
import com.hxx.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity> implements MemberService {

    @Autowired
    private  MemberMapper memberMapper;

    @Override
    public MemberEntity login(String mobile) {
        return memberMapper.selectOne(new LambdaQueryWrapper<MemberEntity>()
                .eq(MemberEntity::getMobile,mobile));
    }
}
