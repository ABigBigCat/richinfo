package com.wugui.datax.admin.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jiahui on 2020-07-16 15:42
 */
public class PublicUtils {

    //加密密码
    public static String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    //解密
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //加密 base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

}
