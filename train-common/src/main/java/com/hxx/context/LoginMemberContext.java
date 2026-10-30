package com.hxx.context;

import com.hxx.dto.MemberInfo;

//会员信息存储线程中，从线程中获取会员信息
public class LoginMemberContext {

    private static final ThreadLocal<MemberInfo> MEMBER_THREAD_LOCAL = new ThreadLocal<>();

    public static void setMember(MemberInfo memberEntity) {
        MEMBER_THREAD_LOCAL.set(memberEntity);
    }

    public static MemberInfo getMember() {
        return MEMBER_THREAD_LOCAL.get();
    }

    public static void removeMember() {
        MEMBER_THREAD_LOCAL.remove();
    }
    //获取会员id
    public static Long getMemberId() {
        return MEMBER_THREAD_LOCAL.get().getId();
    }
}
