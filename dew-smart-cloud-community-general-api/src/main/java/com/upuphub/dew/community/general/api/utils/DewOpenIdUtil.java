package com.upuphub.dew.community.general.api.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


/**
 * @author Leo Wang
 * @version 1.0
 * @date 2019/9/4 23:41
 */

@Slf4j
public class DewOpenIdUtil {

    private static final String PASSWORD = "dew:account:open";
    private static final String IV = "0000000000000000";

    public static String generateOpenId(Long uin) {
        try {
            byte[] contentBytes = uin.toString().getBytes(StandardCharsets.UTF_8);
            byte[] paddingContentBytes = new byte[16];
            for(int i =0 ; i < 16 ; i++){
                if(i < contentBytes.length){
                    paddingContentBytes[i] = contentBytes[i];
                }else {
                    paddingContentBytes[i] = '\0';
                }
            }
            Cipher cipher = generateCipher(Cipher.ENCRYPT_MODE);
            byte[] result = cipher.doFinal(paddingContentBytes);
            // 加密
            return Hex.encodeHexString(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException  | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
           log.error("OpenID change Error",e);
        }
        return null;
    }

    private static Cipher generateCipher(int mode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKeySpec key = new SecretKeySpec(PASSWORD.getBytes(StandardCharsets.UTF_8), "AES");
        // 创建密码器
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        // 初始化
        cipher.init(mode, key, new IvParameterSpec(IV.getBytes()));
        return cipher;
    }

    public static Long generateUin(String openId) {
        try {
            Cipher cipher = generateCipher(Cipher.DECRYPT_MODE);
            byte[] result = cipher.doFinal(Hex.decodeHex(openId));
            // 加密
            String uin = new String(result, StandardCharsets.UTF_8);
            int pos = uin.indexOf('\0');
            if(-1 != pos){
                uin = uin.substring(0, pos);
            }
            return Long.valueOf(uin);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException | DecoderException e) {
            log.error("Uin change Error",e);
        }
        return null;
    }
}
