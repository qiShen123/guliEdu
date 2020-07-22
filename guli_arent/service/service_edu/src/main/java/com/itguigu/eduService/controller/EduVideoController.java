package com.itguigu.eduService.controller;


import com.itguigu.eduService.entity.EduVideo;
import com.itguigu.eduService.service.IEduVideoService;
import com.itguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/eduService/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private IEduVideoService iEduVideoService;
    @GetMapping("{videoId}")
    public R getVideo(@PathVariable("videoId") String videoId){
        EduVideo video = iEduVideoService.getById(videoId);
        return R.ok().data("video",video);
    }

    @PutMapping
    public R updateVideo(@RequestBody EduVideo video){
        boolean b = iEduVideoService.updateById(video);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping
    public R saveVideo(@RequestBody EduVideo video){
        boolean b = iEduVideoService.save(video);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("{videoId}")
    public R deleteVideo(@PathVariable("videoId") String videoId){
        boolean b = iEduVideoService.deleteVideo(videoId);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
