package com.lcw.graduation.service.impl;

import com.lcw.graduation.dao.AdminDao;
import com.lcw.graduation.entity.po.Admin;
import com.lcw.graduation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    @Override
    public Admin login(String account, String password) {
        Admin admin = adminDao.findByAccount(account);
        if (admin == null) {
            throw new RuntimeException("账号不存在！");
        }
        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("密码错误！");
        }
        return admin;
    }

    @Override
    public void update(Admin admin) {
        adminDao.updateAdmin(admin);
    }
}
