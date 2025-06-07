package com.cosmos.vis.BdClient.BdImageIdentify.ImageUnderstand;


import com.cosmos.vis.config.BDConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
public class UnderstandResponse {


    @Autowired
    private BDConfig bdConfig;

    public final String API_KEY = bdConfig.getUnderstandApiKey();
    public final String SECRET_KEY = bdConfig.getUnderstandSecretKey();

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public String getResponse(String taskId) throws IOException {
        System.out.println("这里的TASKID" + taskId);
        MediaType mediaType = MediaType.parse("application/json");
        // 创建包含taskId的JSON格式的RequestBody
        RequestBody body = RequestBody.create(mediaType, "{\"task_id\":\"" + taskId + "\"}");
        log.info("tag{}", taskId);
        System.out.println("这里的TASKID2" + taskId);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/image-classify/v1/image-understanding/get-result?access_token=" + getAccessToken())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        //System.out.println(response.body().string());
        String result = response.body().string();
        return result;
    }


    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     * @throws IOException IO异常
     */
    private String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }

}