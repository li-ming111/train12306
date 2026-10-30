package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Blob;
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
@TableName("qrtz_triggers")
public class QrtzTriggersEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 定时任务名称
	 */
	@TableId
	private String schedName;
	/**
	 * 触发器名称
	 */
	private String triggerName;
	/**
	 * 触发器组
	 */
	private String triggerGroup;
	/**
	 * job名称
	 */
	private String jobName;
	/**
	 * job组
	 */
	private String jobGroup;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 下一次触发时间
	 */
	private Long nextFireTime;
	/**
	 * 前一次触发时间
	 */
	private Long prevFireTime;
	/**
	 * 等级
	 */
	private Integer priority;
	/**
	 * 触发状态
	 */
	private String triggerState;
	/**
	 * 触发类型
	 */
	private String triggerType;
	/**
	 * 开始时间
	 */
	private Long startTime;
	/**
	 * 结束时间
	 */
	private Long endTime;
	/**
	 * 日程名称
	 */
	private String calendarName;
	/**
	 * 未触发实例
	 */
	private Integer misfireInstr;
	/**
	 * job数据
	 */
	private Blob jobData;

}
