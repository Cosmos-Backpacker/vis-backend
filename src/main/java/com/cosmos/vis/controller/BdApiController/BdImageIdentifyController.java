package com.cosmos.vis.controller.BdApiController;


import com.cosmos.vis.BdClient.BdImageIdentify.AdvancedGeneral;
import com.cosmos.vis.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 *  图像理解
 */
@RestController
@RequestMapping("/BdImageIdentify")
public class BdImageIdentifyController {

    @Autowired
    private AdvancedGeneral advancedGeneral;
    /**
     *  通用理解
     * @param file  图片
     * @return  结果
     */
    @PostMapping("/generalIdentify")
    public Result tyIdentify(@RequestPart(value = "file" ,required = false)MultipartFile file,
                             @RequestParam(required = false) String  imageUrl)
    {

        if (file != null && !file.isEmpty()) {
            // 处理上传的文件
            String result=advancedGeneral.advancedGeneral(file,null);
            return Result.success("success",result);
        } else if (imageUrl != null && !imageUrl.isEmpty()) {
            // 处理图片链接
            try {
                // 从链接下载图片并处理
                String res = advancedGeneral.advancedGeneral(null, imageUrl);
                return Result.success("success", res);
            } catch (Exception e) {
                return Result.error("error ，Failed to download image from URL");
            }
        } else {
            return Result.error("error ，Either file or URL must be provided");
        }

    }


}
