package com.upuphub.dew.community.moments.utils;

import ch.hsr.geohash.GeoHash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/11/20 21:02
 */
public class GeoHashUtil {

    /**
     * 构建GeoHash字符
     *
     * @param lat 纬度坐标
     * @param lon 经度坐标
     * @return 使用给定的经纬度坐标生成的GeoHash字符编码
     */
    public static String buildGeoHash(double lat,double lon){
        // GeoHash编码字符的长度（最大为12）
        int precision = 8;
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, precision);
        return geoHash.toBase32();
    }

    private void geoHashBuildTest() {
        // 纬度坐标
        double lat = 30.549608;
        // 经度坐标
        double lon = 114.376971;
        // GeoHash编码字符的长度（最大为12）
        int precision = 8;
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, precision);
        // 使用给定的经纬度坐标生成的二进制编码
        String binaryCode = geoHash.toBinaryString();
        System.out.println("经纬度坐标： (" + lat + ", " + lon + ")");
        System.out.println("二进制编码：" + binaryCode);
        // 使用给定的经纬度坐标生成的GeoHash字符编码
        String hashCode = geoHash.toBase32();
        System.out.println("GeoHash编码：" + hashCode);
        // 从二进制的编码中分离出经度和纬度分别对应的二进制编码
        char[] binaryCodes = binaryCode.toCharArray();
        List<Character> latCodes = new ArrayList<Character>();
        List<Character> lonCodes = new ArrayList<Character>();
        for (int i = 0; i < binaryCodes.length; i++) {
            if (i % 2 == 0) {
                lonCodes.add(binaryCodes[i]);
            } else {
                latCodes.add(binaryCodes[i]);
            }
        }
        // 纬度对应的二进制编码
        StringBuilder latCode = new StringBuilder();
        // 经度对应的二进制编码
        StringBuilder lonCode = new StringBuilder();
        for (Character ch : latCodes) {
            latCode.append(ch);
        }
        for (Character ch : lonCodes) {
            lonCode.append(ch);
        }
        System.out.println("纬度二进制编码：" + latCode.toString());
        System.out.println("经度二进制编码：" + lonCode.toString());
    }
}
