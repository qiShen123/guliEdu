package com.itguigu.eduService.controller;


import com.itguigu.eduService.entity.subject.OneSubject;
import com.itguigu.eduService.service.IEduSubjectService;
import com.itguihu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-05-08
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/eduService/subject")
public class EduSubjectController {
    @Autowired
    private IEduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }

    @GetMapping
    public R getSubject(){
        List<OneSubject> subject = eduSubjectService.getSubject();
        return R.ok().data("list",subject);
    }
}
