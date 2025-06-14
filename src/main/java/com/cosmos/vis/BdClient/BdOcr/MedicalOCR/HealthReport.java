package com.cosmos.vis.BdClient.BdOcr.MedicalOCR;





import com.cosmos.vis.BdUtils.PhotoShiBie.Base64Util;
import com.cosmos.vis.BdUtils.PhotoShiBie.FileUtil;
import com.cosmos.vis.BdUtils.PhotoShiBie.HttpUtil;
import com.cosmos.vis.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;

/**
 * 医疗诊断报告单识别
 */

@Service
public class HealthReport{


    @Autowired
    private AuthService authService;

    public  String healthReport(@RequestParam(required = false,value = "file") MultipartFile file,
                                @RequestParam(required = false,value = "url" ) String imageUrl) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/health_report";

        try {
            String param;
            if (file != null && !file.isEmpty()) {
                // 处理上传的文件
                byte[] imgData = file.getBytes();
                String imgStr = Base64Util.encode(imgData);
                String imgParam = URLEncoder.encode(imgStr, "UTF-8");
                param = "image=" + imgParam;
            } else if (imageUrl != null && !imageUrl.isEmpty()) {
                // 处理图片链接
                param = "url=" + URLEncoder.encode(imageUrl, "UTF-8");
            } else {
                throw new IllegalArgumentException("Either file or URL must be provided");
            }

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = authService.getImageVisAuth();
            System.out.println("health"+accessToken);
            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }





}