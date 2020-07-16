package com.itguigu.eduService.service.impl;

import com.itguigu.eduService.entity.chapter.ChapterVo;
import com.itguigu.eduService.service.IEduChapterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EduChapterServiceImplTest {
    @Autowired
    private IEduChapterService chapterService;
    @Test
    public void getChapter(){
        List<ChapterVo> chapters = chapterService.getChapters("1273104122035421185");
        System.out.println(chapters);
    }

}
