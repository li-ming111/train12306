package com.hxx.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 会员
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2023-04-28 10:56:55
 */
@Data
@TableName("member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId(type= IdType.ASSIGN_ID)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;

	/**
	 * 手机号
	 */
	@NotBlank(message = "手机号码不能为空")
	private String mobile = "13936439780";
}
