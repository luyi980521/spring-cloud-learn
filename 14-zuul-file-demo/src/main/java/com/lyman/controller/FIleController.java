package com.lyman.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by luyi on 2021/5/10 20:44.
 * 描述：文件上传
 */
@Slf4j
@RestController
public class FIleController {

    @PostMapping("/file/upload")
    public String fileUpload(@RequestParam(value = "file")MultipartFile file) throws IOException {
        log.info("文件名：{}", file.getOriginalFilename());
        log.info("表单名：{}", file.getName());
        byte[] bytes = file.getBytes();
        // 构建文件
        File f = new File(file.getOriginalFilename());
        file.transferTo(f);
        return f.getAbsolutePath();
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
