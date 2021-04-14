package com.lcw.graduation.dao;

import com.lcw.graduation.entity.po.Admin;

public interface AdminDao {

    Admin findByAccount(String account);

    void updateAdmin(Admin admin);
}
