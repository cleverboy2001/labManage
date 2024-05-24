package com.example.labmanage.service;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.labmanage.domain.pojo.User;
import com.example.labmanage.domain.query.UserQuery;
import com.example.labmanage.util.Result;

public interface IUserService extends IService<User> {
     Result register(User user);
     Result login(JSONObject jsonObject);
     // 获取用户信息
     String updateUserInfo(String token, JSONObject jsonObject);
     Result selectUserList(UserQuery queryParams);
}
