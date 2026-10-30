package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("qrtz_fired_triggers")
public class QrtzFiredTriggersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 定时任务名称
	 */
	@TableId
	private String schedName;
	/**
	 * entryId
	 */
	private String entryId;
	/**
	 * 触发器名称
	 */
	private String triggerName;
	/**
	 * 触发器组
	 */
	private String triggerGroup;
	/**
	 * 实例名称
	 */
	private String instanceName;
	/**
	 * 执行时间
	 */
	private Long firedTime;
	/**
	 * 定时任务时间
	 */
	private Long schedTime;
	/**
	 * 等级
	 */
	private Integer priority;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * job名称
	 */
	private String jobName;
	/**
	 * job组
	 */
	private String jobGroup;
	/**
	 * 是否异步
	 */
	private String isNonconcurrent;
	/**
	 * 是否请求覆盖
	 */
	private String requestsRecovery;

}
