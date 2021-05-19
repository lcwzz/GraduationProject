package com.lcw.graduation.service.impl;

import com.lcw.graduation.dao.AdminDao;
import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.entity.po.Department;
import com.lcw.graduation.entity.po.Doctor;
import com.lcw.graduation.entity.po.Extra;
import com.lcw.graduation.entity.vo.*;
import com.lcw.graduation.service.AdminService;
import com.lcw.graduation.entity.DoctorEvaluation;
import com.lcw.graduation.util.KMeans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    private String filePath = ResourceUtils.getURL("classpath:").getPath() + "static/sql/";

    public AdminServiceImpl() throws FileNotFoundException {
        log.info(filePath);
    }


    @Override
    public Admin login(String account, String password) {
        Admin admin = adminDao.findByAccount(account);
        if (admin == null) {
            throw new RuntimeException("账号不存在！");
        }
        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("密码错误！");
        }
        return admin;
    }

    @Override
    public void update(Admin admin) {
        adminDao.updateAdmin(admin);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return adminDao.getAllDoctors();
    }

    @Override
    public List<Department> getAllDepartments() {
        return adminDao.getAllDepartments();
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        adminDao.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(Integer doctorId) {
        adminDao.deleteDoctorById(doctorId);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        adminDao.insertDoctor(doctor);
    }

    @Override
    public List<RecordVO> getAllRecords() {
        return adminDao.getAllRecords();
    }

    @Override
    public Map<String, Object> findExtraPage(Integer adminId, Integer pageNum, Integer pageSize, String name) {
        List<ExtraVO> extraVOList = adminDao.findExtraPageById(adminId, (pageNum - 1) * pageSize, pageSize, name);
        Integer total = adminDao.findExtraTotalById(adminId, name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("extra", extraVOList);
        map.put("total", total);
        return map;
    }

    @Override
    public void addOrUpdateExtra(Extra extra) {
        if (extra.getId() == null) {
            adminDao.insertExtra(extra);
        } else {
            adminDao.updateExtra(extra);
        }
    }

    @Override
    public void deleteExtra(Integer id) {
        adminDao.deleteExtraById(id);
    }

    @Override
    public List<ProjectVO> getAllProjects(Integer adminId) {
        return adminDao.getProjectsByAdminId(adminId);
    }

    @Override
    public void checkProject(Map<String, String> checkResult) {
        Integer id = Integer.valueOf(checkResult.get("id"));
        String state = checkResult.get("state");
        Integer adminId = Integer.valueOf(checkResult.get("adminId"));
        adminDao.updateProjectState(id, state, adminId);
    }

    @Override
    public Map<String, Object> getDoctorPage(Integer pageNum, Integer pageSize, String name) {
        List<DoctorVO> doctors = adminDao.getDoctorPage((pageNum - 1) * pageSize, pageSize, name);
        Integer total = adminDao.getDoctorPageTotal(name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("doctors", doctors);
        map.put("total", total);
        return map;
    }

    @Override
    public void saveFile(Integer id, MultipartFile file) throws IOException {
        // 1.1 生成目录
        String fileDirStr = filePath + new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File fileDir = new File(fileDirStr);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        // 1.2 生成文件名
        String oldName = file.getOriginalFilename();
        String newName = System.currentTimeMillis() + "-" +  oldName;
        // 1.3 保存文件
        file.transferTo(new File(fileDir, newName));

        // 2. 保存文件信息到数据库
        com.lcw.graduation.entity.po.File fileDb = new com.lcw.graduation.entity.po.File();
        fileDb.setAdminId(id)
                .setPath(fileDirStr)
                .setOldName(oldName)
                .setNewName(newName)
                .setDate(new Timestamp(System.currentTimeMillis()));
        adminDao.insertFile(fileDb);
    }

    @Override
    public List<FileVO> getFiles() {
        return adminDao.findAllFiles();
    }

    @Override
    public com.lcw.graduation.entity.po.File findFileById(Integer id) {
        return adminDao.findFileById(id);
    }

    @Override
    public void deleteFile(Integer id) {
        com.lcw.graduation.entity.po.File file = adminDao.findFileById(id);
        // 删除磁盘上的文件
        File realFile = new File(file.getPath(), file.getNewName());
        if (realFile.exists()) {
            realFile.delete();
        }
        // 删除数据库记录
        adminDao.deleteById(id);
    }

    @Override
    public List<DoctorEvaluation> evaluateDoctor() {
        KMeans kMeans = new KMeans(generateDataset());
        kMeans.cluster();
        return kMeans.getDataset();
    }

    private List<DoctorEvaluation> generateDataset() {
        List<DoctorEvaluation> list = adminDao.getDoctorEvaluation();
        for (DoctorEvaluation doctorEvaluation : list) {
            doctorEvaluation.setX(getMedicalScore(doctorEvaluation.getId()));
            doctorEvaluation.setY(getRecordScore(doctorEvaluation.getId()));
            doctorEvaluation.setZ(getExtraScore(doctorEvaluation.getId()));
        }
        return list;
    }

    private int getExtraScore(int id) {
        int reward = adminDao.getExtraRewardScore(id);
        int punishment = adminDao.getExtraPunishmentScore(id);
        return reward - punishment;
    }

    private int getRecordScore(int id) {
        return -adminDao.getRecordScore(id);
    }

    private int getMedicalScore(int id) {
        return adminDao.getMedicalScore(id);
    }
}
