package com.itguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IVodService {
    String uploadVideo(MultipartFile video);

    void deleteVideo(String videoId);

    void deleteMoreVideo(List<String> videoIds);

    String getPlayAuth(String videoId);
}
