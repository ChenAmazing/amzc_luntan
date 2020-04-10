package com.mty.cisp.service;

import com.alibaba.fastjson.JSONObject;
import com.mty.cisp.utils.FileUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class QiniuService {
    private static String ACCESS_KEY = "bWrGI_GoEE6cQ7xV0aMhcUjLkD3o0d0_wYvJ_AXI";
    private static String SECRET_KEY = "PS1gjVDwVxVmGyME1Lhgxhy7_SdBF9-MzBYJdCb5";
    //要上传的空间
    private static String bucketname = "amzc";
    //设置服务器域名
    private static String QINIU_IMAGE_DOMAIN ="http://q8gyj1a32.bkt.clouddn.com/";

    public static String saveImage(MultipartFile file){
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }
            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            //调用put方法上传
            UploadManager uploadManager = getUploadManager();
            Response res = uploadManager.put(file.getBytes(), fileName, getToken());
            //打印返回的信息
            if (res.isOK() && res.isJson()) {
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                System.out.println("===七牛异常:" + res.bodyString());
                return null;
            }
        } catch (Exception e) {
            // 请求失败时打印的异常的信息
            return null;
        }
    }

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getToken() {
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(bucketname);
    }

    //创建上传对象
    private static UploadManager getUploadManager() {
        Configuration cfg = new Configuration(Zone.zone0());
        return new UploadManager(cfg);
    }
}
