package com.datahome.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/9/4 14:37
 */
public class HttpClientUtil {

    public static String getResponseContent(HttpPost httpPost, Integer timeout) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        //连接超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();

        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpPost.setHeader("X-Request-With", "XMLHttpRequest");


        httpPost.setConfig(requestConfig);

        //http请求
        HttpResponse httpResponse = httpClient.execute(httpPost);

        // 读取响应的字节流
        InputStream inputStream = httpResponse.getEntity().getContent();
        StringBuffer buffer = new StringBuffer();
        InputStreamReader inputReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferReader = new BufferedReader(inputReader);
        String str = "";
        while ((str = bufferReader.readLine()) != null) {
            buffer.append(str + "\n");
        }
        return buffer.toString();
    }

    public static String getResponseContent(HttpGet httpGet, Integer timeout) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        //连接超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();

        httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpGet.setHeader("Cache-Control","no-cache");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Pragma","no-cache");
        httpGet.setHeader("Upgrade-Insecure-Requests","1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3381.1 Safari/537.36");
        httpGet.setConfig(requestConfig);

        //http请求
        HttpResponse httpResponse = httpClient.execute(httpGet);

        // 读取响应的字节流
        InputStream inputStream = httpResponse.getEntity().getContent();
        StringBuffer buffer = new StringBuffer();
        InputStreamReader inputReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferReader = new BufferedReader(inputReader);
        String str = "";
        while ((str = bufferReader.readLine()) != null) {
            buffer.append(str + "\n");
        }
        return buffer.toString();
    }

    public static String getResponseContent(HttpPost httpPost, Integer timeout, Integer tryTime) {
        HttpClient httpClient = HttpClients.createDefault();
        //连接超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();

        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
        httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpPost.setHeader("X-Request-With", "XMLHttpRequest");
        httpPost.setConfig(requestConfig);

        //http请求
        HttpResponse httpResponse = null;
        StringBuffer buffer = new StringBuffer();
        try {
            httpResponse = httpClient.execute(httpPost);
            // 读取响应的字节流
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamReader inputReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferReader = new BufferedReader(inputReader);
            String str = "";
            while ((str = bufferReader.readLine()) != null) {
                buffer.append(str + "\n");
            }
        } catch (IOException e) {
            if (tryTime < 5) {
                try {
                    Thread.sleep(1000 * tryTime);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                tryTime++;
                System.out.println(" 地址： " + httpPost.getURI() + "     尝试次数：" + tryTime);
                getResponseContent(httpPost, timeout * tryTime, tryTime);
            }
            e.printStackTrace();
        }

        if (buffer == null || "".equals(buffer.toString())) {
            return "error";
        }

        return buffer.toString();
    }


    public static String getResponseContent(HttpGet httpGet, Integer timeout, Integer tryTime) {
        HttpClient httpClient = HttpClients.createDefault();
        //连接超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD)
                .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout).build();

        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
        httpGet.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpGet.setHeader("X-Request-With", "XMLHttpRequest");
        httpGet.setConfig(requestConfig);


        //http请求
        HttpResponse httpResponse = null;
        StringBuffer buffer = new StringBuffer();
        try {
            httpResponse = httpClient.execute(httpGet);
            // 读取响应的字节流
            InputStream inputStream = httpResponse.getEntity().getContent();
            InputStreamReader inputReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferReader = new BufferedReader(inputReader);
            String str = "";
            while ((str = bufferReader.readLine()) != null) {
                buffer.append(str + "\n");
            }
        } catch (IOException e) {
            if (tryTime < 5) {
                try {
                    System.out.println("链接地址：" + httpGet.getURI() + "     尝试次数:" + tryTime);
                    Thread.sleep(1000 * tryTime);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                tryTime++;
                getResponseContent(httpGet, timeout * tryTime, tryTime);
            }
            e.printStackTrace();
        }
        if (buffer == null || "".equals(buffer.toString())) {
            return "error";
        }
        return buffer.toString();
    }


    public static String getCookie(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        String result = null;

        try {
            httpClient = HttpClients.createDefault();
            HttpClientContext context = HttpClientContext.create();

            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0");
            httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            httpPost.setHeader("X-Request-With", "XMLHttpRequest");

            CloseableHttpResponse response = httpClient.execute(httpPost, context);
            try {
                System.out.println(">>>>>>headers:");
                Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
                System.out.println(">>>>>>cookies:");
                context.getCookieStore().getCookies().forEach(System.out::println);
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
