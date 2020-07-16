package com.itguigu.eduService.client;

import com.itguigu.eduService.client.hystrix.VodFileDegradeFeignClient;
import com.itguihu.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Component
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    @DeleteMapping("/eduvod/video/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/eduvod/video/deleteMoreVideo")
    R deleteMoreVideo(@RequestBody List<String> videoIds);
}
