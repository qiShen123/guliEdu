package com.itguigu.eduService.service.impl;

import com.itguigu.eduService.entity.EduCourse;
import com.itguigu.eduService.entity.subject.OneSubject;
import com.itguigu.eduService.entity.subject.TwoSubject;
import com.itguigu.eduService.entity.vo.CourseInfoVo;
import com.itguigu.eduService.entity.vo.CourseVo;
import com.itguigu.eduService.service.IEduCourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EduCourseServiceImplTest {

    @Autowired
    private IEduCourseService service;

    @Test
    public void getCourseVo() {
        CourseInfoVo courseVo = service.getCourseVo("1273157616863870977");
        System.out.println(courseVo);
    }

    @Test
    public void updateCourse(){
        EduCourse course = new EduCourse();
        course.setId("1273177383918227457");
        course.setTitle("燃气费");
        service.updateById(course);
    }

    @Test
    public void getCourseInfoTest(){
        CourseVo courseInfo = service.getCourseInfo("1273648402114043906");
        System.out.println(courseInfo);
    }

    @Test
    void myTest() {
        OneSubject oneSubject = new OneSubject();
        oneSubject.setId("one");
        oneSubject.setTitle("oneTitle");

        TwoSubject twoSubject = new TwoSubject();
        twoSubject.setId("two");

        BeanUtils.copyProperties(twoSubject,oneSubject);

        System.out.println(oneSubject+"  "+twoSubject);
    }

}
