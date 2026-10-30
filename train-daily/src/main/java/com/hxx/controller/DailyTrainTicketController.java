package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.DailyTrainTicketEntity;
import com.hxx.service.DailyTrainSeatService;
import com.hxx.service.DailyTrainTicketService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailyTrainTicket")
public class DailyTrainTicketController {
    @Autowired
    private DailyTrainTicketService dailyTrainSeatService;

    @GetMapping("loadPage")
    public R loadPage(Page page, DailyTrainTicketEntity dailyTrainTicketEntity) {
        dailyTrainSeatService.loadPage(page, dailyTrainTicketEntity);
        return R.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @PostMapping("save")
    public R save(@RequestBody DailyTrainTicketEntity dailyTrainTicketEntity) {
        dailyTrainSeatService.save(dailyTrainTicketEntity);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody DailyTrainTicketEntity dailyTrainTicketEntity) {
        dailyTrainSeatService.updateById(dailyTrainTicketEntity);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Long id) {
        dailyTrainSeatService.removeById(id);
        return R.ok();
    }

}

































