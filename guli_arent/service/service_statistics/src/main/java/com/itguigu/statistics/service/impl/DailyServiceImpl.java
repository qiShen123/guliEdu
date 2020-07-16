package com.itguigu.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.statistics.client.UCenterClient;
import com.itguigu.statistics.entity.Daily;
import com.itguigu.statistics.mapper.DailyMapper;
import com.itguigu.statistics.service.IDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-12
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements IDailyService {

    @Autowired
    private UCenterClient uCenterClient;

    @Override
    public Integer getUserNum(String date) {
        Integer userNum = uCenterClient.getUserNum(date);

        Daily daily = new Daily();
        daily.setDateCalculated(date);
        daily.setRegisterNum(userNum);
        daily.setLoginNum(RandomUtils.nextInt(100, 200));
        daily.setCourseNum(RandomUtils.nextInt(100, 200));
        daily.setVideoViewNum(RandomUtils.nextInt(100, 200));

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("date_calculated", date);
        Integer integer = baseMapper.selectCount(wrapper);
        if (integer > 0) {
            Daily daily1 = baseMapper.selectOne(wrapper);
            daily.setId(daily1.getId());
            baseMapper.updateById(daily);
        } else {
            baseMapper.insert(daily);
        }
        return userNum;
    }

    @Override
    public Map<String, Object> showData(String type, String begin, String end) {
        Map<String, Object> objectMap = new HashMap<>();
        List<Integer> numList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("date_calculated", type);
        wrapper.between("date_calculated", begin, end);
        List<Daily> list = baseMapper.selectList(wrapper);


        for (Daily daily : list) {
            dateList.add(daily.getDateCalculated());
            switch (type) {
                case "course_num":
                    numList.add(daily.getCourseNum());
                    break;
                case "register_num":
                    numList.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    numList.add(daily.getLoginNum());
                    break;
                case "video_view_num":
                    numList.add(daily.getVideoViewNum());
                    break;
            }
        }
        objectMap.put("numList", numList);
        objectMap.put("dateList", dateList);

        return objectMap;
    }
}
