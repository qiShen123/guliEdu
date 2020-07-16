package com.itguigu.eduService.service;

import com.itguigu.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-05-04
 */
public interface IEduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherFront(Integer page, Integer limit);
}
