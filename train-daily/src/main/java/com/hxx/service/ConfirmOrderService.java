package com.hxx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hxx.entity.ConfirmOrderEntity;

public interface ConfirmOrderService extends IService<ConfirmOrderEntity> {

    Page<ConfirmOrderEntity> getConfirmOrderList(Page page, ConfirmOrderEntity confirmOrderEntity);

}
