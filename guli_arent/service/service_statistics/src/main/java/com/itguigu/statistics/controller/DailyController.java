package com.itguigu.statistics.controller;


import com.itguigu.statistics.service.IDailyService;
import com.itguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author shenqi
 * @since 2020-07-12
 */
@RestController
@RequestMapping("/statistics")
@CrossOrigin
public class DailyController {

    @Autowired
    private IDailyService dailyService;

    /**
     * 手动添加记录
     *
     * @param date
     * @return
     */
    @PostMapping("/getUserNum/{date}")
    public R getUserNum(@PathVariable("date") String date) {
        Integer userNum = dailyService.getUserNum(date);
        return R.ok().data("userNum", userNum);
    }

    @GetMapping("/{type}/{begin}/{end}")
    public R showData(@PathVariable("type") String type,
                      @PathVariable("begin") String begin,
                      @PathVariable("end") String end) {
        Map<String,Object> dataMap = dailyService.showData(type,begin,end);
        return R.ok().data(dataMap);
    }
}
