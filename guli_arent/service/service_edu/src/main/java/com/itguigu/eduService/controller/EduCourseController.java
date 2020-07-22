package com.itguigu.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itguigu.eduService.entity.EduCourse;
import com.itguigu.eduService.entity.vo.CourseInfoVo;
import com.itguigu.eduService.entity.vo.CourseQuery;
import com.itguigu.eduService.entity.vo.CourseVo;
import com.itguigu.eduService.service.IEduCourseService;
import com.itguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin
@Slf4j
public class EduCourseController {

    @Autowired
    private IEduCourseService iEduCourseService;

    //课程列表分页查询
    @PostMapping("getAllCourses/{current}/{limit}")
    public R getAllCourses(@PathVariable("current") Integer current,
                           @PathVariable("limit") Integer limit,
                           @RequestBody(required = false) CourseQuery query) {
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper wrapper = new QueryWrapper();
        String title = query.getTitle();
        String status = query.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        IPage coursePage = iEduCourseService.page(page, wrapper);
        log.info(coursePage.toString());
        return R.ok().data("coursePage", coursePage);
    }

    //新建课程信息
    @PostMapping()
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = iEduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    //根据id获取课程信息
    @GetMapping("{id}")
    public R getCourse(@PathVariable("id") String id) {
        CourseInfoVo courseVo = iEduCourseService.getCourseVo(id);
        return R.ok().data("courseVo", courseVo);
    }

    //获取课程发布前的信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable("courseId") String courseId) {
        CourseVo courseInfo = iEduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfo", courseInfo);
    }

    //更新课程基础信息
    @PutMapping()
    public R upDateCourse(@RequestBody CourseInfoVo courseInfoVo) {
        iEduCourseService.updateCourse(courseInfoVo);
        return R.ok();
    }

    //发布课程
    @PutMapping("publish/{courseId}")
    private R publishCourse(@PathVariable("courseId") String courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        iEduCourseService.updateById(course);
        return R.ok();
    }

    //从列表中删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable("courseId") String courseId) {
        R r = iEduCourseService.deleteCourse(courseId);
        return r;
    }
}
