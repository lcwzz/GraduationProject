package com.lcw.graduation.service;

import com.lcw.graduation.entity.vo.DoctorVO;

public interface DoctorService {

    DoctorVO login(String account, String password);
}
