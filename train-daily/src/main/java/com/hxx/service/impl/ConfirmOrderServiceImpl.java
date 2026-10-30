package com.hxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxx.entity.ConfirmOrderEntity;
import com.hxx.mapper.ConfirmOrderMapper;
import com.hxx.service.ConfirmOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmOrderServiceImpl extends ServiceImpl<ConfirmOrderMapper, ConfirmOrderEntity>
implements ConfirmOrderService {
    @Autowired
    private ConfirmOrderMapper confirmOrderMapper;
    @Override
    public Page<ConfirmOrderEntity> getConfirmOrderList(Page page, ConfirmOrderEntity confirmOrderEntity) {
        return confirmOrderMapper.selectPage(page, new LambdaQueryWrapper<ConfirmOrderEntity>()
                .eq(confirmOrderEntity.getMemberId() != null, ConfirmOrderEntity::getMemberId, confirmOrderEntity.getMemberId())
                .eq(confirmOrderEntity.getStatus() != null, ConfirmOrderEntity::getStatus, confirmOrderEntity.getStatus())
                .eq(confirmOrderEntity.getTrainCode() != null, ConfirmOrderEntity::getTrainCode, confirmOrderEntity.getTrainCode())
                .eq(confirmOrderEntity.getDate() != null, ConfirmOrderEntity::getDate, confirmOrderEntity.getDate())
        );
    }
}
