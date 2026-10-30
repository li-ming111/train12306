package com.hxx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.StationEntity;
import com.hxx.service.StationService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    private StationService stationService;

    @GetMapping("page")
    public R getStationList(Page page, StationEntity stationEntity) {
        stationService.getStationList(page, stationEntity);
        return R.ok().data("list",page.getRecords() ).data("total",page.getTotal());
    }

    @PostMapping("/save")
    public R save(@RequestBody StationEntity stationEntity) {
        stationService.save(stationEntity);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody StationEntity stationEntity) {
        stationService.updateById(stationEntity);
        return R.ok();
    }

    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Long id) {
        stationService.removeById(id);
        return R.ok();
    }

    @GetMapping("all")
    public R getAllStation() {
        return R.ok().data("list",stationService.list());
    }
}









































