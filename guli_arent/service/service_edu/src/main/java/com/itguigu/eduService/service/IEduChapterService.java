package com.itguigu.eduService.service;

import com.itguigu.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itguigu.eduService.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
public interface IEduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapters(String courseId);

    boolean deleteChapter(String chapterId);
}
