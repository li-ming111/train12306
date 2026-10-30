package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.DailyTrainCarriageEntity;
import com.hxx.service.DailyTrainCarriageService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/dailyTrainCarriage")
public class DailyTrainCarriageController {
    @Autowired
    private DailyTrainCarriageService dailyTrainCarriageService;

    @GetMapping("loadPage")
    public R loadPage(Page page, DailyTrainCarriageEntity dailyTrainCarriageEntity) {
        dailyTrainCarriageService.getDailyTrainCarriageList(page, dailyTrainCarriageEntity);
        return R.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @PostMapping("save")
    public R save(@RequestBody DailyTrainCarriageEntity dailyTrainCarriageEntity) {
        dailyTrainCarriageService.saveCarriage(dailyTrainCarriageEntity);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody DailyTrainCarriageEntity dailyTrainCarriageEntity) {
        dailyTrainCarriageService.updateById(dailyTrainCarriageEntity);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Long id) {
        dailyTrainCarriageService.removeById(id);
        return R.ok();
    }

    @GetMapping("load/{trainCode}")
    public R load(@PathVariable String trainCode) {
        return R.ok().data("list", dailyTrainCarriageService.loadByTrainCode(trainCode));
    }

    @GetMapping("/genDailyCarriage")
    public void genDailyCarriage(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                 @RequestParam("trainCode") String trainCode) {
        dailyTrainCarriageService.genDaily(date, trainCode);
    }
}

























