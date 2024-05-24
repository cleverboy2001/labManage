package com.example.labmanage.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.labmanage.domain.pojo.User;
import com.example.labmanage.domain.query.UserQuery;
import com.example.labmanage.service.IUserService;
import com.example.labmanage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject requestJson) {
        return userService.login(requestJson);
    }

    @GetMapping("/list")
    //GetMapping请求参数会通过 URL 查询参数的方式传递
    public Result list(@RequestBody UserQuery queryParams)
    {
        System.out.println(queryParams.getMajor());
        System.out.println(queryParams.getMentor());
        return userService.selectUserList(queryParams);
    }

    @PutMapping("/updateUserInfo")
    public String updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody JSONObject jsonObject) {
        return userService.updateUserInfo(token,jsonObject);
    }

}
