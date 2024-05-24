package com.example.labmanage.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.labmanage.pojo.User;
import com.example.labmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody JSONObject requestJson) {
        return userService.login(requestJson);
    }

    @GetMapping("/userInfo")
    public User userInfo(@RequestHeader("Authorization")  String token) {
        return userService.userInfo(token);
    }

    @PutMapping("/updateUserInfo")
    public String updateUserInfo(@RequestHeader("Authorization") String token, @RequestBody JSONObject jsonObject) {
        return userService.updateUserInfo(token,jsonObject);
    }

}
