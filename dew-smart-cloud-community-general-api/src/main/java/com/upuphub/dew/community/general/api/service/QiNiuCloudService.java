package com.upuphub.dew.community.general.api.service;

import java.util.List;
import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/5 20:55
 */
public interface QiNiuCloudService {
    /**
     * 批量获取图片的七牛云Token
     * @param bucketName 七牛云上传的桶名
     * @param fileKeys 七牛云保存使用的文件名
     * @return 生成的七牛云Token
     */
    public Map<String,String> buildQiNiuImageToken(String bucketName, List<String> fileKeys);
}
