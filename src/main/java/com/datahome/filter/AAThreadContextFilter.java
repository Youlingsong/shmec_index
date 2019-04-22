package com.datahome.filter;

import com.datahome.util.CommonUtil;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * filter 按照注解名称的排序顺序执行，可通过特殊命名 优先执行
 */
@WebFilter(urlPatterns = {"*.do"})
public class AAThreadContextFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {}

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (!ThreadContext.containsKey("ip")) ThreadContext.put("ip", CommonUtil.clienIP(request));
        chain.doFilter(req, res);
    }
}