package com.lcw.graduation.service;

import com.lcw.graduation.entity.po.Project;
import com.lcw.graduation.entity.po.Record;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.ProjectVO;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    DoctorVO login(String account, String password);

    void update(DoctorVO doctorVO);

    List<ExtraVO> findExtra(Integer doctorId);

    void signIn(Map<String, String> requestData) throws Exception;

    List<Record> findRecords(Integer doctorId);

    List<ProjectVO> findProjects(Integer doctorId);

    void addOrUpdateProject(Project project);

    void deleteProject(Integer id);
}
