package com.hxx.dto;

import lombok.Data;

//存储会员信息
@Data
public class MemberInfo {
    private Long id;
    private String mobile;
    private String token;
    private String code;
}
