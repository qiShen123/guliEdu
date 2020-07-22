package com.itguigu.eduService.controller;


import com.itguigu.eduService.entity.EduChapter;
import com.itguigu.eduService.entity.chapter.ChapterVo;
import com.itguigu.eduService.service.IEduChapterService;
import com.itguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/eduService/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private IEduChapterService chapterService;

    @GetMapping("{courseId}")
    public R getChapters(@PathVariable("courseId") String courseId) {
        List<ChapterVo> chapters = chapterService.getChapters(courseId);
        return R.ok().data("chapters", chapters);
    }

    @PostMapping
    public R saveChapters(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    @GetMapping("/getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable("chapterId") String id) {
        EduChapter chapter = chapterService.getById(id);
        return R.ok().data("chapter", chapter);
    }

    @PutMapping
    public R updateChapterInfo(@RequestBody EduChapter chapter) {
        chapterService.updateById(chapter);
        return R.ok();
    }

    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable("chapterId") String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
