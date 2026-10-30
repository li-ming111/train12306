package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 座位
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("train_seat")
public class TrainSeatEntity implements Serializable {
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
	 * 厢序
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
	 * 同车厢座序
	 */
	private Integer carriageSeatIndex;
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
