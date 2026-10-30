package com.hxx.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Integer code;
    private String msg;
    private Map<String,Object> data = new HashMap<>();
    private Boolean SUCCESS;

    private R(){}

    public static R ok(){
        R r = new R();
        r.setSUCCESS(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMsg("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setSUCCESS(false);
        r.setCode(ResultCode.ERROR);
        r.setMsg("失败");
        return r;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public R msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }
}
































