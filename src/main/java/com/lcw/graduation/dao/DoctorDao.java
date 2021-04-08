package com.lcw.graduation.dao;

import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;

import java.util.List;

public interface DoctorDao {

    DoctorVO findByAccount(String account);

    void updateDoctor(DoctorVO doctorVO);

    List<ExtraVO> findExtraById(Integer doctorId);
}
