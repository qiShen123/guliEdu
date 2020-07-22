package com.itguigu.eduService.controller;

import com.itguigu.eduService.entity.EduAdmin;
import com.itguigu.eduService.service.IEduAdminService;
import com.itguigu.commonutils.JwtUtils;
import com.itguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/eduService/admin")
@Slf4j
public class EduLoginController {
    @Autowired
    private IEduAdminService adminService;
    @PostMapping("login")
    public R login(@RequestBody EduAdmin admin) {
        String token = adminService.login(admin);
        log.info(admin.getUsername()+"请求登录");
        return R.ok().data("token", token);
    }

    @GetMapping("info")
    public R getInfo(@RequestParam("token")String token) {
        String id = JwtUtils.getMemberIdByJwtToken(token);
        EduAdmin admin = adminService.getById(id);
        String roles = admin.getRoles();
        String nickname = admin.getNickname();
        String avatar = admin.getAvatar();
        return R.ok().data("roles", roles).data("name", nickname).data("avatar", avatar);
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }
}
