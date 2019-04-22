package com.datahome.filter;

import com.datahome.util.FieldCleanRequestWrapperUtil;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 字段清理Util
 * 除首尾空格、\t、\v、\f
 * 将""替换为null
 * 转义html
 */
@Configuration
@WebFilter(urlPatterns = {"*.jsp", "*.do"})
public class FieldCleanFilter implements Filter {

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new FieldCleanRequestWrapperUtil((HttpServletRequest) servletRequest), servletResponse);
    }
}
