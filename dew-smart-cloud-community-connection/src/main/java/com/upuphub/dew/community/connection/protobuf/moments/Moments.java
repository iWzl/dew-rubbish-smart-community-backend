// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: moments.proto

package com.upuphub.dew.community.connection.protobuf.moments;

public final class Moments {
  private Moments() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_MomentDynamicContent_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_MomentDynamicContent_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Founder_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Founder_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rmoments.proto\"\240\001\n\024MomentDynamicContent" +
      "\022\013\n\003uin\030\001 \001(\003\022\021\n\tlongitude\030\002 \001(\001\022\020\n\010lati" +
      "tude\030\003 \001(\001\022\017\n\007dynamic\030\004 \001(\t\022\020\n\010pictures\030" +
      "\005 \003(\t\022\r\n\005topic\030\006 \001(\t\022\020\n\010classify\030\007 \001(\005\022\022" +
      "\n\nupdateTime\030\010 \001(\003\"\032\n\007Founder\022\017\n\007founder" +
      "\030\001 \001(\003B9\n5com.upuphub.dew.community.conn" +
      "ection.protobuf.momentsP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_MomentDynamicContent_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_MomentDynamicContent_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_MomentDynamicContent_descriptor,
        new java.lang.String[] { "Uin", "Longitude", "Latitude", "Dynamic", "Pictures", "Topic", "Classify", "UpdateTime", });
    internal_static_Founder_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_Founder_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Founder_descriptor,
        new java.lang.String[] { "Founder", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
