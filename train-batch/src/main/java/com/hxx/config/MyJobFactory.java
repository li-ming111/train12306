package com.hxx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;
import org.quartz.spi.TriggerFiredBundle;

@Component
public class MyJobFactory extends SpringBeanJobFactory {

    // 注入AutowireCapableBeanFactory
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    /**
     * 创建作业实例。
     * 重写了父类方法，以便在Spring上下文中初始化作业实例。
     *
     * @param bundle 触发器触发时携带的bundle，包含作业和触发器的相关信息。
     * @return 创建的作业实例。
     * @throws Exception 如果创建作业实例过程中出现异常。
     */
    // 重写createJobInstance方法
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类方法创建作业实例。
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        // 将作业实例注入到Spring上下文中，自动装配其依赖。
        // 将jobInstance添加到spring容器中
        beanFactory.autowireBean(jobInstance);
        return jobInstance;
    }

}


























