package com.eurekatest.commoninfo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * description:
 * restTemplate 请求工具类
 *
 * @author hongjw
 * @create 2020-09-10 17:26
 */
public class RestTemplateUtils<T> {

    public static final RestTemplate REST_TEMPLATE;
    /**
     * json
     */
    private static final HttpHeaders HEADERSJ;
    /**
     * form-data
     */
    private static final HttpHeaders HEADERSF;

    /**
     *
     */

    static {
        CloseableHttpClient closeableHttpClient = null;
        try {
            closeableHttpClient = HttpClientUtils.acceptsUntrustedCertsHttpClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpComponentsClientHttpRequestFactory httpRequestFactory =
                new HttpComponentsClientHttpRequestFactory(closeableHttpClient);
        httpRequestFactory.setConnectionRequestTimeout(100000);
        httpRequestFactory.setConnectTimeout(100000);
        httpRequestFactory.setReadTimeout(100000);
        REST_TEMPLATE = new RestTemplate(httpRequestFactory);

        HEADERSJ = new HttpHeaders();
        HEADERSJ.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HEADERSF = new HttpHeaders();
        HEADERSF.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
    }

    public static String get(String url) {
        return REST_TEMPLATE.getForObject(url, String.class);
    }

    public static String request(String url, HttpMethod method) {
        return REST_TEMPLATE.exchange(url,
                method,
                new HttpEntity<String>(HEADERSJ),
                String.class).getBody();
    }

    /**
     * header为：json
     *
     * @param url
     * @param requestParams
     * @return
     * @throws Exception
     */
    public static String postForJSON(String url, Object requestParams) throws Exception {
        HttpEntity<Object> formEntity =
                new HttpEntity<>(JSONObject.toJSONString(requestParams), HEADERSJ);
        String responseJson;
        try {
            responseJson = REST_TEMPLATE.postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            throw e;
        }
        return responseJson;
    }

    /**
     * header为：json
     *
     * @param url
     * @param requestParams
     * @return
     * @throws Exception
     */
    public static String getForJSON(String url, Map requestParams) {
        String responseJson;
        try {
            responseJson = REST_TEMPLATE.getForObject(url, String.class, requestParams);
        } catch (Exception e) {
            throw e;
        }
        return responseJson;
    }


    /**
     * post表单请求
     *
     * @param url
     * @param map
     * @return
     */
    public static String postFormData(String url, Map<String, String> map) {
        MultiValueMap<String, String> reqMap = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            reqMap.add(entry.getKey(), entry.getValue());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String res;
        try {
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(reqMap, headers);
            ResponseEntity<String> response = REST_TEMPLATE.postForEntity(url, request, String.class);
            res = response.getBody();
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    /**
     * header为：form-data
     *
     * @param url
     * @param requestParams
     * @return
     * @throws Exception
     */
    public static JSONObject postForForm(String url, Map<String, ? extends Object> requestParams) throws Exception {
        LinkedMultiValueMap body = new LinkedMultiValueMap();
        for (String key : requestParams.keySet()) {
            body.add(key, requestParams.get(key));
        }
        HttpEntity<String> entity = new HttpEntity(body, HEADERSF);
        JSONObject responseJson;
        try {
            responseJson = REST_TEMPLATE.postForObject(url, entity, JSONObject.class);
        } catch (Exception e) {
            throw e;
        }
        return responseJson;
    }

    /**
     * 发送文件请求
     *
     * @param url
     * @param token
     * @return
     */
    public static String file(String url, MultipartFile file, String token) {
        // 生成临时文件
        String tempFilePath = System.getProperty("java.io.tmpdir") + file.getOriginalFilename();
        File tmpFile = new File(tempFilePath);
        // 结果，抛异常就是 null
        String result = null;
        try {
            // 保存为文件
            file.transferTo(tmpFile);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
            if (token != null) {
                headers.add("Authorization", token);
            }
            MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
            // 把临时文件变成 FileSystemResource
            FileSystemResource resource = new FileSystemResource(tempFilePath);
            param.add("file", resource);
            HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(param, headers);
            result = REST_TEMPLATE.postForObject(url, formEntity, String.class);
            //删除临时文件文件
            tmpFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 发送文件请求
     *
     * @param url
     * @param token
     * @return
     */
    public static String file(String url, File file, String token) {


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.setContentType(MediaType.parseMediaType("multipart/form-data;charset=UTF-8"));
        if (token != null) {
            headers.add("Authorization", token);
        }
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        // 把临时文件变成 FileSystemResource
        FileSystemResource resource = new FileSystemResource(file);
        param.add("file", resource);
        HttpEntity<MultiValueMap<String, Object>> formEntity = new HttpEntity<>(param, headers);
        return REST_TEMPLATE.postForObject(url, formEntity, String.class);


    }


}
