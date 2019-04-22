package com.datahome.listener;

import org.elasticsearch.client.RestClient;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class RestClientListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {}

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            RestClient restClient = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(RestClient.class);
            if (restClient != null) restClient.close();
            //CommonUtil.LOGGER.debug("成功关闭RestClient");
        } catch (IOException e) {
           // CommonUtil.LOGGER.error(e.getMessage());
        }
    }
}
