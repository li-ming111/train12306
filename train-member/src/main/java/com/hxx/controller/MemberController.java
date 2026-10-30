package com.hxx.controller;

import com.hxx.dto.MemberInfo;
import com.hxx.entity.MemberEntity;
import com.hxx.service.MemberService;
import com.hxx.util.JwtUtils;
import com.hxx.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    private String code;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody MemberInfo info){
        MemberEntity login = memberService.login(info.getMobile());
        //if (login!=null && code.equals(info.getCode())){
            //info.setId(login.getId());
            info.setToken(JwtUtils.createToken(info.getId(),info.getMobile()));
            return R.ok().data("member",info);
        //}
       // return R.error();
    }

    //随机生成验证码
    private String code(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            code +=random.nextInt(10);
        }
        return code;
    }

    //发送验证码
    @GetMapping("/sendCode")
    public R sendCode(String mobile){
        code = code();
        return R.ok().data("code", code);
    }

    @GetMapping("/member/info")
    public R info(){
        return R.ok().data("mobile", "13800000002");
    }
}
