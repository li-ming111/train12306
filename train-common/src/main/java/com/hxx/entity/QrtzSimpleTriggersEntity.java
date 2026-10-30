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
@TableName("qrtz_simple_triggers")
public class QrtzSimpleTriggersEntity implements Serializable {
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
	 * 重复执行次数
	 */
	private Long repeatCount;
	/**
	 * 重复执行间隔
	 */
	private Long repeatInterval;
	/**
	 * 已经触发次数
	 */
	private Long timesTriggered;

}
