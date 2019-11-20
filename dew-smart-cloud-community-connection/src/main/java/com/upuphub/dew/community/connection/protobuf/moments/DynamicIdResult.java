// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: moments.proto

package com.upuphub.dew.community.connection.protobuf.moments;

/**
 * Protobuf type {@code DynamicIdResult}
 */
public  final class DynamicIdResult extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:DynamicIdResult)
    DynamicIdResultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DynamicIdResult.newBuilder() to construct.
  private DynamicIdResult(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DynamicIdResult() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DynamicIdResult();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DynamicIdResult(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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

            dynamicId_ = input.readInt64();
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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_DynamicIdResult_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_DynamicIdResult_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult.class, com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult.Builder.class);
  }

  public static final int DYNAMICID_FIELD_NUMBER = 1;
  private long dynamicId_;
  /**
   * <pre>
   * Dynamic Content ID
   * </pre>
   *
   * <code>int64 dynamicId = 1;</code>
   */
  public long getDynamicId() {
    return dynamicId_;
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
    if (dynamicId_ != 0L) {
      output.writeInt64(1, dynamicId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (dynamicId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, dynamicId_);
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
    if (!(obj instanceof com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult)) {
      return super.equals(obj);
    }
    com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult other = (com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult) obj;

    if (getDynamicId()
        != other.getDynamicId()) return false;
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
    hash = (37 * hash) + DYNAMICID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDynamicId());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parseFrom(
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
  public static Builder newBuilder(com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult prototype) {
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
   * Protobuf type {@code DynamicIdResult}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:DynamicIdResult)
      com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_DynamicIdResult_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_DynamicIdResult_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult.class, com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult.Builder.class);
    }

    // Construct using com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult.newBuilder()
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
      dynamicId_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.upuphub.dew.community.connection.protobuf.moments.Moments.internal_static_DynamicIdResult_descriptor;
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult getDefaultInstanceForType() {
      return com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult.getDefaultInstance();
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult build() {
      com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult buildPartial() {
      com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult result = new com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult(this);
      result.dynamicId_ = dynamicId_;
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
      if (other instanceof com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult) {
        return mergeFrom((com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult other) {
      if (other == com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult.getDefaultInstance()) return this;
      if (other.getDynamicId() != 0L) {
        setDynamicId(other.getDynamicId());
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
      com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long dynamicId_ ;
    /**
     * <pre>
     * Dynamic Content ID
     * </pre>
     *
     * <code>int64 dynamicId = 1;</code>
     */
    public long getDynamicId() {
      return dynamicId_;
    }
    /**
     * <pre>
     * Dynamic Content ID
     * </pre>
     *
     * <code>int64 dynamicId = 1;</code>
     */
    public Builder setDynamicId(long value) {
      
      dynamicId_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Dynamic Content ID
     * </pre>
     *
     * <code>int64 dynamicId = 1;</code>
     */
    public Builder clearDynamicId() {
      
      dynamicId_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:DynamicIdResult)
  }

  // @@protoc_insertion_point(class_scope:DynamicIdResult)
  private static final com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult();
  }

  public static com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DynamicIdResult>
      PARSER = new com.google.protobuf.AbstractParser<DynamicIdResult>() {
    @java.lang.Override
    public DynamicIdResult parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DynamicIdResult(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DynamicIdResult> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DynamicIdResult> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.upuphub.dew.community.connection.protobuf.moments.DynamicIdResult getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

