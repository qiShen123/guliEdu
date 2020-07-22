package com.itguigu.eduService.client.hystrix;

import com.itguigu.eduService.client.VodClient;
import com.itguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 微服务远程调用失败后处理方案
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public R removeVideo(String videoId) {
        return R.error().message("视频删除出错");
    }

    @Override
    public R deleteMoreVideo(List<String> videoIds) {
        return R.error().message("视频删除出错");
    }
}
