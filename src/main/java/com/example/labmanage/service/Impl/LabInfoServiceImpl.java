package com.example.labmanage.service.Impl;
import com.alibaba.fastjson.JSONObject;
import com.example.labmanage.mapper.LabInfoMapper;
import com.example.labmanage.domain.pojo.Lab;
import com.example.labmanage.service.ILabInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LabInfoServiceImpl implements ILabInfoService {
    @Autowired
    private LabInfoMapper labInfoMapper;
    @Override
    public JSONObject getLabInfo() {
        JSONObject jsonObject = new JSONObject();
        Lab labInfo = labInfoMapper.selectOne(null);
        jsonObject.put("labId",labInfo.getLabId());
        jsonObject.put("labLocation",labInfo.getLabLocation());
        jsonObject.put("labManager",labInfo.getLabManager());
        jsonObject.put("labPic",labInfo.getLabPic());
        jsonObject.put("labIntroduction",labInfo.getLabIntroduction());
        return jsonObject;
    }
}
