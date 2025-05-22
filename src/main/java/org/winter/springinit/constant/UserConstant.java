package org.winter.springinit.constant;

/**
 * 用户常量
 */
public interface UserConstant {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user:login:";

    Integer USER_LOGIN_TTL = 30;

    String LOGIN_HEADER = "authority";

    int USER_ADMIN = 1;

    Integer USER_STATUS_BAD= 1;
}
