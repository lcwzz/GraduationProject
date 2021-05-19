package com.lcw.graduation.service;

import com.lcw.graduation.entity.po.*;
import com.lcw.graduation.entity.vo.FileVO;
import com.lcw.graduation.entity.vo.ProjectVO;
import com.lcw.graduation.entity.vo.RecordVO;
import com.lcw.graduation.entity.DoctorEvaluation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface AdminService {
    Admin login(String account, String password);

    void update(Admin admin);

    List<Doctor> getAllDoctors();

    List<Department> getAllDepartments();

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Integer doctorId);

    void addDoctor(Doctor doctor);

    List<RecordVO> getAllRecords();

    Map<String, Object> findExtraPage(Integer adminId, Integer pageNum, Integer pageSize, String name);

    void addOrUpdateExtra(Extra extra);

    void deleteExtra(Integer id);

    List<ProjectVO> getAllProjects(Integer adminId);

    void checkProject(Map<String, String> checkResult);

    Map<String, Object> getDoctorPage(Integer pageNum, Integer pageSize, String name);

    void saveFile(Integer id, MultipartFile file) throws IOException;

    List<FileVO> getFiles();

    File findFileById(Integer id);

    void deleteFile(Integer id);

    List<DoctorEvaluation> evaluateDoctor();
}
