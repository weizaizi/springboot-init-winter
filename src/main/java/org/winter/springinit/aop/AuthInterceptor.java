package org.winter.springinit.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.winter.springinit.annotation.AuthCheck;
import org.winter.springinit.common.ErrorCode;
import org.winter.springinit.constant.UserConstant;
import org.winter.springinit.domain.vo.user.UserVO;
import org.winter.springinit.exception.BusinessException;
import org.winter.springinit.service.UserService;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthInterceptor  {

    private final UserService userService;

    /**
     * 执行拦截
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        int role = authCheck.value();
        // 当前登录用户
        UserVO loginUser = userService.getLoginUser();
        // 如果被封号，直接拒绝
        if(Objects.equals(loginUser.getUserStatus(), UserConstant.USER_STATUS_BAD)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR , "账号异常");
        }
        // 必须有管理员权限
        if (UserConstant.USER_ADMIN != role) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "权限不足");
        }

        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}
