package com.itguigu.eduService.controller;
import com.itguihu.commonutils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UnLoginController {

    @RequestMapping("unLogin")
    public R returnUnLogin() {
        return R.noLogin();
    }

}
