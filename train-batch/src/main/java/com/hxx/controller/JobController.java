package com.hxx.controller;

import com.hxx.domain.CronJobReq;
import com.hxx.domain.CronJobResp;
import com.hxx.util.R;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class JobController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(JobController.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 启动指定的定时任务。
     *
     * 通过接收CronJobReq对象作为请求体，从中获取任务名称和组别，
     * 然后触发对应的任务执行。此方法用于在接收到请求时，立即启动一个
     * 已经配置好的定时任务，而不需要等待下一次触发时间。
     *
     * @param cronJobReq 包含任务名称和组别的请求对象。
     * @return 返回一个表示操作成功的响应对象。
     * @throws SchedulerException 如果触发任务时发生错误。
     */
    /**
     * 启动任务
     */
    @RequestMapping("/run")
    public R run(@RequestBody CronJobReq cronJobReq) throws SchedulerException {
        logger.info("启动任务");
        // 从请求对象中获取任务名称和组别
        String jobReqName = cronJobReq.getName();
        String jobReqGroup = cronJobReq.getGroup();
        // 触发指定名称和组别的任务
        schedulerFactoryBean.getScheduler().triggerJob(JobKey.jobKey(jobReqName, jobReqGroup));
        // 返回操作成功的响应对象
        return R.ok();
    }

    /**
     * 创建一个定时任务。
     * 通过@RequestBody注解将CronJobReq对象绑定到方法参数上，使得可以从HTTP请求体中直接获取到定时任务的配置信息。
     *
     * @param cronJobReq 包含定时任务详细信息的请求对象，如任务名称、分组、CRON表达式和描述。
     * @return 返回一个表示操作结果的对象，此处为R.ok()，表示操作成功。
     * @throws SchedulerException 如果调度器操作失败，则抛出此异常。
     */
    /**
     * 创建定时任务
     */
    @RequestMapping("/add")
    public R add(@RequestBody CronJobReq cronJobReq) throws SchedulerException {
        // 从请求对象中提取定时任务的名称、分组、CRON表达式和描述信息
        // 从请求对象中获取任务名称和组别
        String jobReqName = cronJobReq.getName();
        String jobReqGroup = cronJobReq.getGroup();
        String cronExpression = cronJobReq.getCronExpression();
        String description = cronJobReq.getDescription();
        // 记录创建定时任务的日志信息
        logger.info("创建定时任务:{},{},{},{}", jobReqName, jobReqGroup, cronExpression, description);

        try {
            // 获取调度器实例
            var scheduler = schedulerFactoryBean.getScheduler();
            // 启动调度器
            scheduler.start();
            // 根据任务名称和分组创建JobDetail对象，指定任务执行的类通过Class.forName动态获取
            JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(jobReqName))
                    .withIdentity(jobReqName, jobReqGroup).build();
            // 根据CRON表达式创建CronScheduleBuilder
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 根据任务名称、分组、调度策略和描述创建Trigger对象
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobReqName, jobReqGroup)
                    .withSchedule(scheduleBuilder).withDescription(description).build();
            // 将JobDetail和Trigger注册到调度器中
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e) {
            // 如果任务执行类找不到，则打印堆栈跟踪
            e.printStackTrace();
        } catch (Exception e) {
            // 其他异常处理
            e.printStackTrace();
        }
        // 返回操作成功的响应
        return R.ok();
    }

    /**
     * 暂停任务
     */
    @RequestMapping("/pause")
    public R pause(@RequestBody CronJobReq cronJobReq) throws SchedulerException {
        // 从请求对象中获取任务名称和组别
        String jobReqName = cronJobReq.getName();
        String jobReqGroup = cronJobReq.getGroup();
        logger.info("暂停任务:{},{}", jobReqName, jobReqGroup);
        // 暂停指定名称和组别的任务
        schedulerFactoryBean.getScheduler().pauseJob(JobKey.jobKey(jobReqName, jobReqGroup));
        logger.info("暂停任务成功");
        // 返回操作成功的响应对象
        return R.ok();
    }

    /**
     * 恢复任务
     */
    @RequestMapping("/resume")
    public R resume(@RequestBody CronJobReq cronJobReq) throws SchedulerException {
        // 从请求对象中获取任务名称和组别
        String jobReqName = cronJobReq.getName();
        String jobReqGroup = cronJobReq.getGroup();
        logger.info("恢复任务:{},{}", jobReqName, jobReqGroup);
        // 恢复指定名称和组别的任务
        schedulerFactoryBean.getScheduler().resumeJob(JobKey.jobKey(jobReqName, jobReqGroup));
        logger.info("恢复任务成功");
        return R.ok();
    }

    /**
     * 更新任务调度
     * 通过接收CronJobReq对象，更新已存在的任务的调度信息，包括cron表达式和描述。
     * 如果任务不存在，则抛出SchedulerException。
     *
     * @param cronJobReq 包含任务名称、组别、cron表达式和描述的请求对象。
     * @return 返回一个表示操作结果的对象。
     * @throws SchedulerException 如果任务不存在或者调度器操作失败，则抛出此异常。
     */
    /**
     * 更新任务
     */
    @RequestMapping("/reschedule")
    public R reschedule(@RequestBody CronJobReq cronJobReq) throws SchedulerException {
        // 从请求对象中提取任务名称、组别、cron表达式和描述
        // 从请求对象中获取任务名称和组别
        String jobReqName = cronJobReq.getName();
        String jobReqGroup = cronJobReq.getGroup();
        String cronExpression = cronJobReq.getCronExpression();
        String description = cronJobReq.getDescription();
        // 记录更新任务的信息
        logger.info("更新任务:{},{},{}", jobReqName, jobReqGroup, cronExpression);
        // 获取调度器实例
        // 获取调度器实例
        var scheduler = schedulerFactoryBean.getScheduler();
        // 根据任务名称和组别创建TriggerKey
        TriggerKey triggerKey = TriggerKey.triggerKey(jobReqName, jobReqGroup);
        try {
            // 创建基于cron表达式的调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            // 根据TriggerKey获取现有的CronTrigger
            CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
            // 如果触发器不存在，则抛出异常
            if (trigger == null) {
                throw new SchedulingException("获取定时任务失败");
            }
            // 设置触发器的开始时间
            trigger.setStartTime(new Date());
            // 更新触发器的描述和调度信息
            CronTrigger cronTrigger = trigger;
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(description)
                    .withSchedule(scheduleBuilder).build();
            // 重新调度任务
            scheduler.rescheduleJob(triggerKey, cronTrigger);
            // 记录更新成功的日志
            logger.info("更新任务成功");
        } catch (Exception e) {
            // 打印异常堆栈跟踪
            e.printStackTrace();
        }
        // 返回操作成功的响应
        return R.ok();
    }

    /**
     * 删除任务
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody CronJobReq cronJobReq) throws SchedulerException {
        // 从请求对象中获取任务名称和组别
        String jobReqName = cronJobReq.getName();
        String jobReqGroup = cronJobReq.getGroup();
        logger.info("删除任务:{},{}", jobReqName, jobReqGroup);
        // 删除指定名称和组别的任务
        schedulerFactoryBean.getScheduler().deleteJob(JobKey.jobKey(jobReqName, jobReqGroup));
        logger.info("删除任务成功");
        return R.ok();
    }

    /**
     * 获取任务列表
     */
    @RequestMapping("/query")
    public List<CronJobResp> all() throws SchedulerException {
        logger.info("获取任务列表");
        //获取任务列表
        List<CronJobResp> cronJobRespList = new ArrayList<>();
        //获取任务调度对象
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //遍历任务组
        for (String groupName : scheduler.getJobGroupNames()) {
        //遍历任务
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                //封装任务信息
                CronJobResp cronJobResp = new CronJobResp();
                //任务组
                cronJobResp.setGroup(groupName);
                //任务名称
                cronJobResp.setName(jobKey.getName());
                //获取触发器集合
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                //获取触发器
                CronTrigger trigger = (CronTrigger)triggers.get(0);
                //下次执行时间
                cronJobResp.setNextFireTime(trigger.getNextFireTime());
                //上次执行时间
                cronJobResp.setPrevFireTime(trigger.getPreviousFireTime());
                //cron表达式
                cronJobResp.setCronExpression(trigger.getCronExpression());
                //描述
                cronJobResp.setDescription(trigger.getDescription());
                //状态名称
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                cronJobResp.setState(triggerState.name());
                //添加到集合中
                cronJobRespList.add(cronJobResp);
            }
        }
        return cronJobRespList;
    }
}


























