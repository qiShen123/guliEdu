package com.itguigu.eduService.mapper;

import com.itguigu.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itguigu.eduService.entity.subject.OneSubject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author shenqi
 * @since 2020-05-08
 */
@Component
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {
    List<OneSubject> getSubject();
}
