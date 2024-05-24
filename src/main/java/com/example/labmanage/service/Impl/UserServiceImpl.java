package com.example.labmanage.service.Impl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.labmanage.domain.query.UserQuery;
import com.example.labmanage.mapper.UserMapper;
import com.example.labmanage.domain.pojo.User;
import com.example.labmanage.service.IUserService;
import com.example.labmanage.util.JwtUtil;
import com.example.labmanage.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    public Result register(User user) {

        User u = userMapper.selectById(user.getStudentId());
        if (u != null)
        {
            return Result.error("用户已存在");
        }
        else{
        userMapper.insert(user);
        return Result.success();
        }

    }

    @Override
    public Result selectUserList(UserQuery queryParams) {
        //如果user.getStudentId()不为null，则在SQL查询中添加一个条件，比较User表中的studentId字段是否等于user.getStudentId()的值
        System.out.println(queryParams.getMajor());
        System.out.println(queryParams.getMentor());
        List<User> list = lambdaQuery().eq(queryParams.getStudentId() != null, User::getStudentId, queryParams.getStudentId())
                .like(queryParams.getUsername() != null, User::getUsername, queryParams.getUsername())
                .like(queryParams.getMajor() != null, User::getMajor, queryParams.getMajor())
                .like(queryParams.getMentor() != null, User::getMentor, queryParams.getMentor())
                .list();
        return Result.success(list);
    }

    public Result login(JSONObject jsonObject) {
        String studentId = jsonObject.getString("studentId");
        String password = jsonObject.getString("password");
        User user = userMapper.selectById(studentId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        if (password.equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap();
            claims.put("studentId", studentId);
            claims.put("password", password);
            String token = JwtUtil.genToken(claims);
            System.out.println("你好");
            return Result.success(token);
        } else {
            return Result.error("密码错误");

        }
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

