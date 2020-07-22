package com.itguigu.eduService.controller.interceptor;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itguigu.eduService.client.OrderClient;
import com.itguigu.eduService.entity.EduCourse;
import com.itguigu.eduService.entity.chapter.ChapterVo;
import com.itguigu.eduService.entity.vo.CourseVo;
import com.itguigu.eduService.entity.vo.frontVo.CourseFrontVo;
import com.itguigu.eduService.entity.vo.frontVo.CourseWebVo;
import com.itguigu.eduService.service.IEduChapterService;
import com.itguigu.eduService.service.IEduCourseService;
import com.itguigu.commonutils.JwtUtils;
import com.itguigu.commonutils.R;
import com.itguigu.commonutils.orderVo.EduCourseOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduService/front/courseFront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private IEduCourseService courseService;
    @Autowired
    private IEduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    /**
     * 课程分页查询
     *
     * @param page
     * @param limit
     * @param courseFrontVo
     * @return
     */
    @PostMapping("/{page}/{limit}")
    public R getFrontCourse(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                            @RequestBody CourseFrontVo courseFrontVo) {
        Page<EduCourse> coursePage = new Page<>(page, limit);
        Map<String, Object> courseMap = courseService.getFrontCourse(coursePage, courseFrontVo);
        return R.ok().data("courseMap", courseMap);
    }

    /**
     * 查询课程想去
     *
     * @param courseId
     * @return
     */
    @GetMapping("{courseId}")
    public R getCourseInfo(@PathVariable("courseId") String courseId,
                           HttpServletRequest request) {
        List<ChapterVo> chapters = chapterService.getChapters(courseId);

        CourseWebVo courseInfo = courseService.getCourseFrontInfo(courseId);

        boolean isBuy = orderClient.getIsBuy(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("courseInfo", courseInfo).data("chapters", chapters).data("isBuy", isBuy);
    }

    //根据id获取课程对象，用于远程调用
    @GetMapping("/getCourseInfoOrder/{courseId}")
    public EduCourseOrder getCourseFrontOrder(@PathVariable("courseId") String courseId) {
        CourseVo courseInfo = courseService.getCourseInfo(courseId);
        EduCourseOrder courseOrder = new EduCourseOrder();
        BeanUtils.copyProperties(courseInfo, courseOrder);
        return courseOrder;
    }
}
