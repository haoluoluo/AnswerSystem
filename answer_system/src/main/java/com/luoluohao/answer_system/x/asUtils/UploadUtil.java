package com.luoluohao.answer_system.x.asUtils;

import com.luoluohao.answer_system.util.FileUtil;
import com.luoluohao.answer_system.util.StringUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件上传处理工具
 *
 * @Filename: com.abcdpower.x.iwllutil.UploadUtil.java
 * @Type: UploadUtil
 * @Author: macbook[weide<weide001 @ gmail.com>]
 * @Created: 2018/9/24 下午2:18
 * @Version: iwill.zt-service V1.0.0
 */
public class UploadUtil {

    /**
     * @Description 保存上传文件
     * @Param [file, saveas]
     * @Return java.lang.Boolean
     */
    public static Boolean Save(MultipartFile file, String saveas) throws IOException {
        if (null == file) return false;

        String contenttype = file.getContentType();         //图片文件类型
        String originalname = file.getOriginalFilename();   //原始图片名字

        String filepath = FileUtil.BasePath(saveas);
        String filename = FileUtil.FileName(saveas);
        filepath = StringUtil.IsEmpty(filepath) ? "./" : filepath;
        filename = StringUtil.IsEmpty(filename) ? originalname : filename;

        File fileTarget = new File(filepath);
        if (!fileTarget.exists()) fileTarget.mkdirs();

        String savename = filepath + "/" + filename;
        FileOutputStream out = new FileOutputStream(savename);

        out.write(file.getBytes());
        out.flush();
        out.close();

        return true;
    }
}
