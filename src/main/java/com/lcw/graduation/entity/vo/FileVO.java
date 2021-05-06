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
public class FileVO {
    private Integer id;
    private String oldName;
    private String newName;
    private String path;
    @JsonFormat(pattern = "yyyy年MM月dd日", timezone = "GMT+8")
    private Timestamp date;
    private Integer adminId;
    private String adminName;
}
