package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.DailyTrainStationEntity;
import com.hxx.service.DailyTrainStationService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/dailyTrainStation")
public class DailyTrainStationController {

    @Autowired
    private DailyTrainStationService dailyTrainStationService;

    @GetMapping("loadPage")
    public R loadPage(Page page, DailyTrainStationEntity dailyTrainStationEntity) {
         dailyTrainStationService.getDailyTrainStationList(page, dailyTrainStationEntity);
        return R.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @PostMapping("save")
    public R save(@RequestBody DailyTrainStationEntity dailyTrainStationEntity) {
        dailyTrainStationService.save(dailyTrainStationEntity);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody DailyTrainStationEntity dailyTrainStationEntity) {
        dailyTrainStationService.updateById(dailyTrainStationEntity);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Long id) {
        dailyTrainStationService.removeById(id);
        return R.ok();
    }

    @GetMapping("genTrainStation")
    public void genTrainStation(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                             @RequestParam("trainCode") String trainCode) {

        dailyTrainStationService.genDaily(date, trainCode);
    }
}
