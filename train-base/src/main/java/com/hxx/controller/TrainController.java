package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.TrainEntity;
import com.hxx.service.SeatService;
import com.hxx.service.TrainService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    private TrainService trainService;
    @Autowired
    private SeatService seatService;

    @GetMapping("page")
    public R getTrainList(Page page, TrainEntity trainEntity) {
        trainService.getTrainList(page,trainEntity);
        return R.ok().data("list",page.getRecords()).data("total",page.getTotal());
    }

    @PostMapping("save")
    public R save(@RequestBody TrainEntity trainEntity) {
        trainService.save(trainEntity);
        return R.ok();
    }

    @PutMapping("update")
    public R update(@RequestBody TrainEntity trainEntity) {
        trainService.updateById(trainEntity);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable Long id) {
        trainService.removeById(id);
        return R.ok();
    }

    @GetMapping("allTrain")
    public R  allTrain() {
        return R.ok().data("list",trainService.list());
    }
    @GetMapping("all")
    public List<TrainEntity> getAllTrain() {
        return trainService.list();
    }

    @GetMapping("gen/{trainCode}")
    public R genTrain(@PathVariable String trainCode) {
        seatService.genTrainSeat(trainCode);
        return R.ok();
    }
}


























