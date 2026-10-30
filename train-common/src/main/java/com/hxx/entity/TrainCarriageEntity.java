package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 火车车厢
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("train_carriage")
public class TrainCarriageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	/**
	 * 车次编号
	 */
	private String trainCode;
	/**
	 * 厢号
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
