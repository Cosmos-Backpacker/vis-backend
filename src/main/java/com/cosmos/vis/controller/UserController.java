package com.cosmos.vis.controller;


import com.cosmos.vis.common.ErrorCode;
import com.cosmos.vis.pojo.Result;
import com.cosmos.vis.pojo.User;
import com.cosmos.vis.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CosmosBackpacker
 * @since 2025-03-10
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping("/login")
    public Result login(String userAccount, String userPassword, HttpServletRequest request) {
        return userService.userLogin(userAccount, userPassword, request);

    }


    @PostMapping("/register")
    public Result register(String account, String password, String checkPassword) {

        return userService.userRegister(account, password, checkPassword);

    }


    @GetMapping("/selectAllUser")
    public Result selectAllUser(@RequestParam String username, HttpServletRequest request) {


        List<User> userList = userService.selectAllByUsername(username, request);

        if (userList != null) {
            return Result.success("查询成功", userList);
        }
        return Result.error(ErrorCode.SYSTEM_ERROR);
    }


    @DeleteMapping("/delete")
    public Result deleteUserById(Integer id, HttpServletRequest request) {
        return userService.deleteUserById(id, request);
    }


    @GetMapping("/userLayout")
    public Result userLayout(HttpServletRequest request) {
        return userService.userLayout(request);
    }


    @PostMapping("/updateInfo")
    public Result updateUser(@RequestBody User user, HttpServletRequest request) {

        if (user == null) {
            throw new RuntimeException("参数为空");
        }
        log.error(user.toString());
        if (userService.updateUser(user, request)) {
            return Result.success("修改成功");
        } else {
            return Result.error("修改失败");
        }
    }

}
