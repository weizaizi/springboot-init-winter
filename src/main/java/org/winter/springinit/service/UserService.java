package org.winter.springinit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.winter.springinit.domain.dto.user.UserQueryRequest;
import org.winter.springinit.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.winter.springinit.domain.vo.user.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author winter
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2025-05-21 14:06:03
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    String userLogin(String userAccount, String userPassword);

    UserVO getLoginUserVO(User user);


    UserVO getLoginUser();

    Wrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    List<UserVO> getUserVO(List<User> records);

    UserVO getUserVO(User user);

    boolean userLogout(HttpServletRequest request);
}
