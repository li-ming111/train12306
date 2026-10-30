package com.hxx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.TrainSeatEntity;
import com.hxx.service.SeatService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/page")
    public R getSeatList(Page page, TrainSeatEntity seatEntity) {
        seatService.getTrainList(page, seatEntity);
        return R.ok().data("list", page.getRecords()).data("total", page.getTotal());
    }

    @PostMapping("/save")
    public R save(@RequestBody TrainSeatEntity seatEntity) {
        seatService.save(seatEntity);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody TrainSeatEntity seatEntity) {
        seatService.updateById(seatEntity);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Long id) {
        seatService.removeById(id);
        return R.ok();
    }

    @GetMapping("/load/{trainCode}")
    public List<TrainSeatEntity> load(@PathVariable String trainCode) {
        return seatService.list(new QueryWrapper<TrainSeatEntity>()
                .eq("train_code", trainCode));
    }
}



















