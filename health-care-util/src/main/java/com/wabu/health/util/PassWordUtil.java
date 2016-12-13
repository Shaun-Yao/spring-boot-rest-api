package com.wabu.health.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 密码加密，加密的类型可有如下几种 MD2,MD5,SHA-1,SHA-256, SHA-384, SHA-512
 *
 * @author yao
 *
 */
@Slf4j
public class PassWordUtil {
    private PassWordUtil() {}

    public static final String SHA_512 = "SHA-512";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA = "SHA";
    public static final String MD5 = "MD5";

    public static final String AES = "AES";

    public static String md5(final String value) {
        String result = null;
        try {
            result = hash(value, MD5);
        }
        catch (IllegalArgumentException e) {
            log.error("系统 MD5 算法不可用");
        }
        return result;
    }

    public static String sha1(final String value) {
        String result = null;
        try {
            result = hash(value, SHA);
        }
        catch (IllegalArgumentException e) {
            log.error("系统 SHA 算法不可用");
        }
        return result;
    }

    public static String sha256(final String value) {
        String result = null;
        try {
            result = hash(value, SHA_256);
        }
        catch (IllegalArgumentException e) {
            log.error("系统 SHA256 算法不可用");
        }
        return result;
    }

    /**
     * 返回指定的字符串的 Hash
     *
     * @param text 要计算 Hash 的字符串
     * @param encryptType 加密类型，取值范围为： "MD5", "SHA", "SHA-1"
     * @return
     */
    public static String hash(String text, String encryptType) {
        if (null == encryptType || encryptType.length() == 0) {
            throw new IllegalArgumentException("encryptType 不能为 null");
        }
        if (null == text) {
            throw new IllegalArgumentException("text 不能为 null");
        }

        String result = null;
        byte[] b = text.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance(encryptType);
            md.update(b);
            result = BinaryUtil.toHexString(md.digest());
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

    /**
     * 使用 AES 加密
     * 
     * @param content
     * @param password
     * @return
     */
    public static String encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            /* http://www.javaworld.com.tw/jute/post/view?bid=5&id=218684 */
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(password.getBytes());
            kgen.init(128, sr);

            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES);// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return BinaryUtil.toHexString(result);
        }
        catch (Exception e) {}
        return null;
    }

    /**
     * 使用 AES 解密
     * 
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {
        if (content == null || content.length() == 0) {
            return "";
        }
        try {
            KeyGenerator kgen = KeyGenerator.getInstance(AES);
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, AES);
            Cipher cipher = Cipher.getInstance(AES);// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(BinaryUtil.fromHexString(content));
            return new String(result, "utf-8"); // 加密
        }
        catch (Exception e) {}
        return "";
    }

    /**
     * 随机生成密码的强度
     * 
     * @author yao
     */
    public enum PasswordStrength {
        /** 弱密码，仅含有 0-9 的数字 */
        NUMERIC("1234567890"), ALPHA_NUMERIC("1234567890abcdefghijklmnpqrstuvwxyz"), STRONG(
                "1234567890abcdefghijklmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ_+-=!@#$%^&*"), ;

        @Getter(AccessLevel.PACKAGE)
        private String allowedChars;

        PasswordStrength(final String allowedChars) {
            this.allowedChars = allowedChars;
        }
    }

    /**
     * 随机生成密码
     * TODO 强密码生成策略有问题，需要改
     * 
     * @param length 密码长度
     * @param ps 密码的强度
     * @return 随机生成的密码
     */
    public static String generate(int length, PasswordStrength ps) {
        int actualLength = length;
        if (actualLength < 0) {
            actualLength = 4;
        }
        PasswordStrength actualPs = ps;
        if (actualPs == null) {
            actualPs = PasswordStrength.NUMERIC;
        }

        final Random r = new Random();
        final StringBuilder sb = new StringBuilder();
        // 根据密码强度选择 source
        final String source = actualPs.getAllowedChars();
        for (int i = 0; i < actualLength; i++) {
            sb.append(source.charAt(r.nextInt(source.length())));
        }
        return sb.toString();
    }
}
