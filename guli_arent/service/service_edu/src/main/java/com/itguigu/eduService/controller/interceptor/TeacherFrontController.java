package com.itguigu.eduService.controller.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.eduService.entity.EduCourse;
import com.itguigu.eduService.entity.EduTeacher;
import com.itguigu.eduService.service.IEduCourseService;
import com.itguigu.eduService.service.IEduTeacherService;
import com.itguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/front/teacherFront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private IEduTeacherService teacherService;
    @Autowired
    private IEduCourseService courseService;

    @GetMapping("{page}/{limit}")
    public R getTeacherFront(@PathVariable("page")Integer page,@PathVariable("limit") Integer limit){
        Map<String,Object> teachers = teacherService.getTeacherFront(page,limit);
        return R.ok().data("teachers",teachers);
    }

    @GetMapping("/{teacherId}")
    public R getTeacherInfo(@PathVariable("teacherId")String teacherId){
        EduTeacher teacher = teacherService.getById(teacherId);

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courses = courseService.list(wrapper);
        if (null!=teacher){
            return R.ok().data("teacher",teacher).data("courses",courses);
        }else {
            return R.error();
        }
    }
}
