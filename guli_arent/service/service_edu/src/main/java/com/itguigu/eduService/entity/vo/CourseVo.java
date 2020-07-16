package com.itguigu.eduService.entity.vo;

import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CourseVo {
    private String id;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String teacherName;
    private String cover;
    private String oneSubject;
    private String twoSubject;
}
