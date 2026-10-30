package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 每日车次
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("daily_train")
public class DailyTrainEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type= IdType.ASSIGN_ID)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	/**
	 * 日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	/**
	 * 车次编号
	 */
	private String code;
	/**
	 * 车次类型|枚举[TrainTypeEnum]
	 */
	private String type;
	/**
	 * 始发站
	 */
	private String start;
	/**
	 * 始发站拼音
	 */
	private String startPinyin;
	/**
	 * 出发时间
	 */
	private Time startTime;
	/**
	 * 终点站
	 */
	private String end;
	/**
	 * 终点站拼音
	 */
	private String endPinyin;
	/**
	 * 到站时间
	 */
	private Time endTime;
	/**
	 * 新增时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

}
