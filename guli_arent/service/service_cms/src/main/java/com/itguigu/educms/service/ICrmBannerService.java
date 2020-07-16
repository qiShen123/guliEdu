package com.itguigu.educms.service;

import com.itguigu.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-29
 */
public interface ICrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getPages(Integer page, Integer limit);

    List<CrmBanner> getAllBanner();
}
