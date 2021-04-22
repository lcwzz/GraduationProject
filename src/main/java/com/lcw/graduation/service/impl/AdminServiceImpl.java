package com.lcw.graduation.service.impl;

import com.lcw.graduation.dao.AdminDao;
import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.entity.po.Department;
import com.lcw.graduation.entity.po.Doctor;
import com.lcw.graduation.entity.po.Extra;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.ProjectVO;
import com.lcw.graduation.entity.vo.RecordVO;
import com.lcw.graduation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


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
    public List<DoctorVO> getAllDoctors() {
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

}
