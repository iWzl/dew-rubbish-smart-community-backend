package com.upuphub.dew.community.moments.utils;

import ch.hsr.geohash.GeoHash;

/**
 * @author Leo Wang
 * @version 1.0
 *
 * geoHash length	width	height
 * 1	5,009.4km	4,992.6km
 * 2	1,252.3km	624.1km
 * 3	156.5km	156km
 * 4	39.1km	19.5km
 * 5	4.9km	4.9km
 * 6	1.2km	609.4m
 * 7	152.9m	152.4m
 * 8	38.2m	19m
 * 9	4.8m	4.8m
 * 10	1.2m	59.5cm
 * 11	14.9cm	14.9cm
 * 12	3.7cm	1.9cm
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
        int precision = 12;
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, precision);
        return geoHash.toBase32();
    }

    /**
     * 构建GeoHash字符
     *
     * @param lat 纬度坐标
     * @param lon 经度坐标
     * @return 使用给定的经纬度坐标生成的GeoHash字符编码
     */
    public static String buildGeoHashCityRange(double lat,double lon){
        // GeoHash编码字符的长度（最大为12）
        int precision = 4;
        GeoHash geoHash = GeoHash.withCharacterPrecision(lat, lon, precision);
        return geoHash.toBase32();
    }
}
