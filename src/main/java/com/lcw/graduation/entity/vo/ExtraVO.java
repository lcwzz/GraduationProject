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
    private String name;
}
