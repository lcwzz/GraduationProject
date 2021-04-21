package com.lcw.graduation.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ExtraVO {
    private Integer id;
    private Integer money;
    private String sign;
    private String reason;
    private String doctorId;
    private String adminId;

    // 管理员查询则为医生名字
    // 医生查询则为管理员名字
    private String name;
}
