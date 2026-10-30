package com.hxx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.TrainCarriageEntity;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hxx.service.TrainCarriageSerivce;

import java.util.List;

@RestController
@RequestMapping("/trainCarriage")
public class TrainCarriageController {
    @Autowired
    private TrainCarriageSerivce trainCarriageService;

    @GetMapping("page")
    public R getTrainCarriageList(Page page, TrainCarriageEntity trainCarriageEntity) {
        trainCarriageService.getTrainCarriageList(page, trainCarriageEntity);
        return R.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @PostMapping("/save")
    public R save(@RequestBody TrainCarriageEntity trainCarriageEntity) {
        trainCarriageService.save(trainCarriageEntity);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody TrainCarriageEntity trainCarriageEntity) {
        trainCarriageService.updateById(trainCarriageEntity);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Long id) {
        trainCarriageService.removeById(id);
        return R.ok();
    }

    @GetMapping("findAll")
    public List<TrainCarriageEntity> findAll(@RequestParam String trainCode) {
        return trainCarriageService
                .list(new LambdaQueryWrapper<TrainCarriageEntity>()
                        .eq(TrainCarriageEntity::getTrainCode, trainCode));
    }
}































