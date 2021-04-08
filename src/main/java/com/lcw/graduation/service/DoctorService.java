package com.lcw.graduation.service;

import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;

import java.util.List;

public interface DoctorService {

    DoctorVO login(String account, String password);

    void update(DoctorVO doctorVO);

    List<ExtraVO> findExtra(Integer doctorId);
}
