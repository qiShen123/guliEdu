package com.itguigu.msmService.controller;

import com.itguigu.msmService.service.IMsmService;
import com.itguihu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduMsm/msm")
@CrossOrigin
public class MsmController {
    @Autowired
    private IMsmService msmService;

    @GetMapping("{phoneNum}")
    public R getCode(@PathVariable("phoneNum") String phoneNum){
        String code = msmService.getCode(phoneNum);
        if (null!=code){
            return R.ok();
        }else {
            return R.error().message("发送失败");
        }
    }
}
