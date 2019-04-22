package com.datahome.exception;

import com.datahome.util.CommonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String exceptionHandler(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        // 获取最底层的异常
        String message = e.getClass().getName() + " : " + e.getMessage();
        // String message=e.getMessage();
        Throwable t = e.getCause();
        if (t != null) {
            Throwable temp = null;
            for (; ; ) {
                if (t == null) {
                    message = temp.getClass().getName() + " : " + temp.getMessage();
                    // message=temp.getMessage();
                    break;
                } else {
                    temp = t;
                    t = t.getCause();
                }
            }
        }
        //if (sw != null) logger.error(sw.toString());
        return CommonUtil.format(1, message);
    }
}
