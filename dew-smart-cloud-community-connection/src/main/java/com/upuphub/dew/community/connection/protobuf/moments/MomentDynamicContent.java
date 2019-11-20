// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: moments.proto

package com.upuphub.dew.community.connection.protobuf.moments;

/**
 * Protobuf type {@code MomentDynamicContent}
 */
public  final class MomentDynamicContent extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:MomentDynamicContent)
    MomentDynamicContentOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MomentDynamicContent.newBuilder() to construct.
  private MomentDynamicContent(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MomentDynamicContent() {
    dynamic_ = "";
    pictures_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    topic_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MomentDynamicContent();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MomentDynamicContent(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            uin_ = input.readInt64();
            break;
          }
          case 17: {

            longitude_ = input.readDouble();
            break;
          }
          case 25: {

            latitude_ = input.readDouble();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            dynamic_ = s;
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              pictures_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            pictures_.add(s);
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            topic_ = s;
            break;
          }
          case 56: {

            classify_ = input.readInt32();
            break;
          }
          case 64: {

            updateTime_ = input.readInt64();
            break;
          }
          case 72: {

            dynamicContentId_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        pictures_ = pictures_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_MomentDynamicContent_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_MomentDynamicContent_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent.class, com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent.Builder.class);
  }

  public static final int UIN_FIELD_NUMBER = 1;
  private long uin_;
  /**
   * <pre>
   * 消息所属的UIN
   * </pre>
   *
   * <code>int64 uin = 1;</code>
   */
  public long getUin() {
    return uin_;
  }

  public static final int LONGITUDE_FIELD_NUMBER = 2;
  private double longitude_;
  /**
   * <pre>
   * 用户发布消息的经度
   * </pre>
   *
   * <code>double longitude = 2;</code>
   */
  public double getLongitude() {
    return longitude_;
  }

  public static final int LATITUDE_FIELD_NUMBER = 3;
  private double latitude_;
  /**
   * <pre>
   * 用户发布消息的维度
   * </pre>
   *
   * <code>double latitude = 3;</code>
   */
  public double getLatitude() {
    return latitude_;
  }

  public static final int DYNAMIC_FIELD_NUMBER = 4;
  private volatile java.lang.Object dynamic_;
  /**
   * <pre>
   * 用户发布消息的消息正文
   * </pre>
   *
   * <code>string dynamic = 4;</code>
   */
  public java.lang.String getDynamic() {
    java.lang.Object ref = dynamic_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      dynamic_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 用户发布消息的消息正文
   * </pre>
   *
   * <code>string dynamic = 4;</code>
   */
  public com.google.protobuf.ByteString
      getDynamicBytes() {
    java.lang.Object ref = dynamic_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      dynamic_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PICTURES_FIELD_NUMBER = 5;
  private com.google.protobuf.LazyStringList pictures_;
  /**
   * <pre>
   * 用户发布消息携带的配图
   * </pre>
   *
   * <code>repeated string pictures = 5;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getPicturesList() {
    return pictures_;
  }
  /**
   * <pre>
   * 用户发布消息携带的配图
   * </pre>
   *
   * <code>repeated string pictures = 5;</code>
   */
  public int getPicturesCount() {
    return pictures_.size();
  }
  /**
   * <pre>
   * 用户发布消息携带的配图
   * </pre>
   *
   * <code>repeated string pictures = 5;</code>
   */
  public java.lang.String getPictures(int index) {
    return pictures_.get(index);
  }
  /**
   * <pre>
   * 用户发布消息携带的配图
   * </pre>
   *
   * <code>repeated string pictures = 5;</code>
   */
  public com.google.protobuf.ByteString
      getPicturesBytes(int index) {
    return pictures_.getByteString(index);
  }

  public static final int TOPIC_FIELD_NUMBER = 6;
  private volatile java.lang.Object topic_;
  /**
   * <pre>
   * 用户动态信息参与的话题
   * </pre>
   *
   * <code>string topic = 6;</code>
   */
  public java.lang.String getTopic() {
    java.lang.Object ref = topic_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      topic_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 用户动态信息参与的话题
   * </pre>
   *
   * <code>string topic = 6;</code>
   */
  public com.google.protobuf.ByteString
      getTopicBytes() {
    java.lang.Object ref = topic_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      topic_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CLASSIFY_FIELD_NUMBER = 7;
  private int classify_;
  /**
   * <pre>
   * 用户消息动态的种类
   * </pre>
   *
   * <code>int32 classify = 7;</code>
   */
  public int getClassify() {
    return classify_;
  }

  public static final int UPDATETIME_FIELD_NUMBER = 8;
  private long updateTime_;
  /**
   * <pre>
   * 用户消息动态的更新时间
   * </pre>
   *
   * <code>int64 updateTime = 8;</code>
   */
  public long getUpdateTime() {
    return updateTime_;
  }

  public static final int DYNAMICCONTENTID_FIELD_NUMBER = 9;
  private long dynamicContentId_;
  /**
   * <pre>
   * 用户动态消息的ID
   * </pre>
   *
   * <code>int64 dynamicContentId = 9;</code>
   */
  public long getDynamicContentId() {
    return dynamicContentId_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (uin_ != 0L) {
      output.writeInt64(1, uin_);
    }
    if (longitude_ != 0D) {
      output.writeDouble(2, longitude_);
    }
    if (latitude_ != 0D) {
      output.writeDouble(3, latitude_);
    }
    if (!getDynamicBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, dynamic_);
    }
    for (int i = 0; i < pictures_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, pictures_.getRaw(i));
    }
    if (!getTopicBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, topic_);
    }
    if (classify_ != 0) {
      output.writeInt32(7, classify_);
    }
    if (updateTime_ != 0L) {
      output.writeInt64(8, updateTime_);
    }
    if (dynamicContentId_ != 0L) {
      output.writeInt64(9, dynamicContentId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (uin_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, uin_);
    }
    if (longitude_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, longitude_);
    }
    if (latitude_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, latitude_);
    }
    if (!getDynamicBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, dynamic_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < pictures_.size(); i++) {
        dataSize += computeStringSizeNoTag(pictures_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getPicturesList().size();
    }
    if (!getTopicBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, topic_);
    }
    if (classify_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, classify_);
    }
    if (updateTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(8, updateTime_);
    }
    if (dynamicContentId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(9, dynamicContentId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent)) {
      return super.equals(obj);
    }
    com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent other = (com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent) obj;

    if (getUin()
        != other.getUin()) return false;
    if (java.lang.Double.doubleToLongBits(getLongitude())
        != java.lang.Double.doubleToLongBits(
            other.getLongitude())) return false;
    if (java.lang.Double.doubleToLongBits(getLatitude())
        != java.lang.Double.doubleToLongBits(
            other.getLatitude())) return false;
    if (!getDynamic()
        .equals(other.getDynamic())) return false;
    if (!getPicturesList()
        .equals(other.getPicturesList())) return false;
    if (!getTopic()
        .equals(other.getTopic())) return false;
    if (getClassify()
        != other.getClassify()) return false;
    if (getUpdateTime()
        != other.getUpdateTime()) return false;
    if (getDynamicContentId()
        != other.getDynamicContentId()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + UIN_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUin());
    hash = (37 * hash) + LONGITUDE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getLongitude()));
    hash = (37 * hash) + LATITUDE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getLatitude()));
    hash = (37 * hash) + DYNAMIC_FIELD_NUMBER;
    hash = (53 * hash) + getDynamic().hashCode();
    if (getPicturesCount() > 0) {
      hash = (37 * hash) + PICTURES_FIELD_NUMBER;
      hash = (53 * hash) + getPicturesList().hashCode();
    }
    hash = (37 * hash) + TOPIC_FIELD_NUMBER;
    hash = (53 * hash) + getTopic().hashCode();
    hash = (37 * hash) + CLASSIFY_FIELD_NUMBER;
    hash = (53 * hash) + getClassify();
    hash = (37 * hash) + UPDATETIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUpdateTime());
    hash = (37 * hash) + DYNAMICCONTENTID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDynamicContentId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code MomentDynamicContent}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:MomentDynamicContent)
      com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContentOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_MomentDynamicContent_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_MomentDynamicContent_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent.class, com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent.Builder.class);
    }

    // Construct using com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      uin_ = 0L;

      longitude_ = 0D;

      latitude_ = 0D;

      dynamic_ = "";

      pictures_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      topic_ = "";

      classify_ = 0;

      updateTime_ = 0L;

      dynamicContentId_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_MomentDynamicContent_descriptor;
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent getDefaultInstanceForType() {
      return com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent.getDefaultInstance();
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent build() {
      com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent buildPartial() {
      com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent result = new com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent(this);
      int from_bitField0_ = bitField0_;
      result.uin_ = uin_;
      result.longitude_ = longitude_;
      result.latitude_ = latitude_;
      result.dynamic_ = dynamic_;
      if (((bitField0_ & 0x00000001) != 0)) {
        pictures_ = pictures_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.pictures_ = pictures_;
      result.topic_ = topic_;
      result.classify_ = classify_;
      result.updateTime_ = updateTime_;
      result.dynamicContentId_ = dynamicContentId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent) {
        return mergeFrom((com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent other) {
      if (other == com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent.getDefaultInstance()) return this;
      if (other.getUin() != 0L) {
        setUin(other.getUin());
      }
      if (other.getLongitude() != 0D) {
        setLongitude(other.getLongitude());
      }
      if (other.getLatitude() != 0D) {
        setLatitude(other.getLatitude());
      }
      if (!other.getDynamic().isEmpty()) {
        dynamic_ = other.dynamic_;
        onChanged();
      }
      if (!other.pictures_.isEmpty()) {
        if (pictures_.isEmpty()) {
          pictures_ = other.pictures_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensurePicturesIsMutable();
          pictures_.addAll(other.pictures_);
        }
        onChanged();
      }
      if (!other.getTopic().isEmpty()) {
        topic_ = other.topic_;
        onChanged();
      }
      if (other.getClassify() != 0) {
        setClassify(other.getClassify());
      }
      if (other.getUpdateTime() != 0L) {
        setUpdateTime(other.getUpdateTime());
      }
      if (other.getDynamicContentId() != 0L) {
        setDynamicContentId(other.getDynamicContentId());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long uin_ ;
    /**
     * <pre>
     * 消息所属的UIN
     * </pre>
     *
     * <code>int64 uin = 1;</code>
     */
    public long getUin() {
      return uin_;
    }
    /**
     * <pre>
     * 消息所属的UIN
     * </pre>
     *
     * <code>int64 uin = 1;</code>
     */
    public Builder setUin(long value) {
      
      uin_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 消息所属的UIN
     * </pre>
     *
     * <code>int64 uin = 1;</code>
     */
    public Builder clearUin() {
      
      uin_ = 0L;
      onChanged();
      return this;
    }

    private double longitude_ ;
    /**
     * <pre>
     * 用户发布消息的经度
     * </pre>
     *
     * <code>double longitude = 2;</code>
     */
    public double getLongitude() {
      return longitude_;
    }
    /**
     * <pre>
     * 用户发布消息的经度
     * </pre>
     *
     * <code>double longitude = 2;</code>
     */
    public Builder setLongitude(double value) {
      
      longitude_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息的经度
     * </pre>
     *
     * <code>double longitude = 2;</code>
     */
    public Builder clearLongitude() {
      
      longitude_ = 0D;
      onChanged();
      return this;
    }

    private double latitude_ ;
    /**
     * <pre>
     * 用户发布消息的维度
     * </pre>
     *
     * <code>double latitude = 3;</code>
     */
    public double getLatitude() {
      return latitude_;
    }
    /**
     * <pre>
     * 用户发布消息的维度
     * </pre>
     *
     * <code>double latitude = 3;</code>
     */
    public Builder setLatitude(double value) {
      
      latitude_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息的维度
     * </pre>
     *
     * <code>double latitude = 3;</code>
     */
    public Builder clearLatitude() {
      
      latitude_ = 0D;
      onChanged();
      return this;
    }

    private java.lang.Object dynamic_ = "";
    /**
     * <pre>
     * 用户发布消息的消息正文
     * </pre>
     *
     * <code>string dynamic = 4;</code>
     */
    public java.lang.String getDynamic() {
      java.lang.Object ref = dynamic_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        dynamic_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 用户发布消息的消息正文
     * </pre>
     *
     * <code>string dynamic = 4;</code>
     */
    public com.google.protobuf.ByteString
        getDynamicBytes() {
      java.lang.Object ref = dynamic_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        dynamic_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 用户发布消息的消息正文
     * </pre>
     *
     * <code>string dynamic = 4;</code>
     */
    public Builder setDynamic(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      dynamic_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息的消息正文
     * </pre>
     *
     * <code>string dynamic = 4;</code>
     */
    public Builder clearDynamic() {
      
      dynamic_ = getDefaultInstance().getDynamic();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息的消息正文
     * </pre>
     *
     * <code>string dynamic = 4;</code>
     */
    public Builder setDynamicBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      dynamic_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList pictures_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensurePicturesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        pictures_ = new com.google.protobuf.LazyStringArrayList(pictures_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getPicturesList() {
      return pictures_.getUnmodifiableView();
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public int getPicturesCount() {
      return pictures_.size();
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public java.lang.String getPictures(int index) {
      return pictures_.get(index);
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public com.google.protobuf.ByteString
        getPicturesBytes(int index) {
      return pictures_.getByteString(index);
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public Builder setPictures(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensurePicturesIsMutable();
      pictures_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public Builder addPictures(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensurePicturesIsMutable();
      pictures_.add(value);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public Builder addAllPictures(
        java.lang.Iterable<java.lang.String> values) {
      ensurePicturesIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, pictures_);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public Builder clearPictures() {
      pictures_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户发布消息携带的配图
     * </pre>
     *
     * <code>repeated string pictures = 5;</code>
     */
    public Builder addPicturesBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensurePicturesIsMutable();
      pictures_.add(value);
      onChanged();
      return this;
    }

    private java.lang.Object topic_ = "";
    /**
     * <pre>
     * 用户动态信息参与的话题
     * </pre>
     *
     * <code>string topic = 6;</code>
     */
    public java.lang.String getTopic() {
      java.lang.Object ref = topic_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        topic_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * 用户动态信息参与的话题
     * </pre>
     *
     * <code>string topic = 6;</code>
     */
    public com.google.protobuf.ByteString
        getTopicBytes() {
      java.lang.Object ref = topic_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        topic_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 用户动态信息参与的话题
     * </pre>
     *
     * <code>string topic = 6;</code>
     */
    public Builder setTopic(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      topic_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户动态信息参与的话题
     * </pre>
     *
     * <code>string topic = 6;</code>
     */
    public Builder clearTopic() {
      
      topic_ = getDefaultInstance().getTopic();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户动态信息参与的话题
     * </pre>
     *
     * <code>string topic = 6;</code>
     */
    public Builder setTopicBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      topic_ = value;
      onChanged();
      return this;
    }

    private int classify_ ;
    /**
     * <pre>
     * 用户消息动态的种类
     * </pre>
     *
     * <code>int32 classify = 7;</code>
     */
    public int getClassify() {
      return classify_;
    }
    /**
     * <pre>
     * 用户消息动态的种类
     * </pre>
     *
     * <code>int32 classify = 7;</code>
     */
    public Builder setClassify(int value) {
      
      classify_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户消息动态的种类
     * </pre>
     *
     * <code>int32 classify = 7;</code>
     */
    public Builder clearClassify() {
      
      classify_ = 0;
      onChanged();
      return this;
    }

    private long updateTime_ ;
    /**
     * <pre>
     * 用户消息动态的更新时间
     * </pre>
     *
     * <code>int64 updateTime = 8;</code>
     */
    public long getUpdateTime() {
      return updateTime_;
    }
    /**
     * <pre>
     * 用户消息动态的更新时间
     * </pre>
     *
     * <code>int64 updateTime = 8;</code>
     */
    public Builder setUpdateTime(long value) {
      
      updateTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户消息动态的更新时间
     * </pre>
     *
     * <code>int64 updateTime = 8;</code>
     */
    public Builder clearUpdateTime() {
      
      updateTime_ = 0L;
      onChanged();
      return this;
    }

    private long dynamicContentId_ ;
    /**
     * <pre>
     * 用户动态消息的ID
     * </pre>
     *
     * <code>int64 dynamicContentId = 9;</code>
     */
    public long getDynamicContentId() {
      return dynamicContentId_;
    }
    /**
     * <pre>
     * 用户动态消息的ID
     * </pre>
     *
     * <code>int64 dynamicContentId = 9;</code>
     */
    public Builder setDynamicContentId(long value) {
      
      dynamicContentId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 用户动态消息的ID
     * </pre>
     *
     * <code>int64 dynamicContentId = 9;</code>
     */
    public Builder clearDynamicContentId() {
      
      dynamicContentId_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:MomentDynamicContent)
  }

  // @@protoc_insertion_point(class_scope:MomentDynamicContent)
  private static final com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent();
  }

  public static com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MomentDynamicContent>
      PARSER = new com.google.protobuf.AbstractParser<MomentDynamicContent>() {
    @java.lang.Override
    public MomentDynamicContent parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MomentDynamicContent(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MomentDynamicContent> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MomentDynamicContent> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.upuphub.dew.community.connection.protobuf.moments.MomentDynamicContent getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

