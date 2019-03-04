package com.luoluohao.answer_system.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.Header;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

/**
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {

    public String doPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<>();
            Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String doPost(String url, Map<String, Object> map) {
        String result;
        try {
            MultipartEntityBuilder mBuilder = MultipartEntityBuilder.create();
            for (Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() instanceof String) {
                    mBuilder.addTextBody(entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof MultipartFile) {
                    mBuilder.addBinaryBody(
                            entry.getKey(),
                            ((MultipartFile) entry.getValue()).getBytes(),
                            ContentType.MULTIPART_FORM_DATA,
                            ((MultipartFile) entry.getValue()).getOriginalFilename()
                    );
                }
            }

            HttpEntity reqEntity = mBuilder.build();
//            // 建立一个sslcontext，这里我们信任任何的证书。
//            SSLContext context = getTrustAllSSLContext();
//            // 建立socket工厂
//            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(context);
            // 建立连接器
            CloseableHttpClient client = HttpClients.createDefault();
            // 得到一个post请求的实体
            HttpPost post = new HttpPost(url);
            // 给请求添加参数
            post.setEntity(reqEntity);
            // 执行请求并获得结果
            CloseableHttpResponse reponse = client.execute(post);
            // 获得返回的内容
            HttpEntity entity = reponse.getEntity();
            // 输出
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public String postJson(String url, String json) {
        String result = null;
        try {
            // 建立连接器
            CloseableHttpClient client = HttpClients.createDefault();
            // 得到一个post请求的实体
            HttpPost post = new HttpPost(url);
            StringEntity stringEntity = new StringEntity(json, "utf-8");
            stringEntity.setContentEncoding("utf-8");
            post.setHeader("Content-type", "application/json");
            // 给请求添加参数
            post.setEntity(stringEntity);
            CloseableHttpResponse response = client.execute(post);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String doGet(String url) {

        String result = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, "utf-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    private SSLContext getTrustAllSSLContext() throws Exception {
        SSLContextBuilder context = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                        return true;
                    }
                }
        );
        return context.build();
    }

}