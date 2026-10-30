package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 车站
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("station")
public class StationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type=IdType.ASSIGN_ID)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;
	/**
	 * 站名
	 */
	private String name;
	/**
	 * 站名拼音
	 */
	private String namePinyin;
	/**
	 * 站名拼音首字母
	 */
	private String namePy;
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
