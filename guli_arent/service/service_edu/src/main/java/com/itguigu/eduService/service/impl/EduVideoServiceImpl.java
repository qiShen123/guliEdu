package com.itguigu.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itguigu.eduService.client.VodClient;
import com.itguigu.eduService.entity.EduChapter;
import com.itguigu.eduService.entity.EduVideo;
import com.itguigu.eduService.mapper.EduVideoMapper;
import com.itguigu.eduService.service.IEduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author shenqi
 * @since 2020-06-16
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements IEduVideoService {
    @Autowired
    private VodClient vodClient;

    @Override
    public int count(QueryWrapper<EduChapter> wrapper) {
        return 0;
    }


    //删除单个视频
    @Override
    public boolean deleteVideo(String videoId) {

        EduVideo eduVideo = baseMapper.selectById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!videoSourceId.isEmpty()){
            vodClient.removeVideo(videoSourceId);
        }
        int b = baseMapper.deleteById(videoId);
        if (0==b){
            return false;
        }else {
            return true;
        }

    }
}
