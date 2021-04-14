package com.lcw.graduation.service;

import com.lcw.graduation.entity.po.Admin;

public interface AdminService {
    Admin login(String account, String password);

    void update(Admin admin);
}
