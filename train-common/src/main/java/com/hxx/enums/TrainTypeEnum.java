package com.hxx.enums;

import java.math.BigDecimal;
import java.util.*;

public enum TrainTypeEnum {

    G("G", "高铁", new BigDecimal("1.2")),
    D("D", "动车", new BigDecimal("1")),
    K("K", "快速", new BigDecimal("0.8"));

    private String code;
    private String desc;
    /**
     * 票价比例，例：1.1，则票价 = 1.1 * 每公里单价（SeatTypeEnum.price） * 公里（station.km）
     */
    private BigDecimal priceRate;

    private TrainTypeEnum(String code,String desc,BigDecimal priceRate){
        this.code = code;
        this.desc = desc;
        this.priceRate = priceRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(BigDecimal priceRate) {
        this.priceRate = priceRate;
    }
    //获取枚举数据
    public static List<Map<String,String>> getEnumList(){
        List<Map<String,String>> list = new ArrayList<>();
        for (TrainTypeEnum anEnum : EnumSet.allOf(TrainTypeEnum.class)) {
            Map<String,String> map = new HashMap<>();
            map.put("code",anEnum.code);
            map.put("desc",anEnum.desc);
            list.add(map);
        }
        return list;
    }
}
