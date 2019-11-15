// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: moments.proto

package com.upuphub.dew.community.connection.protobuf.moments;

public interface MomentDynamicContentOrBuilder extends
    // @@protoc_insertion_point(interface_extends:MomentDynamicContent)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 uin = 1;</code>
   */
  long getUin();

  /**
   * <code>int64 createTime = 2;</code>
   */
  long getCreateTime();

  /**
   * <code>double longitude = 3;</code>
   */
  double getLongitude();

  /**
   * <code>double latitude = 4;</code>
   */
  double getLatitude();

  /**
   * <code>string dynamic = 5;</code>
   */
  java.lang.String getDynamic();
  /**
   * <code>string dynamic = 5;</code>
   */
  com.google.protobuf.ByteString
      getDynamicBytes();

  /**
   * <code>repeated string pictures = 6;</code>
   */
  java.util.List<java.lang.String>
      getPicturesList();
  /**
   * <code>repeated string pictures = 6;</code>
   */
  int getPicturesCount();
  /**
   * <code>repeated string pictures = 6;</code>
   */
  java.lang.String getPictures(int index);
  /**
   * <code>repeated string pictures = 6;</code>
   */
  com.google.protobuf.ByteString
      getPicturesBytes(int index);

  /**
   * <code>string topic = 7;</code>
   */
  java.lang.String getTopic();
  /**
   * <code>string topic = 7;</code>
   */
  com.google.protobuf.ByteString
      getTopicBytes();

  /**
   * <code>int32 classify = 8;</code>
   */
  int getClassify();
}