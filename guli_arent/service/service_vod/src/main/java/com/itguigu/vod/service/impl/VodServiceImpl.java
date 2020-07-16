package com.itguigu.vod.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguigu.vod.service.IVodService;
import com.itguigu.vod.utils.VideoPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.itguigu.vod.utils.DefaultAcsClient.initVodClient;

@Service
@Slf4j
public class VodServiceImpl implements IVodService {
    //上传视频
    @Override
    public String uploadVideo(MultipartFile video) {
        String fileName = video.getOriginalFilename();
        String title = video.getOriginalFilename();
        String id = null;
        try {
            InputStream inputStream = video.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(VideoPropertiesUtils.ACCESS_KEY_ID, VideoPropertiesUtils.ACCESS_KEY_SECRET,
                    title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            id = response.getVideoId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    //删除视频
    @Override
    public void deleteVideo(String videoId) {
        this.deleteVideoById(videoId);
    }

    //批量删除视频
    @Override
    public void deleteMoreVideo(List<String> videoIds) {
        String videoId = StringUtils.join(videoIds,",");
        this.deleteVideoById(videoId);
    }

    /**
     * 根据视频id获取凭证
     * @param videoId
     * @return
     */
    @Override
    public String getPlayAuth(String videoId) {
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            DefaultAcsClient defaultAcsClient = initVodClient(VideoPropertiesUtils.ACCESS_KEY_ID, VideoPropertiesUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            response = defaultAcsClient.getAcsResponse(request);
        } catch (Exception e) {
            throw new GuliException(20001,"播放失败");
        }
        return response.getPlayAuth();
    }


    public void deleteVideoById(String videoIds){
        DefaultAcsClient client = null;
        DeleteVideoResponse response = new DeleteVideoResponse();
        DeleteVideoRequest request = new DeleteVideoRequest();
        try {
            client = initVodClient(VideoPropertiesUtils.ACCESS_KEY_ID, VideoPropertiesUtils.ACCESS_KEY_SECRET);
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoIds);
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
