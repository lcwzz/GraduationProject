package com.lcw.graduation.dao;

import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.entity.po.Department;
import com.lcw.graduation.entity.po.Doctor;
import com.lcw.graduation.entity.po.Extra;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.ProjectVO;
import com.lcw.graduation.entity.vo.RecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {

    Admin findByAccount(String account);

    void updateAdmin(Admin admin);

    List<Doctor> getAllDoctors();

    List<Department> getAllDepartments();

    void updateDoctor(Doctor doctor);

    void deleteDoctorById(Integer doctorId);

    void insertDoctor(Doctor doctor);

    List<RecordVO> getAllRecords();

    List<ExtraVO> findExtraPageById(@Param("adminId") Integer adminId,
                                    @Param("start")Integer start,
                                    @Param("count")Integer count,
                                    @Param("name")String name);

    Integer findExtraTotalById(@Param("adminId") Integer adminId,
                               @Param("name") String name);

    void insertExtra(Extra extra);

    void updateExtra(Extra extra);

    void deleteExtraById(Integer id);

    List<ProjectVO> getProjectsByAdminId(Integer adminId);

    void updateProjectState(@Param("id") Integer id,
                            @Param("state") String state,
                            @Param("adminId") Integer adminId);

    List<DoctorVO> getDoctorPage(@Param("start")Integer start,
                                 @Param("count")Integer count,
                                 @Param("name")String name);

    Integer getDoctorPageTotal(String name);
}
