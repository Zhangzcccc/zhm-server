package com.ic.agile.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ZhangZc
 * @date: 2023/4/10 14:11
 * @description: http请求工具包
 */
@Slf4j
public class HttpUtils {
    /**
     * body有内容的post请求方法 controller可以用bean直接接收参数
     *
     * @param url     目标地址
     * @param headers 封装的参数
     * @param bodyStr 封装的参数
     * @return 将响应结果转换成string返回
     */
    public static String postRequest(String url, Map<String, Object> headers, String bodyStr) {
        HttpResponse response;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(url);
            for (Map.Entry<String, Object> e : headers.entrySet()) {
                request.addHeader(e.getKey(), String.valueOf(e.getValue()));
            }
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000).setConnectionRequestTimeout(15000).setSocketTimeout(15000).build();
            request.setConfig(requestConfig);
            request.setEntity(new StringEntity(bodyStr, "utf-8"));
            try {
                response = httpClient.execute(request);
                return EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 传输文件对象  image imagedata
     *
     * @param url
     * @param param
     * @param fileByte
     * @param binaryName
     * @return
     */
    public static String doFormPost(String url, Map<String, String> param, byte[] fileByte, String binaryName) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        HttpPost httppost = new HttpPost(url);
        try {
            // HttpMultipartMode.RFC6532参数的设定是为避免文件名为中文时乱码
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
            // 设置请求的编码格式
            builder.setCharset(Consts.UTF_8);
            builder.setContentType(ContentType.MULTIPART_FORM_DATA);
            // 添加文件
            // builder.addBinaryBody("file", file);
            // 或者使用字节流也行，根据具体需要使用
            builder.addBinaryBody(binaryName, fileByte, ContentType.APPLICATION_OCTET_STREAM, "");
            // 或者builder.addPart("file",new FileBody(file));
            // 添加参数
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addTextBody(key, param.get(key), ContentType.create("text/plain", StandardCharsets.UTF_8));
                }
            }
            HttpEntity reqEntity = builder.build();
            httppost.setEntity(reqEntity);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000).setConnectionRequestTimeout(15000).setSocketTimeout(15000).build();
            httppost.setConfig(requestConfig);
            response = httpClient.execute(httppost);
            if (response == null) {
                throw new RuntimeException("请求：" + url + " 的返回值为null");
            }
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * get请求
     *
     * @param url 目标请求地址
     * @return 将响应结果转换成string返回
     */
    public static String getRequest(String url, Map<String, Object> queryMap) {
        String result = null;
        try {
            // 根据地址获取请求
            HttpGet request = new HttpGet(buildUrl(url, null, queryMap));
            request.setHeader("content-type", "application/json");
            // 获取当前客户端对
            CloseableHttpClient httpclient = HttpClients.createDefault();
            // 通过请求对象获取响应对象
            HttpResponse response = httpclient.execute(request);
            // 判断请求结果状态码
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            log.error("请求[{}]发生错误：", url, e);
        }
        return result;
    }

    /**
     * 查询参数拼接生成
     *
     * @param host
     * @param path
     * @param querys
     * @return
     */
    public static String buildUrl(String host, String path, Map<String, Object> querys) {
        StringBuilder sbUrl = new StringBuilder();
        try {
            sbUrl.append(host);
            if (!StringUtils.isBlank(path)) {
                sbUrl.append(path);
            }
            if (null != querys) {
                StringBuilder sbQuery = new StringBuilder();
                for (Map.Entry<String, Object> query : querys.entrySet()) {
                    if (0 < sbQuery.length()) {
                        sbQuery.append("&");
                    }
                    if (StringUtils.isBlank(query.getKey()) && ObjectUtils.isNotEmpty(query.getValue())) {
                        sbQuery.append(query.getValue());
                    }
                    if (StringUtils.isNotBlank(query.getKey()) && ObjectUtils.isNotEmpty(query.getValue())) {
                        sbQuery.append(query.getKey());
                        if (query.getValue() != null) {
                            sbQuery.append("=");
                            sbQuery.append(URLEncoder.encode(query.getValue().toString(), "utf-8"));
                        }
                    }
                }
                if (0 < sbQuery.length()) {
                    sbUrl.append("?").append(sbQuery);
                }
            }
        } catch (Exception e) {
            log.error("查询参数封装出错！", e);
            return null;
        }
        return sbUrl.toString();
    }

    /* -------------------------GET----------------------------- */

    /**
     * GET
     *
     * @param host    服务器地址
     * @param path    接口地址
     * @param headers 请求头
     * @param querys  查询参数
     * @return 请求结果 response
     */
    public static HttpResponse doGetResp(String host, String path, Map<String, Object> headers, Map<String, Object> querys) {
        // 1.https处理
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 2.完整url构建
            String buildUrl = buildUrl(host, path, querys);
            log.debug("GET请求URL=【{}】", buildUrl);
            HttpGet request = new HttpGet(buildUrl);
            // 3.设置请求超时配置
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(15000).setConnectionRequestTimeout(15000).setSocketTimeout(15000).build();
            request.setConfig(requestConfig);
            // 4.请求头设置
            if (null != headers) {
                for (Map.Entry<String, Object> e : headers.entrySet()) {
                    String value = e.getValue() == null ? null : e.getValue().toString();
                    request.setHeader(e.getKey(), value);
                }
            }
            // 5.结果返回
            return httpClient.execute(request);
        } catch (Exception e) {
            log.error("解析调用返回接口数据失败" + e.getMessage());
            return null;
        }
    }

    /**
     * GET
     *
     * @param host    服务器地址
     * @param path    接口地址
     * @param headers 请求头
     * @param querys  查询参数
     * @return 请求结果 json字符串
     */
    public static String doGet(String host, String path, Map<String, Object> headers, Map<String, Object> querys) {
        try {
            HttpResponse response = doGetResp(host, path, headers, querys);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            log.error("解析调用返回接口数据失败" + e.getMessage());
            return null;
        }
    }

    /**
     * GET
     *
     * @param host    服务器地址
     * @param path    接口地址
     * @param headers 请求头
     * @return
     */
    public static String doGet(String host, String path, HashMap<String, Object> headers) {
        return doGet(host, path, headers, null);
    }

    /**
     * 下载图片
     *
     * @param imageUrl 图片地址
     * @return byte[]
     */
    public static byte[] downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("下载图片失败" + e.getMessage());
            return null;
        }
    }
}