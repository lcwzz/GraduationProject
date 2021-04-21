package com.lcw.graduation.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lcw.graduation.dao.DoctorDao;
import com.lcw.graduation.entity.po.Project;
import com.lcw.graduation.entity.po.Record;
import com.lcw.graduation.entity.vo.DoctorVO;
import com.lcw.graduation.entity.vo.ExtraVO;
import com.lcw.graduation.entity.vo.ProjectVO;
import com.lcw.graduation.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

    @Override
    public void update(DoctorVO doctorVO) {
        doctorDao.updateDoctor(doctorVO);
    }

    @Override
    public List<ExtraVO> findExtra(Integer doctorId) {
        return doctorDao.findExtraById(doctorId);
    }

    @Override
    public void signIn(Map<String, String> requestData) throws Exception {
        Record record = new Record();
        record.setDoctorId(Integer.parseInt(requestData.get("id")));
        String time = requestData.get("time");
        record.setTime(Timestamp.valueOf(time));
        // 设置状态
        int hour = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss").parse(time).getHours();
        if (requestData.get("symbol").equals("上班")) {
            // 上班时间是8点之前
            if (hour >= 8) {
                record.setState("迟到");
            } else {
                record.setState("正常");
            }
        } else {
            // 下班时间是18点之后
            if (hour < 18) {
                record.setState("早退");
            } else {
                record.setState("正常");
            }
        }
        doctorDao.insertRecord(record);
    }

    @Override
    public List<Record> findRecords(Integer doctorId) {
        return doctorDao.findRecordsById(doctorId);
    }

    @Override
    public List<ProjectVO> findProjects(Integer doctorId) {
        List<ProjectVO> projects = doctorDao.findProjectsById(doctorId);
        for (ProjectVO project : projects) {
            if (StringUtils.isEmpty(project.getName())) {
                project.setName("暂无数据");
            }
        }
        return projects;
    }

    @Override
    public void addProject(Project project) {
        project.setState("未审核");
        project.setDate(new Timestamp(System.currentTimeMillis()));
        doctorDao.insertProject(project);
    }
}
