package com.lcw.graduation.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProjectVO {
    private Integer id;
    private String description;
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp date;
    private Integer money;
    private Integer doctorId;
    private Integer adminId;

    // 管理员查询则为申请人名字
    // 申请人查询则为管理员名字
    private String name;
}
