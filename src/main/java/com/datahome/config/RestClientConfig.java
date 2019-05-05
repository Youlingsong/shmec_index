package com.datahome.config;

import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

//@Configuration
@PropertySource("classpath:elasticsearch.properties")
public class RestClientConfig {

    @Value("${hosts}")
    private String host;
    @Value("${ports}")
    private String port;


    @Bean(name = "restClient")
    public RestClient getRestClient() {
        String[] hosts = host.split("\\|");
        String[] ports = port.split("\\|");
        List<HttpHost> hostList = new ArrayList<>();
        for (int i = 0; i < hosts.length; i++) {
            hostList.add(new HttpHost(hosts[i], Integer.parseInt(ports[i]), "http"));
        }

        RestClientBuilder.RequestConfigCallback requestConfigCallback = builder -> {
            builder.setConnectTimeout(30000);
            builder.setConnectionRequestTimeout(30000);
            return builder;
        };

        RestClientBuilder restClientBuilder = RestClient.builder(hostList.toArray(new HttpHost[hostList.size()])).setRequestConfigCallback(requestConfigCallback);

        System.out.println("连接elasticserache成功");
        try {
            String s = saveSchema(restClientBuilder.build());
            System.out.println(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return restClientBuilder.build();
    }








    public static String saveSchema(RestClient restClient) throws Exception {
        String method = "PUT";
        String endpoint = "/test-index";
        Response response = restClient.performRequest(method,endpoint);
        System.out.println(EntityUtils.toString(response.getEntity()));
        return EntityUtils.toString(response.getEntity());
    }

}
