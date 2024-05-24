package com.example.labmanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.labmanage.service.LabInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/labInfo")
@RestController
public class LabInfoController {
    @Autowired
    private LabInfoService labInfoService;
    @GetMapping("/getLabInfo")
    public JSONObject getLabInfo() {

        return labInfoService.getLabInfo();
    }
}
