package org.winter.springinit.domain.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.winter.springinit.common.PageRequest;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：0-普通用户，1-管理员
     */
    private Integer userRole;

    @Serial
    private static final long serialVersionUID = 1L;
}