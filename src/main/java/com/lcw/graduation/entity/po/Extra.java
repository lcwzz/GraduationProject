package com.lcw.graduation.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Extra {
    private Integer id;
    private Integer money;
    private String sign;
    private String reason;
    private Integer doctorId;
    private Integer adminId;
}
