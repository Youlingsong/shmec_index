package com.datahome.filter;

import com.datahome.util.CommonUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @Author xl
 * @Description: 用户登录 过滤
 * @Date: Create in 2018/1/13 16:35
 */
//@WebFilter(urlPatterns = {"/index/*", "/indexData/*", "/account"})
public class AccountFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        //项目路径
        String path = request.getServletPath();
        //方法名
        String method = path.substring(path.lastIndexOf("/"), path.length());

        if (!CommonUtil.OPEN_PATH.contains(method)) {
            Map<String, Object> accountMap = (Map<String, Object>) session.getAttribute("account");
            if (accountMap == null) {
                CommonUtil.os(response, 4401, "用户未登录！");
                return;
            }
           /* if ((System.currentTimeMillis() - session.getLastAccessedTime()) > 1000 * 60 * 10) {
                //清空session
                session.invalidate();
                CommonUtil.os(response, 4401, "登录过期！");
                return;
            }*/
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
