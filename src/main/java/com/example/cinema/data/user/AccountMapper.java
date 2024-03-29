package com.example.cinema.data.user;

import com.example.cinema.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Repository
@Mapper
public interface AccountMapper {

    /**
     * 创建一个新的账号
     * @param user
     * @return
     */
    public int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     * @param username
     * @return
     */
    public User getAccountByName(@Param("username") String username);
}
