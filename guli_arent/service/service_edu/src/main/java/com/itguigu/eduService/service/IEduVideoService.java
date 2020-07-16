package com.itguigu.eduService.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.eduService.entity.EduChapter;
import com.itguigu.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
public interface IEduVideoService extends IService<EduVideo> {

    int count(QueryWrapper<EduChapter> wrapper);

    boolean deleteVideo(String videoId);
}
