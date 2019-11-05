// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mqtt.proto

package com.upuphub.dew.community.connection.protobuf.mqtt;

/**
 * Protobuf type {@code MqttMessage}
 */
public  final class MqttMessage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:MqttMessage)
    MqttMessageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MqttMessage.newBuilder() to construct.
  private MqttMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MqttMessage() {
    payload_ = com.google.protobuf.ByteString.EMPTY;
    messageId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MqttMessage();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MqttMessage(
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

            tag_ = input.readInt32();
            break;
          }
          case 16: {

            type_ = input.readInt32();
            break;
          }
          case 26: {

            payload_ = input.readBytes();
            break;
          }
          case 34: {
            java.lang.String s = input.readStringRequireUtf8();

            messageId_ = s;
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
    return com.upuphub.dew.community.connection.protobuf.mqtt.Mqtt.internal_static_MqttMessage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.upuphub.dew.community.connection.protobuf.mqtt.Mqtt.internal_static_MqttMessage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage.class, com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage.Builder.class);
  }

  public static final int TAG_FIELD_NUMBER = 1;
  private int tag_;
  /**
   * <code>int32 tag = 1;</code>
   */
  public int getTag() {
    return tag_;
  }

  public static final int TYPE_FIELD_NUMBER = 2;
  private int type_;
  /**
   * <code>int32 type = 2;</code>
   */
  public int getType() {
    return type_;
  }

  public static final int PAYLOAD_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString payload_;
  /**
   * <code>bytes payload = 3;</code>
   */
  public com.google.protobuf.ByteString getPayload() {
    return payload_;
  }

  public static final int MESSAGEID_FIELD_NUMBER = 4;
  private volatile java.lang.Object messageId_;
  /**
   * <code>string messageId = 4;</code>
   */
  public java.lang.String getMessageId() {
    java.lang.Object ref = messageId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      messageId_ = s;
      return s;
    }
  }
  /**
   * <code>string messageId = 4;</code>
   */
  public com.google.protobuf.ByteString
      getMessageIdBytes() {
    java.lang.Object ref = messageId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      messageId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (tag_ != 0) {
      output.writeInt32(1, tag_);
    }
    if (type_ != 0) {
      output.writeInt32(2, type_);
    }
    if (!payload_.isEmpty()) {
      output.writeBytes(3, payload_);
    }
    if (!getMessageIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, messageId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (tag_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, tag_);
    }
    if (type_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, type_);
    }
    if (!payload_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, payload_);
    }
    if (!getMessageIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, messageId_);
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
    if (!(obj instanceof com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage)) {
      return super.equals(obj);
    }
    com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage other = (com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage) obj;

    if (getTag()
        != other.getTag()) return false;
    if (getType()
        != other.getType()) return false;
    if (!getPayload()
        .equals(other.getPayload())) return false;
    if (!getMessageId()
        .equals(other.getMessageId())) return false;
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
    hash = (37 * hash) + TAG_FIELD_NUMBER;
    hash = (53 * hash) + getTag();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType();
    hash = (37 * hash) + PAYLOAD_FIELD_NUMBER;
    hash = (53 * hash) + getPayload().hashCode();
    hash = (37 * hash) + MESSAGEID_FIELD_NUMBER;
    hash = (53 * hash) + getMessageId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parseFrom(
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
  public static Builder newBuilder(com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage prototype) {
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
   * Protobuf type {@code MqttMessage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:MqttMessage)
      com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.upuphub.dew.community.connection.protobuf.mqtt.Mqtt.internal_static_MqttMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.upuphub.dew.community.connection.protobuf.mqtt.Mqtt.internal_static_MqttMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage.class, com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage.Builder.class);
    }

    // Construct using com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage.newBuilder()
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
      tag_ = 0;

      type_ = 0;

      payload_ = com.google.protobuf.ByteString.EMPTY;

      messageId_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.upuphub.dew.community.connection.protobuf.mqtt.Mqtt.internal_static_MqttMessage_descriptor;
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage getDefaultInstanceForType() {
      return com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage.getDefaultInstance();
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage build() {
      com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage buildPartial() {
      com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage result = new com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage(this);
      result.tag_ = tag_;
      result.type_ = type_;
      result.payload_ = payload_;
      result.messageId_ = messageId_;
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
      if (other instanceof com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage) {
        return mergeFrom((com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage other) {
      if (other == com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage.getDefaultInstance()) return this;
      if (other.getTag() != 0) {
        setTag(other.getTag());
      }
      if (other.getType() != 0) {
        setType(other.getType());
      }
      if (other.getPayload() != com.google.protobuf.ByteString.EMPTY) {
        setPayload(other.getPayload());
      }
      if (!other.getMessageId().isEmpty()) {
        messageId_ = other.messageId_;
        onChanged();
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
      com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int tag_ ;
    /**
     * <code>int32 tag = 1;</code>
     */
    public int getTag() {
      return tag_;
    }
    /**
     * <code>int32 tag = 1;</code>
     */
    public Builder setTag(int value) {
      
      tag_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 tag = 1;</code>
     */
    public Builder clearTag() {
      
      tag_ = 0;
      onChanged();
      return this;
    }

    private int type_ ;
    /**
     * <code>int32 type = 2;</code>
     */
    public int getType() {
      return type_;
    }
    /**
     * <code>int32 type = 2;</code>
     */
    public Builder setType(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 type = 2;</code>
     */
    public Builder clearType() {
      
      type_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString payload_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes payload = 3;</code>
     */
    public com.google.protobuf.ByteString getPayload() {
      return payload_;
    }
    /**
     * <code>bytes payload = 3;</code>
     */
    public Builder setPayload(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      payload_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes payload = 3;</code>
     */
    public Builder clearPayload() {
      
      payload_ = getDefaultInstance().getPayload();
      onChanged();
      return this;
    }

    private java.lang.Object messageId_ = "";
    /**
     * <code>string messageId = 4;</code>
     */
    public java.lang.String getMessageId() {
      java.lang.Object ref = messageId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        messageId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string messageId = 4;</code>
     */
    public com.google.protobuf.ByteString
        getMessageIdBytes() {
      java.lang.Object ref = messageId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        messageId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string messageId = 4;</code>
     */
    public Builder setMessageId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      messageId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string messageId = 4;</code>
     */
    public Builder clearMessageId() {
      
      messageId_ = getDefaultInstance().getMessageId();
      onChanged();
      return this;
    }
    /**
     * <code>string messageId = 4;</code>
     */
    public Builder setMessageIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      messageId_ = value;
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


    // @@protoc_insertion_point(builder_scope:MqttMessage)
  }

  // @@protoc_insertion_point(class_scope:MqttMessage)
  private static final com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage();
  }

  public static com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MqttMessage>
      PARSER = new com.google.protobuf.AbstractParser<MqttMessage>() {
    @java.lang.Override
    public MqttMessage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MqttMessage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MqttMessage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MqttMessage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.upuphub.dew.community.connection.protobuf.mqtt.MqttMessage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

