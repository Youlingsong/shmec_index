package com.datahome.aspect;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 跨域切面
 */
@ControllerAdvice
public class CorsControllerAdviceAspect implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Boolean b1 = methodParameter.getDeclaringClass().getAnnotation(RestController.class) != null;
        Boolean b2 = methodParameter.getMethod().getAnnotation(ResponseBody.class) != null;
        return b1 || b2;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse serverHttpResponse) {
        ServletServerHttpResponse sshr = (ServletServerHttpResponse) serverHttpResponse;
        HttpServletResponse response = sshr.getServletResponse();
        response.addHeader("Access-Control-Allow-Origin", "*");
        return body;
    }
}
