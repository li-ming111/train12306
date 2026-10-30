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
@TableName("qrtz_job_details")
public class QrtzJobDetailsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 定时任务名称
	 */
	@TableId
	private String schedName;
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
	 * job类名
	 */
	private String jobClassName;
	/**
	 * 是否持久化
	 */
	private String isDurable;
	/**
	 * 是否非同步
	 */
	private String isNonconcurrent;
	/**
	 * 是否更新数据
	 */
	private String isUpdateData;
	/**
	 * 请求是否覆盖
	 */
	private String requestsRecovery;
	/**
	 * job数据
	 */
	private Blob jobData;

}
