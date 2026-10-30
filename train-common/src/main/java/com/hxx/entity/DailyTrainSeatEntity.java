package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 每日座位
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("daily_train_seat")
public class DailyTrainSeatEntity implements Serializable {
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
	 * 箱序
	 */
	private Integer carriageIndex;
	/**
	 * 排号|01, 02
	 */
	@TableField(value = "`row`")
	private String row;
	/**
	 * 列号|枚举[SeatColEnum]
	 */
	@TableField(value = "`col`")
	private String col;
	/**
	 * 座位类型|枚举[SeatTypeEnum]
	 */
	private String seatType;
	/**
	 * 同车箱座序
	 */
	private Integer carriageSeatIndex;
	/**
	 * 售卖情况|将经过的车站用01拼接，0表示可卖，1表示已卖
	 */
	private String sell;

}
