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
    private String geohash;

    /**
     * 点赞数量
     */
    private Integer likeNumber;

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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
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

    public Double getlatitude() {
        return
                latitude;
    }

    public void setlatitude(Double
                     latitude) {
        this.
                latitude =
                latitude;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MomentsPublishPO other = (MomentsPublishPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getGeohash() == null ? other.getGeohash() == null : this.getGeohash().equals(other.getGeohash()))
                && (this.getLikeNumber() == null ? other.getLikeNumber() == null : this.getLikeNumber().equals(other.getLikeNumber()))
                && (this.getDynamicId() == null ? other.getDynamicId() == null : this.getDynamicId().equals(other.getDynamicId()))
                && (this.getPublishBy() == null ? other.getPublishBy() == null : this.getPublishBy().equals(other.getPublishBy()))
                && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()))
                && (this.getPublishType() == null ? other.getPublishType() == null : this.getPublishType().equals(other.getPublishType()))
                && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
                && (this.getlatitude() == null ? other.getlatitude() == null :this.getlatitude().equals(other.getlatitude()))
            &&
        (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getPublishStatus() == null ? other.getPublishStatus() == null : this.getPublishStatus().equals(other.getPublishStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGeohash() == null) ? 0 : getGeohash().hashCode());
        result = prime * result + ((getLikeNumber() == null) ? 0 : getLikeNumber().hashCode());
        result = prime * result + ((getDynamicId() == null) ? 0 : getDynamicId().hashCode());
        result = prime * result + ((getPublishBy() == null) ? 0 : getPublishBy().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        result = prime * result + ((getPublishType() == null) ? 0 : getPublishType().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getlatitude() == null) ?0 :getlatitude().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPublishStatus() == null) ? 0 : getPublishStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", geohash=").append(geohash);
        sb.append(", likeNumber=").append(likeNumber);
        sb.append(", dynamicId=").append(dynamicId);
        sb.append(", publishBy=").append(publishBy);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", publishType=").append(publishType);
        sb.append(", longitude=").append(longitude);
        sb.append(",latitude = ").append(latitude);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", publishStatus=").append(publishStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}