package com.hxx.service;

import com.hxx.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@FeignClient("trainDaily")
public interface TrainDailyFeign {

    @GetMapping("/dailyTrain/gen_daily/{date}")
    public R genDaily(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
}
