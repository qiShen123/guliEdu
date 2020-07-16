package com.itguigu.serviceBase.exceptionhandler;

import com.itguihu.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        if (e instanceof GuliException) {
            e.printStackTrace();
            GuliException exception = (GuliException) e;
            if (50014==exception.getCode()){
                return R.noLogin().message(exception.getMsg());
            }else{
                return R.error().message(exception.getMsg());
            }
        }
        return R.error().message("出异常啦");
    }
}
