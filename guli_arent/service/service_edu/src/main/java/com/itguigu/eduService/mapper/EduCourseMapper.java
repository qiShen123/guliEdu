package com.itguigu.eduService.mapper;

import com.itguigu.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itguigu.eduService.entity.vo.CourseInfoVo;
import com.itguigu.eduService.entity.vo.CourseVo;
import com.itguigu.eduService.entity.vo.frontVo.CourseFrontVo;
import com.itguigu.eduService.entity.vo.frontVo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseInfoVo getCourseVo(String id);

    CourseVo getCourseInfo(String courseId);

    CourseWebVo getCourseFrontInfo(String courseId);
}
