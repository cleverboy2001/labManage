package com.example.labmanage.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface LabInfoService {
    JSONObject getLabInfo();
}
