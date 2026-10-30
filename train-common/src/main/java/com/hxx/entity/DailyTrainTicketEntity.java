package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 余票信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("daily_train_ticket")
public class DailyTrainTicketEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type= IdType.ASSIGN_ID)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
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
	/**
	 * 日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	/**
	 * 车次编号
	 */
	private String trainCode;
	/**
	 * 出发站
	 */
	private String start;
	/**
	 * 出发站拼音
	 */
	private String startPinyin;
	/**
	 * 出发时间
	 */
	private Time startTime;
	/**
	 * 出发站序|本站是整个车次的第几站
	 */
	private Integer startIndex;
	/**
	 * 到达站
	 */
	private String end;
	/**
	 * 到达站拼音
	 */
	private String endPinyin;
	/**
	 * 到站时间
	 */
	private Time endTime;
	/**
	 * 到站站序|本站是整个车次的第几站
	 */
	private Integer endIndex;
	/**
	 * 一等座余票
	 */
	private Integer ydz;
	/**
	 * 一等座票价
	 */
	private BigDecimal ydzPrice;
	/**
	 * 二等座余票
	 */
	private Integer edz;
	/**
	 * 二等座票价
	 */
	private BigDecimal edzPrice;
	/**
	 * 软卧余票
	 */
	private Integer rw;
	/**
	 * 软卧票价
	 */
	private BigDecimal rwPrice;
	/**
	 * 硬卧余票
	 */
	private Integer yw;
	/**
	 * 硬卧票价
	 */
	private BigDecimal ywPrice;
}
