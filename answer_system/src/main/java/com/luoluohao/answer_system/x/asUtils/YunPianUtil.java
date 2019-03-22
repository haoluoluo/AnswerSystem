package com.luoluohao.answer_system.x.asUtils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 【云片网】短信收发工具
 *
 * @Filename: com.abcdpower.x.iwllutil.YunPianUtil.java
 * @Type: YunPianUtil
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/22 上午9:28
 * @Version: iwill.zt-service V1.0.0
 */
public class YunPianUtil {
    /**
     * @Description 短信发送（文字）
     *
     * @Param [url, params, encoding]
     * @Return java.lang.String
     */
    public static String Send(String url, Map<String,String> params, String encoding) {

        CloseableHttpClient client = HttpClients.createDefault();

        String responseText = null;
        CloseableHttpResponse response = null;

        try {
            HttpPost method = new HttpPost(url);

            if (params != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String,String> param: params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(),param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList,encoding));
            }

            response = client.execute(method);
            HttpEntity entity = response.getEntity();

            if (entity != null) responseText = EntityUtils.toString(entity, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(null!=response) response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return responseText;
    }
}
