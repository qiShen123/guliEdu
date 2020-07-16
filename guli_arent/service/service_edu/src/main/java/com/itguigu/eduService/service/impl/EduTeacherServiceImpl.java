package com.itguigu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itguigu.eduService.entity.EduTeacher;
import com.itguigu.eduService.mapper.EduTeacherMapper;
import com.itguigu.eduService.service.IEduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-05-04
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements IEduTeacherService {

    @Override
    public Map<String, Object> getTeacherFront(Integer page, Integer limit) {
        Map<String, Object> objectMap = new HashMap<>();
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(teacherPage, wrapper);

        List<EduTeacher> records = teacherPage.getRecords();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        long pages = teacherPage.getPages();
        long current = teacherPage.getCurrent();
        boolean next = teacherPage.hasNext();
        boolean previous = teacherPage.hasPrevious();
        objectMap.put("records", records);
        objectMap.put("size", size);
        objectMap.put("total", total);
        objectMap.put("pages", pages);
        objectMap.put("current", current);
        objectMap.put("next", next);
        objectMap.put("previous", previous);

        return objectMap;
    }
}
