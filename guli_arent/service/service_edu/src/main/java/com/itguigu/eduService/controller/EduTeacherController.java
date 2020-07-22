package com.itguigu.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itguigu.eduService.entity.EduTeacher;
import com.itguigu.eduService.entity.vo.TeacherQuery;
import com.itguigu.eduService.service.IEduTeacherService;
import com.itguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-05-04
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/eduService/edu_teacher")
public class EduTeacherController {
    @Autowired
    private IEduTeacherService teacherService;

    @GetMapping()
    public R findAll() {
        List<EduTeacher> eduTeachers = teacherService.list(null);
        return R.ok().data("items", eduTeachers);
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable("id") String id) {
        boolean b = teacherService.removeById(id);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    public R findPage(@PathVariable("current") Integer current, @PathVariable("limit") Integer limit) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        IPage<EduTeacher> page = teacherService.page(teacherPage, null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        R data = R.ok().data("total", total).data("records", records);
        return data;
    }

    @PostMapping("pageTeacher/{current}/{limit}")
    public R findPageCondition(@PathVariable("current") Integer current,
                               @PathVariable("limit") Integer limit,
                               @RequestBody(required = false) TeacherQuery teacherQuery) {
        //构建分页对象
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        //构建条件对象
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }

        IPage<EduTeacher> page = teacherService.page(teacherPage, wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        R data = R.ok().data("total", total).data("records", records);
        return data;
    }

    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher) {
        boolean b = teacherService.save(teacher);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("{id}")
    public R getTeacherById(@PathVariable("id") String id) {
        EduTeacher teacher = teacherService.getById(id);
        R teacher1 = R.ok().data("teacher", teacher);
        return teacher1;
    }

    @PutMapping("update")
    public R updateTeacher(@RequestBody EduTeacher teacher) {
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}
