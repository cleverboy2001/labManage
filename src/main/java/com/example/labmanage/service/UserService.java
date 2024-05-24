package com.example.labmanage.service;
import com.alibaba.fastjson.JSONObject;
import com.example.labmanage.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
     String register(User user);
     String login(JSONObject jsonObject);
     // 获取用户信息
     User userInfo(String token);
     String updateUserInfo(String token, JSONObject jsonObject);
}
