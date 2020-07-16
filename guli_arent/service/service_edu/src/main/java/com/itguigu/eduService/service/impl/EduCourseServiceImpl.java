package com.itguigu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itguigu.eduService.client.VodClient;
import com.itguigu.eduService.entity.EduCourse;
import com.itguigu.eduService.entity.EduCourseDescription;
import com.itguigu.eduService.entity.EduVideo;
import com.itguigu.eduService.entity.vo.frontVo.CourseFrontVo;
import com.itguigu.eduService.entity.vo.CourseInfoVo;
import com.itguigu.eduService.entity.vo.CourseVo;
import com.itguigu.eduService.entity.vo.frontVo.CourseWebVo;
import com.itguigu.eduService.mapper.EduCourseMapper;
import com.itguigu.eduService.service.IEduChapterService;
import com.itguigu.eduService.service.IEduCourseDescriptionService;
import com.itguigu.eduService.service.IEduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itguigu.eduService.service.IEduVideoService;
import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguihu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
@Service
@Slf4j
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements IEduCourseService {

    @Autowired
    private IEduCourseDescriptionService iEduCourseDescriptionService;

    @Autowired
    private IEduChapterService chapterService;

    @Autowired
    private IEduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;


    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (0 == insert) {
            throw new GuliException(20001, "导入失败");
        }
        String id = eduCourse.getId();
        EduCourseDescription eduCourseDescription= new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(id);
        iEduCourseDescriptionService.save(eduCourseDescription);
        return id;
    }

    /**
     * 查询课程和简介
     * @param id
     * @return
     */
    @Override
    public CourseInfoVo getCourseVo(String id) {
        CourseInfoVo courseVo = baseMapper.getCourseVo(id);
        return courseVo;
    }

    @Override
    public void updateCourse(CourseInfoVo courseInfoVo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,course);
        int insert = baseMapper.updateById(course);
        if (0 == insert) {
            throw new GuliException(20001, "更新失败");
        }
        EduCourseDescription description = new EduCourseDescription();
        description.setId(course.getId());
        description.setDescription(courseInfoVo.getDescription());
        iEduCourseDescriptionService.updateById(description);
    }

    /**
     * 查询实际课程
     * @param courseId
     * @return
     */
    @Override
    public CourseVo getCourseInfo(String courseId) {
        CourseVo course = baseMapper.getCourseInfo(courseId);
        return course;
    }

    @Override
    public R deleteCourse(String courseId) {
        List<String> videosId = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);

        QueryWrapper selectWrapper = new QueryWrapper();
        selectWrapper.eq("course_id",courseId);
        selectWrapper.select("video_source_id");
        List<EduVideo> videos = eduVideoService.list(selectWrapper);
        for (EduVideo video:videos){
            String videoId=video.getVideoSourceId();
            if (!videoId.isEmpty()){
                videosId.add(videoId);
            }
        }
        R r = vodClient.deleteMoreVideo(videosId);
        if (r.getCode()==20001){
            throw new GuliException(20001,"熔断器爆错");
        }

        eduVideoService.remove(wrapper);
        chapterService.remove(wrapper);
        iEduCourseDescriptionService.removeById(courseId);
        baseMapper.deleteById(courseId);
        return R.ok();
    }

    /**
     * 前端条件分页查询接口
     * @param coursePage
     * @param courseFrontVo
     * @return
     */
    @Override
    public Map<String, Object> getFrontCourse(Page<EduCourse> coursePage, CourseFrontVo courseFrontVo) {
        Map<String ,Object> courseMap = new HashMap<>();
        String subjectParentId = courseFrontVo.getSubjectParentId();//一级分类
        String subjectId = courseFrontVo.getSubjectId();//二级分类
        String buyCountSort = courseFrontVo.getBuyCountSort();//销量
        String gmtCreateSort = courseFrontVo.getGmtCreateSort();//发布时间
        String priceSort = courseFrontVo.getPriceSort();//价格

        QueryWrapper wrapper = new QueryWrapper();
        if (!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)){
            wrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(gmtCreateSort)){
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(buyCountSort)){
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(priceSort)){
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(coursePage,wrapper);

        List<EduCourse> items = coursePage.getRecords();
        long size = coursePage.getSize();
        long total = coursePage.getTotal();
        long pages = coursePage.getPages();
        long current = coursePage.getCurrent();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();
        courseMap.put("items", items);
        courseMap.put("size", size);
        courseMap.put("total", total);
        courseMap.put("pages", pages);
        courseMap.put("current", current);
        courseMap.put("hasNext", hasNext);
        courseMap.put("hasPrevious", hasPrevious);
        return courseMap;
    }

    @Override
    public CourseWebVo getCourseFrontInfo(String courseId) {
        CourseWebVo courseWbwVo = baseMapper.getCourseFrontInfo(courseId);
        return courseWbwVo;
    }
}
