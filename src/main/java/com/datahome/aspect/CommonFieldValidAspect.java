package com.datahome.aspect;

import com.datahome.util.CommonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;


/**
 * @Author xl
 * @Description:JSR303 bean validation异常信息切面
 * 优先级应该定义为仅次于@ControllerAdvice和RateLimiterAspect
 * 字段验证消息统一为一个消息：xx的值或格式错误，一是为了减轻后台工作量，二是避免暴露正确参数给爬虫可乘之机
 * 前台部分字段校验可以用切实消息，并用jQuery.i18n国际化
 * @Date: Create in 2018/7/5 11:34
 */

@Component
@Aspect
@Order(2)
public class CommonFieldValidAspect {

    @Pointcut("execution(* com.datahome.controller.*.*(.., org.springframework.validation.BindingResult, ..))")
    public void aspect() {}

    @Around(value = "aspect()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] objects = pjp.getArgs();
        BindingResult result = null;

        for (Object object : objects) {
            if (object instanceof BindingResult) {
                result = (BindingResult) object;
                break;
            }
        }

        if (result == null) return pjp.proceed();

        if (result.hasErrors()) {
            //每次只返回首个错误（方便国际化）
            List<FieldError> fieldErrors = result.getFieldErrors();
            String errorField = fieldErrors.get(0).getField();
            System.out.println(" --error: "+errorField);
            //主动反馈给用户的错误，不用记录日志
            String msg = String.format("%s的值或格式错误", errorField);
            //CommonUtil.LOGGER.debug(msg);

            return CommonUtil.format(5000, msg);
        }

        return pjp.proceed();
    }
}
