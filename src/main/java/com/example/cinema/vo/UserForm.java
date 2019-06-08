package com.example.cinema.vo;

import lombok.Data;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Data
public class UserForm {
    /**
     * 用户名，不可重复
     */
    private String username;
    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户身份
     */
    private String identity;

}
