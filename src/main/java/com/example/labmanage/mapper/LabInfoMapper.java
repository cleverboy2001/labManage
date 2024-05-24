package com.example.labmanage.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.labmanage.domain.pojo.Lab;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabInfoMapper extends BaseMapper<Lab> {
}
