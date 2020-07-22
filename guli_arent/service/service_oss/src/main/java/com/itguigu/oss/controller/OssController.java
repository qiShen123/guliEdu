package com.itguigu.oss.controller;

import com.itguigu.oss.service.IOssService;
import com.itguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Slf4j
public class OssController {
    @Autowired
    private IOssService service;

    @PostMapping
    public R uploadOssFile(MultipartFile file) {
        log.info(file.getName());
        log.info(file.toString());
        String url = service.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
