package com.upuphub.dew.community.general.api.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Date;

/**
 * JWTUtils的工具类,产生和创建Token
 * @author Leo Wang
 * @version 1.0
 * @date 2019/8/10 20:13
 */
public class JWTUtil {

    private static final long EXPIRE_TIME_PREFIX = 1296000L;
    private static final long EXPIRE_TIME = EXPIRE_TIME_PREFIX * 1000;
    private final static String ISSUER = "RBC_SVR";
    private final static String SUBJECT = "RBC_TOKEN";

    /**
     * 创建用户Token
     *
     * @param uin  用户uin
     * @param password 用户密钥
     * @return 是否正确
     */
    public static String createToken(String uin,String password){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(password);
        return JWT.create()
                .withSubject(SUBJECT)
                .withClaim("uin",uin)
                .withIssuer(ISSUER)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withSubject(SUBJECT)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * 获取账户用户名
     *
     * @author Leo Wang
     * @param token Token
     * @return token中包含的用户名
     */
    public static String getPassword(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("password").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 根据token，返回用户ID
     *
     * @author Leo Wang
     * @param token Token
     * @return java.lang.Long
     */
    public static String getUin(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uin").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String getToken() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal().toString();
    }

    public static Date getExpiresAt() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

}
