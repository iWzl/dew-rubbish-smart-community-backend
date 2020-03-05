package com.upuphub.dew.community.moments.bean.po;

import java.io.Serializable;

/**
 * moments_publish
 *
 * @author LeoWang
 */
public class MomentsPublishPO implements Serializable {
    /**
     * 发布ID自增主键
     */
    private Long id;

    /**
     * GeoHash
     */
    private String geoHash;

    /**
     * 分类属性
     */
    private Integer classify;

    /**
     * 参与的话题
     */
    private String topic;

    /**
     * 动态ID
     */
    private Long dynamicId;

    /**
     * 发布者UIN
     */
    private Long publishBy;

    /**
     * 发布时间
     */
    private Long publishTime;

    /**
     * 发布类型
     */
    private String publishType;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double
            latitude;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 发布状态
     */
    private Integer publishStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    public Integer getClassify() {
        return classify;
    }

    public void setClassify(Integer classify) {
        this.classify = classify;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Long getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(Long publishBy) {
        this.publishBy = publishBy;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }
}