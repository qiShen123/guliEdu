package com.itguigu.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itguigu.educms.entity.CrmBanner;
import com.itguigu.educms.mapper.CrmBannerMapper;
import com.itguigu.educms.service.ICrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-29
 */
@Service("crmBannerImpl")
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements ICrmBannerService {
    /**
     * 后台分页查询接口
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<CrmBanner> getPages(Integer page, Integer limit) {
        Page<CrmBanner> pages = new Page<>(page,limit);
        IPage<CrmBanner> crmBannerIPage = baseMapper.selectPage(pages, null);
        List<CrmBanner> pageMassage = crmBannerIPage.getRecords();
        return pageMassage;
    }

    /**
     * 前台查询所有banner数据
     * @return
     */
    @Override
    @Cacheable(value = "banner",key = "'selectInfoValue'")
    public List<CrmBanner> getAllBanner() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> banners = baseMapper.selectList(null);
        return banners;
    }
}
