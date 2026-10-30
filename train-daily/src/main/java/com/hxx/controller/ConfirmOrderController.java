package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.ConfirmOrderEntity;
import com.hxx.mq.BeforeConfirmOrderService;
import com.hxx.service.ConfirmOrderService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirmOrder")
public class ConfirmOrderController {

    @Autowired
    private ConfirmOrderService confirmOrderService;

    @Autowired
    private BeforeConfirmOrderService beforeConfirmOrderService;
    /**
     * 查询订单
     */
    @GetMapping("confirmOrder")
    public R confirmOrder(Page page, ConfirmOrderEntity confirmOrderEntity){
        confirmOrderService.getConfirmOrderList(page,confirmOrderEntity);
        return R.ok().data("list",page.getRecords()).data("total",page.getTotal());
    }

    /**
     * 保存订单信息
     */
    @PostMapping("saveConfirmOrder")
    @Transactional
    public R save(@RequestBody ConfirmOrderEntity confirmOrderEntity){
        //预处理订单
        beforeConfirmOrderService.beforeConfirmOrder(confirmOrderEntity);
        //提交订单
        return R.ok();
    }
}




























