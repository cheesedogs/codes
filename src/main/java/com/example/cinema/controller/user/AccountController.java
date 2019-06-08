package com.example.cinema.controller.user;

import com.example.cinema.blImpl.user.AccountServiceImpl;
import com.example.cinema.config.InterceptorConfiguration;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author huwen
 * @date 2019/3/23
 */
@RestController()
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR="用户名或密码错误";
    @Autowired
    private AccountServiceImpl accountService;
    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm, HttpSession session){
        UserVO user = accountService.login(userForm);
        if(user==null){
           return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        //注册session
        session.setAttribute(InterceptorConfiguration.SESSION_KEY,userForm);
        return ResponseVO.buildSuccess(user);
    }
    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserForm userForm){
        return accountService.registerAccount(userForm);
    }

    @PostMapping("/logout")
    public String logOut(HttpSession session){
        session.removeAttribute(InterceptorConfiguration.SESSION_KEY);
        return "index";
    }

    @GetMapping("/admin/getAllUser")
    public ResponseVO getAllUser(){
        //TODO: 获取所有影院角色
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/admin/addUser")
    public ResponseVO addUser(UserForm userForm){
        //TODO: 增加影院角色，和注册差不多
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/admin/updateUser")
    public ResponseVO updateUser(UserForm userForm){
        //TODO: 更新某个影院角色信息
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/admin/delUser")
    public ResponseVO delUser(@RequestParam("id")int id){
        //TODO: 删除指定影院角色
        return ResponseVO.buildSuccess();
    }

    @GetMapping("/user/member/getConsumption")
    public ResponseVO getConsumption(@RequestParam("id") int id){
        //TODO: 获取用户历史消费记录(和会员卡充值记录是分离的)
        return ResponseVO.buildSuccess();
    }

}
