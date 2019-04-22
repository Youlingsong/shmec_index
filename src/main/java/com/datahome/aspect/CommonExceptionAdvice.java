package com.datahome.aspect;

import com.alibaba.fastjson.JSON;
import com.datahome.util.CommonUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xl
 * @Description: 全局异常处理类
 * @Date: Create in 2018/7/5 11:15
 */

@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {

    /**
     * 所有异常报错
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        exception.printStackTrace();
        String message = exception.getMessage();
        Object resultMessage = "服务器异常，请联系管理员！";
        if (message != null && message.startsWith("{") && message.contains("status")) {
            resultMessage = JSON.parseObject(message).get("message");
        }
        String errorClassesName = exception.getClass().getSimpleName();
        if ("MaxUploadSizeExceededException".equals(errorClassesName)) {
            resultMessage = "文件过大，请重新分配！";
        }
        return CommonUtil.format(5000, resultMessage);
    }
}
