package com.lcw.graduation.controller;

import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.service.AdminService;
import com.lcw.graduation.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public ResponseData login(@RequestBody Map<String, String> form) {
        log.info("account=" + form.get("account") + ", password=" + form.get("password"));
        ResponseData responseData = new ResponseData();
        try {
            Admin admin = adminService.login(form.get("account"), form.get("password"));
            responseData.setSuccess(true).setData(admin);
        } catch (Exception e) {
            responseData.setSuccess(false).setMessage(e.getMessage());
        }
        return responseData;
    }

    @PostMapping("update")
    public ResponseData update(@RequestBody Admin admin) {
        log.info(admin.toString());
        ResponseData responseData = new ResponseData();
        try {
            adminService.update(admin);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("更新出错，请重试！");
        }
        return responseData;
    }

}
