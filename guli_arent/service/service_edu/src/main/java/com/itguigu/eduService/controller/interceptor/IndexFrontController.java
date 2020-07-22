package com.itguigu.eduService.controller.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.eduService.entity.EduCourse;
import com.itguigu.eduService.entity.EduTeacher;
import com.itguigu.eduService.service.IEduCourseService;
import com.itguigu.eduService.service.IEduTeacherService;
import com.itguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("eduService/front/indexFront")
@CrossOrigin
public class IndexFrontController {
    @Autowired
    private IEduCourseService courseService;

    @Autowired
    private IEduTeacherService teacherService;

    /**
     * 查询热门课程8，热门导师4
     * @return
     */
    @GetMapping
    @Cacheable(value = "teacherAndCourse",key = "'listInfo'")
    public R getIndexFront(){
        QueryWrapper<EduCourse> course = new QueryWrapper<>();
        course.orderByDesc("id");
        course.last("limit 8");
        List courses = courseService.list(course);

        QueryWrapper<EduTeacher> teacher = new QueryWrapper<>();
        teacher.orderByDesc("id");
        teacher.last("limit 4");
        List<EduTeacher> teachers = teacherService.list(teacher);

        return R.ok().data("courses",courses).data("teachers",teachers);
    }

}
