# 基本说明
结构定义文件为.proto，可以使用import包含另一个.proto文件，注释使用//
# 语法
## 字段限制
* required: 必须赋值的字符
* optional: 可有可无的字段，可以使用[default = xxx]配置默认值
* repeated: 可重复变长字段，类似数组或是LIST
* 由于一些历史原因，基本数值类型的repeated的字段并没有被尽可能地高效编码。在新的代码中，用户应该使用特殊选项[packed=true]来保证更高效的编码：PS(repeated int32 samples = 4 [packed=true];)
## tag
每个字段都有独一无二的tag
tag 1-15是字节编码，16-2047使用2字节编码，所以1-15给频繁使用的字段

## 类型

一个标量消息字段可以含有一个如下的类型——该表格展示了定义于.proto文件中的类型，以及与之对应的、在自动生成的访问类中定义的类型:

| .proto类型 | Java 类型  | C++类型 | 备注                                                         |
| ---------- | ---------- | ------- | ------------------------------------------------------------ |
| double     | double     | double  |                                                              |
| float      | float      | float   |                                                              |
| int32      | int        | int32   | 使用可变长编码方式。编码负数时不够高效——如果你的字段可能含有负数，那么请使用sint32。 |
| int64      | long       | int64   | 使用可变长编码方式。编码负数时不够高效——如果你的字段可能含有负数，那么请使用sint64。 |
| uint32     | int[1]     | uint32  | Uses variable-length encoding.                               |
| uint64     | long[1]    | uint64  | Uses variable-length encoding.                               |
| sint32     | int        | int32   | 使用可变长编码方式。有符号的整型值。编码时比通常的int32高效。 |
| sint64     | long       | int64   | 使用可变长编码方式。有符号的整型值。编码时比通常的int64高效。 |
| sint64     | long       | int64   | 使用可变长编码方式。有符号的整型值。编码时比通常的int64高效。 |
| fixed64    | long[1]    | uint64  | 总是8个字节。如果数值总是比总是比256大的话，这个类型会比uint64高效。 |
| sfixed32   | int        | int32   | 总是4个字节。                                                |
| sfixed64   | long       | int64   | 总是8个字节。                                                |
| bool       | boolean    | bool    |                                                              |
| string     | String     | string  | 一个字符串必须是UTF-8编码或者7-bit ASCII编码的文本。         |
| bytes      | ByteString | string  | 可能包含任意顺序的字节数据。                                 |

​	在一个.proto文件中可以定义多个消息类型。在定义多个相关的消息的时候，这一点特别有用——例如，如果想定义与SearchResponse消息类型对应的回复消息格式的话，你可以将它添加到相同的.proto文件中

## 枚举

当需要定义一个消息类型的时候，可能想为一个字段指定某“预定义值序列”中的一个值。枚举常量必须在32位整型值的范围内。因为enum值是使用可变编码方式的，对负数不够高效，因此不推荐在enum中使用负数。

可以在 一个消息定义的内部或外部定义枚举——这些枚举可以在.proto文件中的任何消息定义里重用。当然也可以在一个消息中声明一个枚举类型，而在另一个不同 的消息中使用它——采用MessageType.EnumType的语法格式。

## 导入定义

通过导入（importing）其他.proto文件中的定义来使用它们。要导入其他.proto文件的定义，你需要在你的文件中添加一个导入声明，如：



protocol编译器就会在一系列目录中查找需要被导入的文件，这些目录通过protocol编译器的命令行参数-I/–import_path指定。如果不提供参数，编译器就在其调用目录下查找。

## 选项

一些选项是文件级别的，意味着它可以作用于最外范围，不包含在任何消息内部、enum或服务定义中。一些选项是消息级别的，意味着它可以用在消息定 义的内部。当然有些选项可以作用在域、enum类型、enum值、服务类型及服务方法中。到目前为止，并没有一种有效的选项能作用于所有的类型。

`java_package` (file option): 这个选项表明生成java类所在的包。如果在.proto文件中没有明确的声明java_package，就采用默认的包名。当然了，默认方式产生的 java包名并不是最好的方式，按照应用名称倒序方式进行排序的。如果不需要产生java代码，则该选项将不起任何作用。如：

```
option java_package = "com.example.foo";
```

`java_outer_classname `(file option): 该选项表明想要生成Java类的名称。如果在.proto文件中没有明确的`java_outer_classname`定义，生成的class名称将会根据.proto文件的名称采用驼峰式的命名方式进行生成。如（foo_bar.proto生成的java类名为FooBar.java）,如果不生成java代码，则该选项不起任何作用。如：

```
`option java_outer_classname = "Ponycopter";`
```

## 解析与序列化

每个message都包含如下方法，用于解析和序列化，注意目标是**字节形式**，非文本。
`bool SerializeToString(string* output) const`: 将message序列化成二进制保存在output中，注意保存的是二进制，不是文本；仅仅是string作为容器。
`bool ParseFromString(const string& data)`: 从给定的二进制数值中解析成message
`bool SerializeToOstream(ostream* output) const`: 序列化到ostream中
`bool ParseFromIstream(istream* input)`: 从istream中解析出message

# 举例介绍

## 建立.proto文件

建立`.proto`文件定义`message`，如下`addressbook.proto`

```java

syntax = "proto3"; // 定义语法类型，通常proto3好于proto2，proto2好于proto1
 

option java_package = "com.example.tutorial";      // 定义报名
option java_outer_classname = "AddressBookProtos"; // 输出的文件名
 
message Person {        // 将会生成对应的指定类 
  required string name = 1;
  required int32 id = 2;
  optional string email = 3;
 
  enum PhoneType { // 定义枚举类型，生成Person_PhoneType类型
    MOBILE = 0;
    HOME = 1;
    WORK = 2;
  }
 
  message PhoneNumber { // 生成Person_PhoneNumber类
    required string number = 1;
    optional PhoneType type = 2 [default = HOME]; // 值必须是枚举类型中的一个
  }
  repeated PhoneNumber phones = 4;
}
 
message AddressBook {
  repeated Person people = 1;
}

```

## 生成工具插件

```xml
<plugin>
<groupId>org.xolstice.maven.plugins</groupId>
<artifactId>protobuf-maven-plugin</artifactId>
<version>0.6.1</version>
<extensions>true</extensions>
<configuration>
    <!--.proto资源文件位置-->
    <protoSourceRoot>${basedir}/src/main/resources/protobuf/</protoSourceRoot>
    <!--文件输出位置-->
    <outputDirectory>${project.build.sourceDirectory}</outputDirectory>
    <!--设置是否在生成java文件之前清空outputDirectory的文件，默认值为true，设置为false时也会覆盖同名文件-->
    <clearOutputDirectory>false</clearOutputDirectory>
    <!--临时文件生成位置-->
    <temporaryProtoFileDirectory>${project.build.directory}/protoc-temp</temporaryProtoFileDirectory>
    <!--编译工具路径-->
    <protocExecutable>${project.basedir}/tools/protoc</protocExecutable>
</configuration>
<executions>
    <execution>
        <goals>
            <goal>compile</goal>
            <goal>test-compile</goal>
        </goals>
    </execution>
</executions>
</plugin>
```

