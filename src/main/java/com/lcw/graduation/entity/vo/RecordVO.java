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
public class RecordVO {
    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private String state;
    private Integer doctorId;
    private String doctorName;
}
