package com.itguigu.statistics.schedule;

import com.itguigu.statistics.service.IDailyService;
import com.itguigu.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DailySchedule {

    @Autowired
    private IDailyService dailyService;

    @Scheduled(cron = "* * 0,1 * * ?")
//    @Scheduled(cron = "0/5 * * * * ?")
    public void inputMessage() {
        Date s = DateUtil.addDays(new Date(), -1);
        String date = DateUtil.formatDate(s);
        dailyService.getUserNum(date);
    }
}
