package com.lcw.graduation.controller;

import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.entity.po.Department;
import com.lcw.graduation.entity.po.Doctor;
import com.lcw.graduation.entity.po.Extra;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.RecordVO;
import com.lcw.graduation.service.AdminService;
import com.lcw.graduation.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("getAllDoctors")
    public ResponseData getAllDoctors() {
        ResponseData responseData = new ResponseData();
        try {
            List<DoctorVO> doctors = adminService.getAllDoctors();
            responseData.setSuccess(true).setData(doctors);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("getAllDepartments")
    public ResponseData getAllDepartments() {
        ResponseData responseData = new ResponseData();
        try {
            List<Department> departments = adminService.getAllDepartments();
            responseData.setSuccess(true).setData(departments);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @PostMapping("updateDoctor")
    public ResponseData updateDoctor(@RequestBody Doctor doctor) {
        log.info(doctor.toString());
        ResponseData responseData = new ResponseData();
        try {
            adminService.updateDoctor(doctor);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("更新出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("deleteDoctor")
    public ResponseData deleteDoctor(@RequestParam("id") Integer doctorId) {
        log.info(doctorId.toString());
        ResponseData responseData = new ResponseData();
        try {
            adminService.deleteDoctor(doctorId);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("删除出错，请重试！");
        }
        return responseData;
    }

    @PostMapping("addDoctor")
    public ResponseData addDoctor(@RequestBody Doctor doctor) {
        log.info(doctor.toString());
        ResponseData responseData = new ResponseData();
        try {
            adminService.addDoctor(doctor);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("添加出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("getAllRecords")
    public ResponseData getAllRecords() {
        ResponseData responseData = new ResponseData();
        try {
            List<RecordVO> recordVOList = adminService.getAllRecords();
            responseData.setSuccess(true).setData(recordVOList);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("findExtra")
    public ResponseData findExtra(@RequestParam("id") Integer adminId) {
        log.info("adminId: " + adminId);
        ResponseData responseData = new ResponseData();
        try {
            List<ExtraVO> extraList = adminService.findExtra(adminId);
            responseData.setSuccess(true).setData(extraList);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @PostMapping("addOrUpdateExtra")
    public ResponseData addOrUpdateExtra(@RequestBody Extra extra) {
        log.info(extra.toString());
        ResponseData responseData = new ResponseData();
        try {
            adminService.addOrUpdateExtra(extra);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("操作出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("deleteExtra")
    public ResponseData deleteExtra(@RequestParam("id") Integer id) {
        log.info("id: " + id);
        ResponseData responseData = new ResponseData();
        try {
            adminService.deleteExtra(id);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("删除出错，请重试！");
        }
        return responseData;
    }
}
