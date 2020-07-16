package com.itguigu.educms.controller;


import com.itguigu.educms.entity.CrmBanner;
import com.itguigu.educms.service.ICrmBannerService;
import com.itguihu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器  后台管理接口
 * </p>
 *
 * @author shenqi
 * @since 2020-06-29
 */
@RestController
@RequestMapping("/eduCms/adminBanner")
@CrossOrigin
public class CrmBannerAdminController {
    @Autowired
    private ICrmBannerService bannerService;

    @GetMapping("{page}/{limit}")
    public R getBannerPages(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit){
        List<CrmBanner> banners= bannerService.getPages(page,limit);
        return R.ok().data("banners",banners);
    }

    @PostMapping
    public R addBanner(@RequestBody CrmBanner banner){
        boolean b = bannerService.save(banner);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PutMapping
    public R changeBanner(@RequestBody CrmBanner banner){
        boolean b = bannerService.updateById(banner);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("{id}")
    public R deleteBanner(@PathVariable("id")String id){
        boolean b = bannerService.removeById(id);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
