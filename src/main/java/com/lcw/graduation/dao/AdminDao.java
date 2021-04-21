package com.lcw.graduation.dao;

import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.entity.po.Department;
import com.lcw.graduation.entity.po.Doctor;
import com.lcw.graduation.entity.po.Extra;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.RecordVO;

import java.util.List;

public interface AdminDao {

    Admin findByAccount(String account);

    void updateAdmin(Admin admin);

    List<DoctorVO> getAllDoctors();

    List<Department> getAllDepartments();

    void updateDoctor(Doctor doctor);

    void deleteDoctorById(Integer doctorId);

    void insertDoctor(Doctor doctor);

    List<RecordVO> getAllRecords();

    List<ExtraVO> findExtraById(Integer adminId);

    void insertExtra(Extra extra);

    void updateExtra(Extra extra);

    void deleteExtraById(Integer id);
}
