package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("qrtz_simprop_triggers")
public class QrtzSimpropTriggersEntity implements Serializable {
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
	 * 开始配置1
	 */
	private String strProp1;
	/**
	 * 开始配置2
	 */
	private String strProp2;
	/**
	 * 开始配置3
	 */
	private String strProp3;
	/**
	 * int配置1
	 */
	private Integer intProp1;
	/**
	 * int配置2
	 */
	private Integer intProp2;
	/**
	 * long配置1
	 */
	private Long longProp1;
	/**
	 * long配置2
	 */
	private Long longProp2;
	/**
	 * 配置描述1
	 */
	private BigDecimal decProp1;
	/**
	 * 配置描述2
	 */
	private BigDecimal decProp2;
	/**
	 * bool配置1
	 */
	private String boolProp1;
	/**
	 * bool配置2
	 */
	private String boolProp2;

}
