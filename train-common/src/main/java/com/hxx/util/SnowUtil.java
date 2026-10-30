package com.hxx.util;

import cn.hutool.core.util.IdUtil;

/**
 * 工具类
 */
public class SnowUtil {
    // 数据中心、数据种子
    private static long dataCenterId = 1;
    // 机器id
    private static long machineId = 1;
    // 雪花算法
    public static long getSnowflakeId() {
        return IdUtil.getSnowflake().nextId();
    }
    // 雪花算法
    public static String getSnowflakeIdStr() {
        return IdUtil.getSnowflake().nextIdStr();
    }
}
