package com.cosmos.vis.controller;


import com.cosmos.vis.common.ErrorCode;
import com.cosmos.vis.pojo.Result;
import com.cosmos.vis.pojo.User;
import com.cosmos.vis.service.IUserService;
import com.cosmos.vis.utils.MailUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;


    @Autowired
    private MailUtil mailUtil;


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

        long userId = userService.getUserId(request);
        if (userService.updateUser(user, request)) {
            //根据实际需求前端更新完成之后需要重新获取更新后的对象
            User newUser = userService.getById(userId);

            return Result.success("修改成功", newUser);
        } else {
            return Result.error("修改失败");
        }
    }


    @PostMapping("/feedBack")
    public Result feedBack(HttpServletRequest request, String content) throws MessagingException {

        long userId = userService.getUserId(request);

        mailUtil.sendTextMailMessage("3317194303@qq.com", "来自用户" + userId + "反馈信息", content);

        return Result.success("反馈成功");

    }


}
