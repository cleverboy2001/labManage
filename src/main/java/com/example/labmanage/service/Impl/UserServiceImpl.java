package com.example.labmanage.service.Impl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.labmanage.mapper.UserMapper;
import com.example.labmanage.pojo.User;
import com.example.labmanage.service.UserService;
import com.example.labmanage.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    public  String register(User user) {
        if (userMapper.selectById(user.getStudentId())==null)
        {
            userMapper.insert(user);
            return "注册成功";
        }
        else{
            return "用户已经存在";
        }

    }

    public String login(JSONObject jsonObject) {
        String studentId = jsonObject.getString("studentId");
        String password = jsonObject.getString("password");
        JSONObject info = new JSONObject();
        User user = userMapper.selectById(studentId);
        if (user == null) {
            return "用户不存在";
        }
        if (password.equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap();
            claims.put("studentId", studentId);
            claims.put("password", password);
            String token = JwtUtil.genToken(claims);
            return token;
        } else {
            return "密码错误";

        }
    }

    @Override
    public User userInfo(String token) {
        Map<String, Object> claims = JwtUtil.parseToken(token);
        String studentId = (String) claims.get("studentId");
        User user = userMapper.selectById(studentId);
        return user;
    }

    @Override
    public String updateUserInfo(String token, JSONObject jsonObject) {
        String studentId_old = (String) JwtUtil.parseToken(token).get("studentId");
        User user = userMapper.selectById(studentId_old);
        String studentId_new = jsonObject.getString("studentId");
        String username = jsonObject.getString("username");
        Integer age = jsonObject.getInteger("age");
        String major = jsonObject.getString("major");
        String grade = jsonObject.getString("grade");
        String mentor = jsonObject.getString("mentor");
        String photo = jsonObject.getString("photo");
        String phone = jsonObject.getString("phone");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("studentId",studentId_old);
        if(studentId_new==null)
        {
            updateWrapper.set("studentId",user.getStudentId());
        }
        else updateWrapper.set("studentId",studentId_new);
        if(username==null)
        {
            updateWrapper.set("username",user.getUsername());
        }
        else updateWrapper.set("username",username);
        if(age==null)
        {
            updateWrapper.set("age",user.getAge());
        }
        else  updateWrapper.set("age",age);
        if(major==null)
        {
            updateWrapper.set("major",user.getMajor());
        }
        else updateWrapper.set("major",major);
        if(grade==null)
        {
            updateWrapper.set("grade",user.getGrade());
        }
        else updateWrapper.set("grade",grade);
        if(mentor==null)
        {
            updateWrapper.set("mentor",user.getMentor());
        }
        else updateWrapper.set("mentor",mentor);
        if(phone==null)
        {
            updateWrapper.set("phone",user.getPhone());
        }
        else updateWrapper.set("phone",phone);
        if(photo==null)
        {
            updateWrapper.set("photo",user.getPhoto());
        }
        else updateWrapper.set("photo",photo);
        int rowsAffected = userMapper.update(null, updateWrapper);
        if (rowsAffected > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
}

