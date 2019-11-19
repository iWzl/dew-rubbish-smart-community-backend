package protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.upuphub.dew.community.connection.protobuf.account.Location;

import java.util.Arrays;
import java.util.Base64;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Location location1 = Location.newBuilder()
                .setCity("Chengdu")
                .setCountry("China")
                .setLatitude(200.11)
                .setLongitude(211.11)
                .setProvince("四川")
                .setUin(100000L)
                .setStreet("高新区")
                .build();
        System.out.println(location1);

        byte[] bytesProto = location1.toByteArray();
        System.out.println(Arrays.toString(bytesProto));

        String base64Proto = Base64.getEncoder().encodeToString(location1.toByteArray());
        System.out.println(base64Proto);

        Location location2 = Location.parseFrom(bytesProto);
        System.out.println(location2);

        Location location3 = Location.parseFrom(Base64.getDecoder().decode(base64Proto));
        System.out.println(location3);
    }
}
