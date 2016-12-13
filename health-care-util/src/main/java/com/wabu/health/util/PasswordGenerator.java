/**
 * PasswordGenerator.java
 * 
 * @date: 2013-1-16
 * @author: Xiaoyu Guo
 */
package com.wabu.health.util;

import org.apache.commons.lang3.StringUtils;

import com.wabu.health.util.PassWordUtil.PasswordStrength;




/**
 * 描述一个密码生成器
 * 
 * @author yao
 */
public class PasswordGenerator {
    /**
     * @return 一个新的随机密码
     */
    public static String next() {
        return PassWordUtil.generate(4, PasswordStrength.NUMERIC);
    }
    
    public static String hash(String password, String salt) {
        // TODO 应用 salt + sha1 生成 hash
        password = PassWordUtil.sha1(PassWordUtil.md5(saltify(password, salt)));
        return password;
    }

    /**
     * 检查明文的密码和 hash 是否一致
     * 
     * @param password 明文的密码
     * @param salt 盐
     * @param hash hash
     * @return 如果一致则返回 {@code true}; 反之返回 {@code false}
     */
    public static boolean verify(final String password,
                                 final String salt,
                                 final String hash) {
        final String h = hash(password, salt);
        if (h.equals(hash)) {
            return true;
        }
        return false;
    }

    /**
     * 加盐算法
     * 
     * @param password 原始的密码
     * @param salt 随机生成的盐值
     * @return 当 password 为 null 时，返回 null; 当 salt 为 null 时，返回 password;
     *         其他情况返回加盐后的值
     */
    protected static final String saltify(String password, String salt) {
        if (password == null) {
            return null;
        }
        if (salt == null) {
            return password;
        }
        // 补全 4 位
        password = StringUtils.leftPad(password, 4, '_');
        StringBuilder sb = new StringBuilder();
        sb.append(password, 0, 2);
        sb.append("\\\\");
        sb.append(salt);
        sb.append("//");
        sb.append(password, 2, password.length());
        return sb.toString();
    }
}
