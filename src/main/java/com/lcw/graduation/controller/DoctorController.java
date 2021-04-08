package com.lcw.graduation.controller;

import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.service.DoctorService;
import com.lcw.graduation.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("doctor")
@Slf4j
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("login")
    public ResponseData login(@RequestBody Map<String, String> form) {
        log.info("account=" + form.get("account") + ", password=" + form.get("password"));
        ResponseData responseData = new ResponseData();
        try {
            DoctorVO doctorVO = doctorService.login(form.get("account"), form.get("password"));
            responseData.setSuccess(true).setData(doctorVO);
        } catch (Exception e) {
            responseData.setSuccess(false).setMessage(e.getMessage());
        }
        return responseData;
    }
}
