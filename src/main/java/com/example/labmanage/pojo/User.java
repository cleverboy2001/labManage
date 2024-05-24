package com.example.labmanage.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    @TableId(value = "studentId",type = IdType.INPUT)
    private String studentId;
    private String username;
    private Integer age;
    private String major;
    private String grade;
    private String mentor;
    private String phone;
    private String photo;
    private String password;
}
