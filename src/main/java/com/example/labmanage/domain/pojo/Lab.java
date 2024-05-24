package com.example.labmanage.domain.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lab {
    @TableId(value = "lab_id",type = IdType.INPUT)
    String labId;
    @TableField("lab_location")
    String labLocation;
    @TableField("lab_manager")
    String labManager;
    @TableField("lab_pic")
    String labPic;
    @TableField("lab_introduction")
    String labIntroduction;
}
