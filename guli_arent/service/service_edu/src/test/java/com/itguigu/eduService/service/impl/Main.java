package com.itguigu.eduService.service.impl;

import com.itguigu.serviceBase.exceptionhandler.GuliException;
import com.itguihu.commonutils.ResultEnum;

import java.util.Scanner;
class TData {
    private String month = "0";
    private String day = "0";
    private String year = "0";

    public TData(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public static void putTime() {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入年月日，以空格分隔");
        String data = in.nextLine();
        String[] date = data.split(" ");
        System.out.println(date[2] + "/" + date[1] + "/" + date[0]);
    }
}

public class Main {
    public static void main(String[] ages) {
        throw new GuliException(50014,"未登录");
    }
}
