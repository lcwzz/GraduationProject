package com.lcw.graduation.service;

import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.entity.po.Department;
import com.lcw.graduation.entity.po.Doctor;
import com.lcw.graduation.entity.po.Extra;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.RecordVO;

import java.util.List;

public interface AdminService {
    Admin login(String account, String password);

    void update(Admin admin);

    List<DoctorVO> getAllDoctors();

    List<Department> getAllDepartments();

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Integer doctorId);

    void addDoctor(Doctor doctor);

    List<RecordVO> getAllRecords();

    List<ExtraVO> findExtra(Integer adminId);

    void addOrUpdateExtra(Extra extra);

    void deleteExtra(Integer id);
}
