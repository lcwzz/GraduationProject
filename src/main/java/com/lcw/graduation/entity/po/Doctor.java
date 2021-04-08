package com.lcw.graduation.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Doctor {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String sex;
    private Integer age;
    private String face;
    private String education;
    private Integer salary;
    private Integer departmentId;
}
