package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 火车车站
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("train_station")
public class TrainStationEntity implements Serializable {
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
	 * 站序
	 */
	@TableField(value = "`index`")
	private Integer index;
	/**
	 * 站名
	 */
	private String name;
	/**
	 * 站名拼音
	 */
	private String namePinyin;
	/**
	 * 进站时间
	 */
	private Time inTime;
	/**
	 * 出站时间
	 */
	private Time outTime;
	/**
	 * 停站时长
	 */
	private Time stopTime;
	/**
	 * 里程（公里）|从上一站到本站的距离
	 */
	private BigDecimal km;
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
