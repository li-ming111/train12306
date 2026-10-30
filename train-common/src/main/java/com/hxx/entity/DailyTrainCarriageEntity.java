package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 每日车厢
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("daily_train_carriage")
public class DailyTrainCarriageEntity implements Serializable {
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
	@TableField(value = "`index`")
	private Integer index;
	/**
	 * 座位类型|枚举[SeatTypeEnum]
	 */
	private String seatType;
	/**
	 * 座位数
	 */
	private Integer seatCount;
	/**
	 * 排数
	 */
	private Integer rowCount;
	/**
	 * 列数
	 */
	private Integer colCount;


}
