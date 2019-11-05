package com.upuphub.dew.community.general.api.service.impl;

import cc.itsc.rbc.api.service.QiNiuCloudService;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */

@Service
public class QiNiuCloudServiceImpl implements QiNiuCloudService {


    private Auth auth;

    private QiNiuCloudServiceImpl(@Value("${dew.image.accessKey}")String accessKey,
                                  @Value("${dew.image.secretKey}")String secretKey){
        auth = Auth.create(accessKey,secretKey);
    }

    @Override
    public Map<String,String> buildQiNiuImageToken(String bucketName, List<String> fileKeys){
        if(fileKeys == null || fileKeys.size() == 0){
            return Collections.emptyMap();
        }
        Map<String,String> qiQiuTokenMap = new HashMap<>(fileKeys.size());
        for (String fileKey : fileKeys) {
            qiQiuTokenMap.putIfAbsent(fileKey,buildQiNiuImageToken(bucketName,fileKey));
        }
        return qiQiuTokenMap;
    }

    /**
     * 获取图片的七牛云Token
     *
     * @param bucketName 七牛云上传的桶名
     * @param fileKey 七牛云保存使用的文件名
     * @return 生成的七牛云Token
     */
    private String buildQiNiuImageToken(String bucketName, String fileKey){
        // 设置上传策略
        StringMap putPolicy = new StringMap();
        int[] wideList = {160,360,640};
        StringBuilder persistentOps = new StringBuilder();
        for (int width : wideList) {
            String fops = String.format("imageView2/1/w/%d/h/%d", width, width);
            String saveKey = String.format("%s:%s_%d", bucketName, fileKey, width);
            //fops+'|saveas/'+saveas_key + ';'
            fops = String.format("%s|saveas/%s;", fops, UrlSafeBase64.encodeToString(saveKey));
            persistentOps.append(fops);
        }
        putPolicy.put("persistentOps", persistentOps);
        long expireSeconds = 3600;
        return auth.uploadToken(bucketName, null, expireSeconds, putPolicy);
    }


}
