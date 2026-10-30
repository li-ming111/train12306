package com.hxx.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hxx.context.LoginMemberContext;
import com.hxx.dto.MemberInfo;
import com.hxx.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MemberInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StrUtil.isNotBlank(token) && !"null".equals(token)) {
            JSONObject claims = JwtUtils.getClaims(token);
            MemberInfo memberInfo = JSONUtil.toBean(claims, MemberInfo.class);
            LoginMemberContext.setMember(memberInfo);
        }
        return true;
    }
}
