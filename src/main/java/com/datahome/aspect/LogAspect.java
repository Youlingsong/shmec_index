package com.datahome.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.datahome.service.LogService;
import com.datahome.util.CommonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xl
 * @Description: 处理log
 * @Date: Create in 2018/10/17 11:02
 */

@Component
@Aspect
public class LogAspect {

    @Resource
    private HttpServletRequest request;

    @Resource
    private LogService logService;

    @Around("execution(* com.datahome.controller.*.*(..))")
    public Object log(ProceedingJoinPoint jp) throws Throwable {

        Map<String, Object> userInfoMap = getUserInfo();

        // 执行目标方法
        Object rvt = jp.proceed();
        if (rvt != null) {
            JSONObject json = new JSONObject();
            try {
                json = JSON.parseObject(rvt.toString());
            } catch (Exception e) {

            }

            String errorMessage = "";
            String status = "";
            if (json.containsKey("status")) {
                status = String.valueOf(json.get("status"));
                if (!"2000".equals(status)) {
                    errorMessage = (String) json.get("data");
                }
            } else {
                errorMessage = json.toJSONString();
            }
            logService.addLog((Integer) userInfoMap.get("userId"), (String) userInfoMap.get("userName"), request.getServletPath(), CommonUtil.clienIP(request), status, errorMessage, (String) userInfoMap.get("userType"));
        }
        return rvt;
    }


    //获取用户信息
    public Map<String, Object> getUserInfo() {
        Map<String, Object> resultMap = new HashMap<>();

        Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("staff");
        if (map != null) {
            resultMap.put("userType", "staff");
        } else {
            map = (Map<String, Object>) request.getSession().getAttribute("account/**/");
            if (map != null) {
                resultMap.put("userType", "account");
            }
        }
        if (map == null) {
            map = new HashMap<>();
            map.put("userId", "null");
            map.put("userName", "null");
        } else {
            resultMap.put("userId", map.get("id"));
            resultMap.put("userName", map.get("name"));
        }
        return resultMap;
    }
}
