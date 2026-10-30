package com.hxx.config;

import com.hxx.interceptor.MemberInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    // 注入拦截器
    @Resource
    private MemberInterceptor memberInterceptor;

    /**
     * 添加拦截器到拦截器注册表中。
     *
     * 此方法用于配置拦截器，使其拦截所有请求，除了特定的登录和发送验证码接口。
     * 通过拦截器，可以实现一些全局的功能或逻辑，比如权限验证、日志记录等。
     *
     * @param registry 拦截器注册表，用于添加和管理拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加memberInterceptor拦截器，并配置拦截所有请求
        registry.addInterceptor(memberInterceptor)
                // 指定拦截器应该拦截的路径模式，这里设置为拦截所有路径
                .addPathPatterns("/**")
                // 排除登录和发送验证码接口，这些接口不需要被拦截器拦截
                .excludePathPatterns("/trainMember/login","/trainMember/sendCode");
    }

}
