package com.itguigu.eduService.service;

import com.itguigu.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itguigu.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-05-08
 */
public interface IEduSubjectService extends IService<EduSubject> {

    public void saveSubject(MultipartFile file, IEduSubjectService subjectService);

    public List<OneSubject> getSubject();
}
