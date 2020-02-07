package com.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 高永佳
 */
public class QiniuUpload {

    private static Logger logger = Logger.getLogger(QiniuUpload.class);

    private static String accessKey = "zowGi4_PBzXng_BawzMe3jy8I30o2EK7dGWAJOXj";
    private static String secretKey = "oT4sHwArTAy99uuAGPwrPIcTIBiNsX5JOX2N_-2E";
    private static String bucket = "xzy-dsp-images";
    private static String bucketHost = "q4ybkmgmn.bkt.clouddn.com";
    //构造一个带指定Zone对象的配置类
    private static Configuration cfg = new Configuration(Zone.zone2());
    //...其他参数参考类注释
    private static UploadManager uploadManager = new UploadManager(cfg);

    public static String upload(MultipartFile file) {
        Response response;
        String key;
        Auth auth;
        String upToken;
        try {
            key = file.getOriginalFilename();
            auth = Auth.create(accessKey, secretKey);
            upToken = auth.uploadToken(bucket);
            try {
                response = uploadManager.put(file.getBytes(), key, upToken);
            } catch (QiniuException e) {
                e.printStackTrace();
                logger.error("upload上传失败");
                return "error";
            }
        } catch (Exception e) {
            logger.error("上传环境配置失败");
            return "error";
        }
        return getUrlFromServer(response);
    }

    /**
     * 解析返回上传成功后的response
     * @param response
     * @return
     */
    private static String getUrlFromServer(Response response) {
        //解析上传成功的结果
        DefaultPutRet putRet = null;
        try {
            putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException e) {
            logger.error("解析response失败");
            return "error";
        }
        String url = "http://" + bucketHost + "/" + putRet.key;
        return url;
    }

}
