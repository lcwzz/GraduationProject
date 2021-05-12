package com.lcw.graduation.dao;

import com.lcw.graduation.entity.po.Medical;
import com.lcw.graduation.entity.po.Project;
import com.lcw.graduation.entity.po.Record;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.ProjectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorDao {

    DoctorVO findByAccount(String account);

    void updateDoctor(DoctorVO doctorVO);

    List<ExtraVO> findExtraById(Integer doctorId);

    void insertRecord(Record record);

    List<Record> findRecordsById(Integer doctorId);

    List<ProjectVO> findProjectsById(Integer doctorId);

    void insertProject(Project project);

    void updateProject(Project project);

    void deleteProjectById(Integer id);

    List<Medical> getMedicalPage(@Param("start")Integer start,
                                 @Param("count")Integer count,
                                 @Param("name")String name,
                                 @Param("id")Integer id);

    Integer getMedicalPageTotal(@Param("id")Integer id,
                                @Param("name")String name);

    void insertMedical(Medical medical);

    void updateMedical(Medical medical);

    void deleteMedical(Integer id);
}
