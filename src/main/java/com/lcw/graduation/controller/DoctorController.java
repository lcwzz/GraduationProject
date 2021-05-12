package com.lcw.graduation.controller;

import com.lcw.graduation.entity.po.Medical;
import com.lcw.graduation.entity.po.Project;
import com.lcw.graduation.entity.po.Record;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.ProjectVO;
import com.lcw.graduation.service.DoctorService;
import com.lcw.graduation.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("update")
    public ResponseData update(@RequestBody DoctorVO doctorVO) {
        log.info(doctorVO.toString());
        ResponseData responseData = new ResponseData();
        try {
            doctorService.update(doctorVO);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("更新出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("findExtra")
    public ResponseData findExtra(@RequestParam("id") Integer doctorId) {
        log.info("doctorId: " + doctorId);
        ResponseData responseData = new ResponseData();
        try {
            List<ExtraVO> extraList = doctorService.findExtra(doctorId);
            responseData.setSuccess(true).setData(extraList);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @PostMapping("signIn")
    public ResponseData signIn(@RequestBody Map<String, String> requestData) {
        log.info("requestData: " + requestData.toString());
        ResponseData responseData = new ResponseData();
        try {
            doctorService.signIn(requestData);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("打卡出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("findRecords")
    public ResponseData findRecords(@RequestParam("id") Integer doctorId) {
        log.info("doctorId: " + doctorId);
        ResponseData responseData = new ResponseData();
        try {
            List<Record> records = doctorService.findRecords(doctorId);
            responseData.setSuccess(true).setData(records);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("findProjects")
    public ResponseData findProjects(@RequestParam("id") Integer doctorId) {
        log.info("doctorId: " + doctorId);
        ResponseData responseData = new ResponseData();
        try {
            List<ProjectVO> projects = doctorService.findProjects(doctorId);
            responseData.setSuccess(true).setData(projects);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @PostMapping("addOrUpdateProject")
    public ResponseData addOrUpdateProject(@RequestBody Project project) {
        log.info(project.toString());
        ResponseData responseData = new ResponseData();
        try {
            doctorService.addOrUpdateProject(project);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("操作出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("deleteProject")
    public ResponseData deleteProject(@RequestParam("id") Integer id) {
        log.info("id: " + id);
        ResponseData responseData = new ResponseData();
        try {
            doctorService.deleteProject(id);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("删除出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("getMedicalPage")
    public ResponseData getMedicalPage(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize,
                                       // 查询条件
                                       @RequestParam(value = "name", defaultValue = "") String name,
                                       @RequestParam(value = "id") Integer doctorId) {
        log.info("pageNum: " + pageNum);
        log.info("pageSize: " + pageSize);
        log.info("name: " + name);
        log.info("doctorId: " + doctorId);
        ResponseData responseData = new ResponseData();
        try {
            Map<String, Object> map = doctorService.getMedicalPage(pageNum, pageSize, name, doctorId);
            responseData.setSuccess(true).setData(map);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查询出错，请重试！");
        }
        return responseData;
    }

    @PostMapping("addOrUpdateMedical")
    public ResponseData addOrUpdateMedical(@RequestBody Medical medical) {
        log.info(medical.toString());
        ResponseData responseData = new ResponseData();
        try {
            doctorService.addOrUpdateExtra(medical);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("操作出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("deleteMedical")
    public ResponseData deleteMedical(@RequestParam("id") Integer id) {
        log.info("id: " + id);
        ResponseData responseData = new ResponseData();
        try {
            doctorService.deleteMedical(id);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("删除出错，请重试！");
        }
        return responseData;
    }
}
