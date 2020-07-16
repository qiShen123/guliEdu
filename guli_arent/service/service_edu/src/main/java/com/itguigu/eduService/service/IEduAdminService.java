package com.itguigu.eduService.service;

import com.itguigu.eduService.entity.EduAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shenqi
 * @since 2020-07-06
 */
public interface IEduAdminService extends IService<EduAdmin> {

    String login(EduAdmin admin);
}
