package com.itguigu.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itguigu.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itguigu.eduService.entity.vo.frontVo.CourseFrontVo;
import com.itguigu.eduService.entity.vo.CourseInfoVo;
import com.itguigu.eduService.entity.vo.CourseVo;
import com.itguigu.eduService.entity.vo.frontVo.CourseWebVo;
import com.itguihu.commonutils.R;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
public interface IEduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseVo(String id);

    void updateCourse(CourseInfoVo courseInfoVo);

     CourseVo getCourseInfo(String courseId);

    R deleteCourse(String courseId);

    Map<String,Object> getFrontCourse(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo);

    CourseWebVo getCourseFrontInfo(String courseId);

}
