package com.datahome;

import com.datahome.dao.impl.BaseDaoFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//开启异步调用方法
@EnableAsync
@SpringBootApplication
@ServletComponentScan({"com.datahome.filter", "com.datahome.listener"})
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseDaoFactoryBean.class)
//开启定时任务
@EnableScheduling
public class ShmecAge06GdnindexApplication extends SpringBootServletInitializer {

    static {
        System.setProperty("log4j2.isThreadContextMapInheritable", Boolean.toString(true));
    }

    public static void main(String[] args) {
        SpringApplication.run(ShmecAge06GdnindexApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ShmecAge06GdnindexApplication.class);
    }

}
