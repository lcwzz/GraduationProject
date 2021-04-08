package com.lcw.graduation.service.impl;

import com.lcw.graduation.dao.DoctorDao;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDao doctorDao;


    @Override
    public DoctorVO login(String account, String password) {
        DoctorVO doctorVO = doctorDao.findByAccount(account);
        if (doctorVO == null) {
            throw new RuntimeException("账号不存在！");
        }
        if (!doctorVO.getPassword().equals(password)) {
            throw new RuntimeException("密码错误！");
        }
        return doctorVO;
    }
}
