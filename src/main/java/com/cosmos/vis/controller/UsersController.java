package com.cosmos.vis.controller;


import com.cosmos.vis.BdClient.BdOcr.BdOcrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CosmosBackpacker
 * @since 2024-10-20
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private BdOcrClient client;

    @GetMapping()
    public String getUser() {

        //return client.basicGeneralOCR();
        return "1";
    }

    @PostMapping("/test")
    public String test(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 获取文件的二进制字节
                byte[] bytes = file.getBytes();

                System.out.println(bytes.toString());
                //假设保存成功
                return client.basicGeneralOCR(file);
            } catch (Exception e) {
                return "文件上传失败";
            }
        } else {
            return "文件为空，请重新上传";
        }

    }
}
