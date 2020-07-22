package com.itguigu.eduService.controller.interceptor;

import com.itguigu.eduService.entity.subject.OneSubject;
import com.itguigu.eduService.service.IEduSubjectService;
import com.itguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eduService/front/subject")
@CrossOrigin
public class SubjectFrontController {
    @Autowired
    private IEduSubjectService eduSubjectService;

    @GetMapping
    public R getSubject(){
        List<OneSubject> subject = eduSubjectService.getSubject();
        return R.ok().data("list",subject);
    }
}
