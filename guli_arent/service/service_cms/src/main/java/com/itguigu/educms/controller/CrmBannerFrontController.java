package com.itguigu.educms.controller;


import com.itguigu.educms.entity.CrmBanner;
import com.itguigu.educms.service.ICrmBannerService;
import com.itguihu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器 前台显示接口
 * </p>
 *
 * @author shenqi
 * @since 2020-06-29
 */
@RestController
@RequestMapping("/eduCms/frontBanner")
@CrossOrigin
public class CrmBannerFrontController {
    @Autowired
    private ICrmBannerService bannerService;

    @GetMapping
    public R getAllBanner() {
        List<CrmBanner> banners = bannerService.getAllBanner();
        return R.ok().data("banners",banners);
    }
}
