package com.itguigu.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.itguigu.eduService.entity.EduSubject;
import com.itguigu.eduService.entity.excel.SubjectData;
import com.itguigu.eduService.entity.subject.OneSubject;
import com.itguigu.eduService.listener.SubjectExcelListener;
import com.itguigu.eduService.mapper.EduSubjectMapper;
import com.itguigu.eduService.service.IEduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-05-08
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements IEduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, IEduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
//            EasyExcel.read(inputStream, SubjectData.class, new DemoDataListener2()).sheet().doRead();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
//            EasyExcel.read(inputStream, Student.class,new DemoDataListener()).sheet().doRead();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getSubject() {
        List<OneSubject> subject = baseMapper.getSubject();
        return subject;
    }
}
