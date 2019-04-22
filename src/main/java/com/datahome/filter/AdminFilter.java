package com.datahome.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author xl
 * @Description: 后台主页 跳转
 * @Date: Create in 2018/1/13 16:35
 */
@WebFilter(urlPatterns = {"/admin"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //项目路径
        String path = request.getServletPath();
        //方法名
        String method = path.substring(path.lastIndexOf("/"), path.length());

        if ("/admin".equals(method)) {
            response.sendRedirect(request.getContextPath() + "/admin/index.html");
            return;
        } else {
            response.sendRedirect(request.getContextPath() + "/exception/404.html");
            return;
        }
    }

    @Override
    public void destroy() {

    }
}
