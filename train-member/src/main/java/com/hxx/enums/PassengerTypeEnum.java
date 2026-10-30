package com.hxx.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum PassengerTypeEnum {

    ADULT(1,"成人"),
    CHILD(2,"儿童"),
    STUDENT(3,"学生");

    private Integer code;
    private String desc;

    PassengerTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * 获取乘客类型的枚举列表。
     * 该方法通过遍历PassengerTypeEnum枚举类的所有值，将每个值的code和desc存入HashMap中，
     * 然后将这些HashMap添加到一个List中，最后返回这个List。这样做的目的是为了以一种更方便的数据结构
     * （List<HashMap<String,Object>>）来存储和访问乘客类型的代码和描述。
     *
     * @return List<HashMap<String,Object>> 包含乘客类型代码和描述的列表。
     */
    public static List<HashMap<String,Object>> getEnumList(){
        // 获取PassengerTypeEnum枚举类的所有值
        PassengerTypeEnum[] values = PassengerTypeEnum.values();
        // 初始化一个List来存储HashMap
        List<HashMap<String,Object>> list = new ArrayList<>();
        // 遍历枚举值
        for (PassengerTypeEnum passengerTypeEnum : values) {
            // 初始化一个HashMap来存储每个枚举值的code和desc
            HashMap<String,Object> map = new HashMap<>();
            // 将枚举值的code和desc存入HashMap
            map.put("code",passengerTypeEnum.getCode());
            map.put("desc",passengerTypeEnum.getDesc());
            // 将HashMap添加到List中
            list.add(map);
        }
        // 返回包含所有枚举值的HashMap的List
        return list;
    }

}
