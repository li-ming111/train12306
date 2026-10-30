package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 乘车人
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("passenger")
public class PassengerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type=IdType.ASSIGN_ID)
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long id;
	/**
	 * 会员id
	 */
	@TableField("member_id")
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private Long memberId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证
	 */
	@TableField("id_card")
	private String idCard;
	/**
	 * 旅客类型|枚举[PassengerTypeEnum]
	 */
	private String type;
	/**
	 * 新增时间
	 */
	@TableField(fill = FieldFill.INSERT,value = "create_Time")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE,value = "update_time")
	private Date updateTime;
}
