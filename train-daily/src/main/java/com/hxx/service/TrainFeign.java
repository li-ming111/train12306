package com.hxx.service;

import com.hxx.entity.TrainCarriageEntity;
import com.hxx.entity.TrainEntity;
import com.hxx.entity.TrainSeatEntity;
import com.hxx.entity.TrainStationEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("trainBase")
public interface TrainFeign {
    @GetMapping("/train/all")
    List<TrainEntity> trainAll();

    @GetMapping("/trainStation/trainCode")
    public List<TrainStationEntity> trainCodeStation(@RequestParam("trainCode") String trainCode);

    @GetMapping("/trainCarriage/findAll")
    List<TrainCarriageEntity> findAll(@RequestParam String trainCode);

    @GetMapping("/seat/load/{trainCode}")
    public List<TrainSeatEntity> load(@PathVariable String trainCode);
}
