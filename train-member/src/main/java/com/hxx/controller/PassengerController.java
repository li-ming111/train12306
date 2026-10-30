package com.hxx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxx.entity.PassengerEntity;
import com.hxx.service.PassengerService;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping("/save")
    public R save(@RequestBody PassengerEntity passengerEntity){
        passengerService.save(passengerEntity);
        return R.ok();
    }

    @PutMapping("/update")
    public R update(@RequestBody PassengerEntity passengerEntity){
        passengerService.updateById(passengerEntity);
        return R.ok();
    }
    @DeleteMapping("/delete")
    public R delete(Long id){
        passengerService.removeById(id);
        return R.ok();
    }
    @GetMapping("/page")
    public R getPassengerList(Page page,  PassengerEntity passengerEntity){
        passengerService.getPassengerList(page,passengerEntity);
        return R.ok()
                .data("list",page.getRecords())
                .data("total",page.getTotal());
    }

    @GetMapping("/show")
    public R show(){
        return R.ok().data("list",passengerService.list());
    }

    @GetMapping("/findByMemberId/{memberId}")
    public List<PassengerEntity> getPassengerByMemberId(@PathVariable Long memberId){
        return passengerService.list(new QueryWrapper<PassengerEntity>().eq("member_id",memberId));
    }
}
