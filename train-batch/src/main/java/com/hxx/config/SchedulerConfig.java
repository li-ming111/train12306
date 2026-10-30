package com.hxx.config;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
public class SchedulerConfig {
    @Resource
    private MyJobFactory myJobFactory;

    /**
     * 配置Quartz调度器的工厂 bean。
     *
     * @param dataSource 用于Quartz调度器的数据库源，指定特定的数据库源以供Quartz使用。
     * @return SchedulerFactoryBean 实例，配置了自定义的工作单元工厂和数据源。
     *
     * @Bean 注解表明该方法将返回一个被Spring管理的bean实例。
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("dataSource")DataSource dataSource) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 设置自定义的工作单元工厂，以支持Spring管理的bean作为任务执行。
        schedulerFactoryBean.setJobFactory(myJobFactory);
        // 配置数据源，使Quartz能够使用数据库存储作业和触发器信息。
        schedulerFactoryBean.setDataSource(dataSource);
        // 设置调度器在启动时的延迟时间，单位为秒，这里设置为2秒后启动。
        schedulerFactoryBean.setStartupDelay(2);
        return schedulerFactoryBean;
    }

}
