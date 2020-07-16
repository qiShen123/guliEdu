package com.itguigu.eduService.mapper;

import com.itguigu.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itguigu.eduService.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
public interface EduChapterMapper extends BaseMapper<EduChapter> {

    List<ChapterVo> getChapter(String courseId);
}
