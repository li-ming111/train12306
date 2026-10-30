package com.hxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.MemberEntity;

public interface MemberService extends IService<MemberEntity> {

    MemberEntity login(String mobile);
}
