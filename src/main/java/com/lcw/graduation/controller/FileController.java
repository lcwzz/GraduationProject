package com.lcw.graduation.controller;

import com.lcw.graduation.entity.po.File;
import com.lcw.graduation.entity.vo.FileVO;
import com.lcw.graduation.service.AdminService;
import com.lcw.graduation.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@Slf4j
@CrossOrigin
@RequestMapping("file")
public class FileController {
    @Autowired
    private AdminService adminService;

    @GetMapping("getFiles")
    @ResponseBody
    public ResponseData getFiles() {
        ResponseData responseData = new ResponseData();
        try {
            List<FileVO> files = adminService.getFiles();
            responseData.setSuccess(true).setData(files);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("查找文件出错，请重试！");
        }
        return responseData;
    }

    @GetMapping("downloadOrBrowseFile")
    public void downloadOrBrowseFile(@RequestParam("id") Integer id,
                                     @RequestParam("opt") String opt,
                                     HttpServletResponse response) throws IOException {
        log.info("id = {}, opt= {}", id, opt);
        File file = adminService.findFileById(id);
        // 获取文件的输入流
        FileInputStream inputStream = new FileInputStream(new java.io.File(file.getPath(), file.getNewName()));
        // 获取响应输出流
        if ("download".equals(opt)) {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getOldName(), "UTF-8"));
        } else if ("browse".equals(opt)) {
            response.setHeader("Content-Disposition", "inline");
        }
        ServletOutputStream outputStream = response.getOutputStream();
        // 文件复制
        IOUtils.copy(inputStream, outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @GetMapping("deleteFile")
    @ResponseBody
    public ResponseData deleteFile(@RequestParam("id") Integer id) {
        log.info("id = " + id);
        ResponseData responseData = new ResponseData();
        try {
            adminService.deleteFile(id);
            responseData.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseData.setSuccess(false).setMessage("删除文件出错，请重试！");
        }
        return responseData;
    }
}
