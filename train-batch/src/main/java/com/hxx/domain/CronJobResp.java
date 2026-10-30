package com.hxx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)//忽略为null的字段
@Data
@ToString
public class CronJobResp {
    //任务分组
    private String group;
    //任务名称
    private String name;
    //任务描述
    private String description;
    //cron表达式
    private String cronExpression;
    //状态
    private String state;
    //下次执行时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date nextFireTime;
    //上次执行时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date prevFireTime;
}
