package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.DailyTrainSeatEntity;
import com.hxx.service.DailyTrainSeatService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dailyTrainSeat")
public class DailyTrainSeatController {
    @Autowired
    private DailyTrainSeatService dailyTrainSeatService;

    @GetMapping("loadPage")
    public R loadPage(Page page, DailyTrainSeatEntity dailyTrainSeatEntity) {
        dailyTrainSeatService.getDailyTrainSeatList(page, dailyTrainSeatEntity);
        return R.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @PostMapping("save")
    public R save(@RequestBody DailyTrainSeatEntity dailyTrainSeatEntity) {
        dailyTrainSeatService.save(dailyTrainSeatEntity);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody DailyTrainSeatEntity dailyTrainSeatEntity) {
        dailyTrainSeatService.updateById(dailyTrainSeatEntity);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Long id) {
        dailyTrainSeatService.removeById(id);
        return R.ok();
    }
}
