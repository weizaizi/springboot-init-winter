package org.winter.springinit.interceptor;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.winter.springinit.domain.vo.user.UserVO;
import org.winter.springinit.utils.UserHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.winter.springinit.constant.UserConstant.*;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        String token = request.getHeader(LOGIN_HEADER);
        if (token == null) {
            response.setStatus(403);
            return false;
        }
        String key = USER_LOGIN_STATE + token;
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        UserVO userVO = BeanUtil.mapToBean(entries, UserVO.class, true, null);
        if (userVO == null) {
            response.setStatus(403);
            return false;
        }
        stringRedisTemplate.expire(key, USER_LOGIN_TTL, TimeUnit.MINUTES);
        UserHolder.setUser(userVO);
        return true;
    }
}
