package com.hxx.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;

/**
 * jwt工具类
 */
public class JwtUtils {

    //秘钥
    private static final String JWT_SECRET = "hxx";

    /**
     * 创建JWT令牌
     *
     * @param id 用户ID，用于在令牌中标识用户
     * @param mobile 用户手机号，可用于额外的身份验证
     * @return 生成的JWT令牌字符串
     *
     * 本函数用于根据给定的用户ID和手机号创建一个JWT令牌。
     * 令牌中包含发行时间、过期时间、生效时间和用户ID、手机号等信息。
     * 使用预定义的JWT密钥对令牌进行签名，确保其安全性。
     */
    //创建token
    public static String createToken(Long id,String mobile){
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        // 获取当前时间
        DateTime now = new DateTime();
        // 计算令牌的过期时间，为当前时间后24小时
        DateTime expTime = now.offsetNew(DateField.HOUR, 24);
        // 初始化令牌的载荷信息
        HashMap<String, Object> payload = new HashMap<>();
        // 设置令牌的发行时间
        payload.put(JWTPayload.ISSUED_AT,now);
        // 设置令牌的过期时间
        payload.put(JWTPayload.EXPIRES_AT,expTime);
        // 设置令牌的生效时间，为当前时间
        payload.put(JWTPayload.NOT_BEFORE,now);
        // 设置用户ID和手机号作为自定义载荷信息
        payload.put("id",id);
        payload.put("mobile",mobile);
        // 使用载荷信息和密钥生成JWT令牌
        return JWTUtil.createToken(payload,JWT_SECRET.getBytes());
    }

    /**
     * 验证JWT令牌的有效性。
     *
     * 本函数用于解析并验证一个JWT令牌的合法性。它首先使用JWTUtil解析令牌，然后设置秘钥，
     * 最后通过调用JWT的validate方法，检查令牌是否在有效期内。
     * 注意，这里将验证过期时间设为0，意味着只检查签名有效性，不检查实际过期时间。
     *
     * @param token 待验证的JWT令牌字符串。
     * @return 如果令牌有效（签名验证通过），返回true；否则返回false。
     */
    public static boolean verify(String token){
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = JWTUtil.parseToken(token).setKey(JWT_SECRET.getBytes());
        return jwt.validate(0);
    }

    /**
     * 从JWT令牌中提取声明信息。
     *
     * 该方法用于解析JWT令牌，并从中移除与时间相关的声明，返回剩余的声明信息。
     * 时间相关的声明包括：发行时间、过期时间和生效时间。
     * 这样做的目的是为了保护敏感信息的隐私或减少信息泄露的风险。
     *
     * @param token 待解析的JWT令牌字符串。
     * @return 包含JWT令牌中除时间声明外的所有声明的JSONObject。
     */
    public static JSONObject getClaims(String token){
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        // 解析JWT令牌并设置解密密钥
        JWT jwt = JWTUtil.parseToken(token).setKey(JWT_SECRET.getBytes());
        // 获取JWT令牌的载荷部分
        JSONObject payloads = jwt.getPayloads();
        // 移除时间相关的声明
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        // 返回处理后的载荷信息
        return payloads;
    }
}

















