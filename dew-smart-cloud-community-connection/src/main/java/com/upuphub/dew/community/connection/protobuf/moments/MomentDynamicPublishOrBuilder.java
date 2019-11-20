// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: moments.proto

package com.upuphub.dew.community.connection.protobuf.moments;

public interface MomentDynamicPublishOrBuilder extends
    // @@protoc_insertion_point(interface_extends:MomentDynamicPublish)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Dynamic Content ID
   * </pre>
   *
   * <code>int64 dynamicId = 1;</code>
   */
  long getDynamicId();

  /**
   * <pre>
   * 用户动态发布者ID
   * </pre>
   *
   * <code>int64 publishBy = 2;</code>
   */
  long getPublishBy();

  /**
   * <pre>
   * 动态的发布类型
   * </pre>
   *
   * <code>.MOMENTS_DYNAMIC_PUBLISH_TYPE publishType = 3;</code>
   */
  int getPublishTypeValue();
  /**
   * <pre>
   * 动态的发布类型
   * </pre>
   *
   * <code>.MOMENTS_DYNAMIC_PUBLISH_TYPE publishType = 3;</code>
   */
  com.upuphub.dew.community.connection.protobuf.moments.MOMENTS_DYNAMIC_PUBLISH_TYPE getPublishType();

  /**
   * <pre>
   * 用户发布消息的经度
   * </pre>
   *
   * <code>double longitude = 4;</code>
   */
  double getLongitude();

  /**
   * <pre>
   * 用户发布消息的纬度
   * </pre>
   *
   * <code>double latitude = 5;</code>
   */
  double getLatitude();
}
