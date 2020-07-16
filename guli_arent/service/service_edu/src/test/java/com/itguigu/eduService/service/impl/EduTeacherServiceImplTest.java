package com.itguigu.eduService.service.impl;

import com.itguigu.eduService.entity.EduTeacher;
import com.itguigu.eduService.entity.subject.OneSubject;
import com.itguigu.eduService.entity.subject.TwoSubject;
import com.itguigu.eduService.mapper.EduSubjectMapper;
import com.itguigu.eduService.service.IEduSubjectService;
import com.itguigu.eduService.service.IEduTeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EduTeacherServiceImplTest {
    @Autowired
    private IEduTeacherService teacherService;
    @Autowired
    private EduSubjectMapper subjectMapper;

    @Test
    public void testInsert(){
        EduTeacher teacher = new EduTeacher();
        teacher.setId("4");
        teacher.setName("张少林");
        teacher.setIntro("硕士学历");
        teacher.setCareer("主任");
        teacher.setLevel(2);
        teacher.setAvatar("大头");
        teacher.setSort(50);

        teacherService.save(teacher);
    }
    @Test
    public void testDeleteById(){
        teacherService.removeById(1);
    }

    @Test
    public void testSubjectMapper(){
        List<OneSubject> subject = subjectMapper.getSubject();
        for (OneSubject oneSubject : subject) {
            System.out.println(oneSubject);
        }
    }
}
