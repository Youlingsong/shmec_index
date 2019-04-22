package com.datahome.listener;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class C3P0Listener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HikariDataSource hikariDataSource = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(HikariDataSource.class);
        try {
            hikariDataSource.close();
            //CommonUtil.LOGGER.info("释放hikariDataSource成功");
        } catch (Exception e) {
           // CommonUtil.LOGGER.error("释放hikariDataSource失败： " + e.getMessage());
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {}
}
