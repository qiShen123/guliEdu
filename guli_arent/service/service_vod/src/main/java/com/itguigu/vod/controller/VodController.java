package com.itguigu.vod.controller;

import com.itguigu.vod.service.IVodService;
import com.itguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private IVodService service;

    @PostMapping
    public R uploadVideo(MultipartFile file){
        String videoId = service.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable("videoId") String videoId ){
        service.deleteVideo(videoId);
        return R.ok();
    }

    @DeleteMapping("deleteMoreVideo")
    public R deleteMoreVideo(@RequestBody List<String> videoIds){
        service.deleteMoreVideo(videoIds);
        return R.ok();
    }

    @GetMapping("getPlayAuth/{videoId}")
    public R getPlayAuth(@PathVariable("videoId") String videoId){
        String  playAuth= service.getPlayAuth(videoId);
        return R.ok().data("playAuth",playAuth);
    }
}
