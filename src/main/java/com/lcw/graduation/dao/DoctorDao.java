package com.lcw.graduation.dao;

import com.lcw.graduation.entity.vo.DoctorVO;

public interface DoctorDao {

    DoctorVO findByAccount(String account);
}
