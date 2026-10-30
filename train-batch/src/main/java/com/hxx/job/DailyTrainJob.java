package com.hxx.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.hxx.service.TrainDailyFeign;
import jakarta.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

import java.util.Date;

@DisallowConcurrentExecution
public class DailyTrainJob implements Job {

    @Resource
    private TrainDailyFeign trainDailyFeign;

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(DailyTrainJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("生成15天后车次数据开始");
        Date date = new Date();
        //15天偏移量
        DateTime dateTime = DateUtil.offsetDay(date, 15);
        trainDailyFeign.genDaily(dateTime.toJdkDate());
        log.info("生成15天后车次数据结束");
    }
}
