package com.itguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface IOssService {

    public String uploadFileAvatar(MultipartFile file);
}
