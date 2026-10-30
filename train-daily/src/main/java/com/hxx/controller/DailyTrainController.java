package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.DailyTrainEntity;
import com.hxx.service.DailyTrainService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/dailyTrain")
public class DailyTrainController {
    @Autowired
    private DailyTrainService dailyTrainService;

    @GetMapping("loadPage")
    public R loadPage(Page page, DailyTrainEntity dailyTrainEntity) {
        dailyTrainService.getDailyTrainList(page, dailyTrainEntity);
        return R.ok().data("list",page.getRecords())
                .data("total",page.getTotal());
    }

    @PostMapping("save")
    public R save(@RequestBody DailyTrainEntity dailyTrainEntity) {
        dailyTrainService.save(dailyTrainEntity);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody DailyTrainEntity dailyTrainEntity) {
        dailyTrainService.updateById(dailyTrainEntity);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Long id) {
        dailyTrainService.removeById(id);
        return R.ok();
    }

    @GetMapping("gen_daily/{date}")
    public R genDaily(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")Date date) {
        dailyTrainService.genDaily(date);
        return R.ok();
    }
}


























