package com.lcw.graduation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DoctorEvaluation {
    private int id; // 医生的id
    private String name; // 医生名字
    private String level; // 评级
    private int x; // 该医生管理病历的数量
    private int y; // 该医生的考勤分
    private int z; // 该医生的奖惩分
}
