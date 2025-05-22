package org.winter.springinit.utils;

import org.winter.springinit.domain.vo.user.UserVO;

public class UserHolder {

    private static final ThreadLocal<UserVO> userVOThreadLocal = new ThreadLocal<>();

    public static UserVO getUser() {
        return userVOThreadLocal.get();
    }

    public static void setUser(UserVO user) {
        userVOThreadLocal.set(user);
    }

}
