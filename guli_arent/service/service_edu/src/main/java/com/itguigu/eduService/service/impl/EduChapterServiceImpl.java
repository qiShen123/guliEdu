package com.itguigu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.eduService.entity.EduChapter;
import com.itguigu.eduService.entity.chapter.ChapterVo;
import com.itguigu.eduService.mapper.EduChapterMapper;
import com.itguigu.eduService.service.IEduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itguigu.eduService.service.IEduVideoService;
import com.itguigu.serviceBase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements IEduChapterService {

    @Autowired
    private IEduVideoService iEduVideoService;
    @Override
    public List<ChapterVo> getChapters(String courseId) {
        List<ChapterVo> chapters = baseMapper.getChapter(courseId);
        return chapters;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("chapterId",chapterId);
        int count = iEduVideoService.count(wrapper);
        if (0 == count){
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }else {
            throw new GuliException(20001,"不能删除");
        }
    }
}
