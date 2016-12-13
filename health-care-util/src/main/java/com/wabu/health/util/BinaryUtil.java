
package com.wabu.health.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 包含二进制协议的常用功能
 *
 * @author yao
 */
public class BinaryUtil {
    private BinaryUtil() {}
    /**
     * 按分隔符把字节数组分割成小块
     *
     * @param buffer 源字节数组
     * @param separator 分割字节
     * @param maxCount 最多分割多少次
     * @return 一个包含字节数组的 List ，至少会包含一个元素
     */
    public static final List<byte[]> splitBytes(final byte[] buffer,
                                                final byte separator,
                                                final int maxCount) {
        if (buffer == null) {
            throw new NullPointerException("buffer 不能为 null");
        }
        if (maxCount <= 0) {
            return Collections.emptyList();
        }
        int index = 0;
        List<byte[]> result = new ArrayList<byte[]>();
        int currentCount = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == separator) {
                currentCount++;

                byte[] temp = sub(buffer, index, i - index);
                result.add(temp);
                index = i + 1;

                if (currentCount >= maxCount) {
                    break;
                }
            }
        }
        if (index < buffer.length) {
            byte[] temp = sub(buffer, index, buffer.length - index);
            result.add(temp);
        }

        return result;
    }

    /**
     * 比较两个数组是否相同
     *
     * @param byte数组a
     * @param byte数组b
     * @return 返回是否相同
     */
    public static final boolean compare(final byte[] a, final byte[] b) {
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 将int转成bytes
     * 
     * @param iSource
     * @param iArrayLen
     * @return
     */
    public static byte[] toByteArray(int iSource, int iArrayLen) {
        byte[] bLocalArr = new byte[iArrayLen];
        for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
            bLocalArr[iArrayLen - i - 1] = (byte) (iSource >> 8 * i & 0xFF);

        }
        return bLocalArr;
    }

    /**
     * 检查一个大数组中，从指定位置开始的一部分是否与给定的数组一致
     * <p>
     * 例子：
     * </p>
     * 
     * <pre>
     * byte[] bigArray = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
     * byte[] pattern  = new byte[]{2, 3};
     * boolean isMatch = BinaryUtil.matches(bigArray, 0, pattern);
     * // isMatch = false
     *
     * isMatch = BianryUtil.matches(bigArray, 2, pattern);
     * // isMatch = true
     * </pre>
     * 
     * @param data 要检查的大数组
     * @param index 要开始的 index ，取值范围为 [0, data.length)
     * @param b 给定的检查模板
     * @return 如果一致则返回 {@code true}
     */
    public static boolean matches(final byte[] data, final int index, final byte[] b) {
        if (data == null || b == null) {
            throw new NullPointerException();
        }
        if (data.length - index + 1 < b.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        for (int i = 0; i < b.length; i++) {
            if (data[index + i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给指定的字节数组在指定的位置设置数据
     * <p>
     * 例如：
     * </p>
     *
     * <pre>
     * {@code
     * byte[] src = [1, 2, 3];
     * byte[] dest = [0, 0, 0, 0, 0, 0];
     * int startIndex = 2;
     * byte[] result = set(dest, src, startIndex);
     *
     * // result = [0, 0, 1, 2, 3, 0];
     * }
     * </pre>
     *
     * @param destArray
     *            要设置的字节数组。若为 <code>null</code> 则会分配一个新的数组。
     * @param dataToSet
     *            要设置的数据
     * @param startIndex
     *            起始 index ，从 0 开始
     * @return 设置后的数组
     */
    public static final byte[] set(byte[] destArray, byte[] dataToSet, int startIndex) {
        if (dataToSet == null) {
            throw new NullPointerException("dataToSet 不能为 null");
        }
        byte[] result = null;

        if (destArray == null) {
            // 当 destArray 为 null 时，分配一个新数组
            result = new byte[startIndex + dataToSet.length];
        } else if (destArray.length < dataToSet.length + startIndex) {
            // 当 destArray 长度不够时，分配一个新数组
            byte[] r = new byte[startIndex + dataToSet.length];
            result = set(r, destArray, 0);
        }

        for (int i = 0; i < dataToSet.length; i++) {
            // 进行赋值
            if (result == null) {
                destArray[i + startIndex] = dataToSet[i];
            } else {
                result[i + startIndex] = dataToSet[i];
            }
        }
        if(result == null) {
            return destArray;
        } else {
            return result;
        }
    }

    /**
     * 取出指定的字节数组中的一部分
     *
     * @param srcArray
     * @param startIndex
     *            起始 index ，若为负值则按 0 处理
     * @param len
     *            要复制的部分的长度
     * @return
     */
    public static final byte[] sub(byte[] srcArray, int startIndex, int len) {
        if (srcArray == null) {
            return new byte[0];
        }

        startIndex = Math.max(0, startIndex);
        len = Math.max(0, len);

        if (startIndex > srcArray.length - 1) {
            throw new IllegalArgumentException("startIndex 过大");
        }

        len = Math.min(len, srcArray.length - startIndex);

        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = srcArray[startIndex + i];
        }

        return result;
    }

    /**
     * 获取字节数组中的 unsigned short (U16) 型数据
     * <p>
     * 例如：
     * </p>
     *
     * <pre>
     * {@code
     * byte[] data = [0, 0, 0, 1, 0, 0];
     * int sh = getUShort(data, 2);
     * // sh = 1;
     * }
     * </pre>
     *
     * @param array
     *            字节数组
     * @param index
     * @return 结果
     */
    public static final int getUShort(byte[] array, int index) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= array.length - 1) {
            throw new IllegalArgumentException();
        }
        byte hi = array[index];
        byte low = array[index + 1];
        return ((hi & 0xFF) << 8) + (low & 0xFF);
    }

    /**
     * 在字节数组中设置 unsigned short (U16) 型数据
     *
     * @param array
     * @param index
     * @param value
     */
    public static final void setUShort(byte[] array, int index, int value) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= array.length - 1) {
            throw new IllegalArgumentException();
        }
        byte hi = (byte) ((value >> 8) & 0xFF);
        byte low = (byte) (value & 0xFF);
        array[index] = hi;
        array[index + 1] = low;
    }

    /**
     * 获取字节数组中的 short (S16) 型数据
     * <p>
     * </p>
     * {@code
     * <pre>
     * byte[] data = [0, 0, 0x30, 0x39, 0];
     * int sh = getShort(data, 2);
     * // sh = -12345
     * </pre>
     * }
     *
     * @param array
     * @param index
     * @return
     */
    public static final int getShort(byte[] array, int index) {
        // 符号位
        boolean isNegative = (array[index] & 0x80) != 0;

        byte hi = array[index];
        byte low = array[index + 1];
        int result = ((hi & 0xFF) << 8) + (low & 0xFF);
        if (isNegative) {
            // 取反加一
            result = ~(result & 0xFFFF) + 1;
        }
        return result;
    }

    /**
     * 在字节数组中设置 short 型数据
     *
     * @param destArray
     * @param index
     * @param value
     *            要设置的值
     */
    public static final void setShort(byte[] destArray, int index, int value) {
        if (destArray == null) {
            throw new NullPointerException("destArray 不能为 null");
        }
        assert destArray.length > index + 1;

        byte hi = (byte) ((value >> 8) & 0xFF);
        byte low = (byte) (value & 0xFF);
        destArray[index] = hi;
        destArray[index + 1] = low;
    }

    /**
     * 获取字节数组中的 byte (U8) 型数据
     * <p>
     * 例如：
     * </p>
     *
     * <pre>
     * {@code
     * byte[] data = [0xFF, 0xFF, 0];
     * int bt = getByte(data, 1);
     * // bt = 255;
     * }
     * </pre>
     *
     * @param array
     * @param index
     * @return
     */
    public static final int getUByte(byte[] array, int index) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException();
        }
        byte b = array[index];
        return b & 0xFF;
    }

    /**
     * 获取字节数组中的 Boolean 型数据
     * <p>
     * <code>0</code> 会被转换为 <code>false</code>
     * </p>
     * <p>
     * 其他值会被转换为 <code>true</code>
     * </p>
     *
     * @param array
     * @param index
     * @return
     */
    public static final boolean getBoolean(byte[] array, int index) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index >= array.length) {
            throw new IllegalArgumentException();
        }
        if (array[index] != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 对整个数组中的数据进行异或操作
     *
     * @param array
     * @return
     */
    public static final byte xor(byte[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        byte res = 0;
        for (int i = 0; i < array.length; i++) {
            res ^= array[i];
        }
        return res;
    }

    private static final char[] HEX_CHARS = {'0',
                                             '1',
                                             '2',
                                             '3',
                                             '4',
                                             '5',
                                             '6',
                                             '7',
                                             '8',
                                             '9',
                                             'A',
                                             'B',
                                             'C',
                                             'D',
                                             'E',
                                             'F'};

    public static final byte fromHexChar(char hexChar) {
        hexChar = Character.toUpperCase(hexChar);
        for (byte i = 0; i < HEX_CHARS.length; i++) {
            if (HEX_CHARS[i] == hexChar) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 将一个 16 进制字符串解析成字节数组
     *
     * @param hex
     * @return
     */
    public static final byte[] fromHexString(String hex) {
        if (hex == null) {
            throw new NullPointerException();
        }

        hex = hex.toUpperCase();

        List<Byte> resultList = new ArrayList<Byte>();
        for (int i = 0; i < hex.length() - 1; i++) {
            char h = hex.charAt(i);
            char g = hex.charAt(i + 1);
            if ((h >= '0' && h <= '9') || (h >= 'A' && h <= 'F')) {
                if ((g >= '0' && g <= '9') || (g >= 'A' && g <= 'F')) {
                    // h g 均有效
                    byte hi = fromHexChar(h);
                    byte low = fromHexChar(g);
                    byte b = (byte) ((((hi & 0xFF) << 4) + (low & 0xFF)) & 0xFF);
                    resultList.add(b);
                }
                // 无论 g 是否有效，均跳过 g
                i++;
            }
        }
        byte[] result = new byte[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    /**
     * 把字节数组转换成 16 进制字符串 <br />
     * 例如：
     * 
     * <pre>
     * byte[] param = {0x01, 0x02, 0x03, 0x31, 0x32, 0x33};
     * String actual = ByteUtils.bytesToHexString(param);
     * // actual = "010203313233";
     * </pre>
     *
     * @param bytes
     * @return
     */
    public static final String toHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String sTemp;
        for (int i = 0; i < bytes.length; i++) {
            sTemp = Integer.toHexString(0xFF & bytes[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }
}
