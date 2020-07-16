package com.itguigu.ucenter.service.impl;

import com.itguigu.ucenter.service.IMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MemberServiceImplTest {
    @Autowired
    private IMemberService memberService;

    @Test
    public void testSelectNum() {
        Integer userNum = memberService.getUserNum("2020-07-05");
        System.out.println(userNum);
    }
}
