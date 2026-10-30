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
@TableName("qrtz_scheduler_state")
public class QrtzSchedulerStateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 定时任务名称
	 */
	@TableId
	private String schedName;
	/**
	 * 实例名称
	 */
	private String instanceName;
	/**
	 * 最近检入时间
	 */
	private Long lastCheckinTime;
	/**
	 * 检入间隔
	 */
	private Long checkinInterval;

}
