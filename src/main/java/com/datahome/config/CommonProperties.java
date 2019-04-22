package com.datahome.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xl
 * @Description: 公共属性
 * @Date: Create in 2018/10/19 11:22
 */

@Getter
@Setter
@Configuration
public class CommonProperties {

    @Value("${upload.baseFilePath}")
    String baseFilePath;

}
