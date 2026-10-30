package com.hxx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.TrainStationEntity;
import com.hxx.service.TrainStationService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainStation")
public class TrainStationController {
    @Autowired
    private TrainStationService trainStationService;

    @GetMapping("page")
    public R getTrainStationList(Page page, TrainStationEntity trainStationEntity) {
        trainStationService.getTrainStationList(page, trainStationEntity);
        return R.ok().data("list",page.getRecords()).data("total",page.getTotal());
    }

    @PostMapping("/save")
    public R save(@RequestBody TrainStationEntity trainStationEntity) {
        trainStationService.save(trainStationEntity);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody TrainStationEntity trainStationEntity) {
        trainStationService.updateById(trainStationEntity);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Long id) {
        trainStationService.removeById(id);
        return R.ok();
    }

    @GetMapping("/trainCode")
    public List<TrainStationEntity> trainCodeStation(@RequestParam("trainCode") String trainCode){
        QueryWrapper<TrainStationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("train_code",trainCode);
        return trainStationService.list(wrapper);
    }
}

















