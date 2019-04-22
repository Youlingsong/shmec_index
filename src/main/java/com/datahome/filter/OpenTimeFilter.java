package com.datahome.filter;

import com.datahome.listener.OpenTimeListener;
import com.datahome.util.CommonUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @Author xl
 * @Description: 平台开放时间 过滤
 * @Date: Create in 2018/1/13 16:35
 */
@WebFilter(urlPatterns = {"/mgmt/indexData/*"})
public class OpenTimeFilter implements Filter {

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
        Map<String, Object> map = OpenTimeListener.openTime;

        //启用时间管理
        if ("1".equals(map.get("status")) && (method.startsWith("/save") || method.startsWith("/update"))) {
            Date startTime = (Date)map.get("startTime");
            Date endTime = (Date)map.get("endTime");
            //todo 平台时间判断

            if (!(startTime.before(new Date()) && endTime.after(new Date()))) {
                CommonUtil.os(response, 4200, " 数据未开放！");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
