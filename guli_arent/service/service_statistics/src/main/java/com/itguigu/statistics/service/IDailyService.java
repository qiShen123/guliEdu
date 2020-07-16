package com.itguigu.statistics.service;

import com.itguigu.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-12
 */
public interface IDailyService extends IService<Daily> {

    Integer getUserNum(String date);

    Map<String, Object> showData(String type, String begin, String end);
}
