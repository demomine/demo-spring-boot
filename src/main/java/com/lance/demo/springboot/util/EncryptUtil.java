package com.lance.demo.springboot.util;

import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

/**
 * Created by perdonare on 2016/11/2.
 */
public class EncryptUtil {
    public final static String RSA_CHIPER = "RSA/ECB/PKCS1Padding";
    public final static int ENCRYPT_KEYSIZE = 117;
    public static String base64Encode(String str) throws UnsupportedEncodingException {
        return new BASE64Encoder().encode(str.getBytes("UTF-8"));
    }

    public static PublicKey loadPublicKey(String path) throws Exception{
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        Certificate c = cf.generateCertificate(EncryptUtil.class.getClassLoader().getResourceAsStream(path));
        return c.getPublicKey();
    }

    public static PrivateKey loadPrivateKey(String path,String password) throws Exception{
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(EncryptUtil.class.getClassLoader().getResourceAsStream(path),password.toCharArray());
        Enumeration<String> aliases = keyStore.aliases();
        if (aliases.hasMoreElements()) {
            return (PrivateKey)keyStore.getKey(aliases.nextElement(), password.toCharArray());
        }
        throw new Exception("no key alias found");
    }

    public static String encrypt(String text, PublicKey publicKey) throws Exception {
        assert text != null;
        byte[] srcData = text.getBytes();
        final Cipher cipher = Cipher.getInstance(RSA_CHIPER);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 分段加密
        int blockSize = ENCRYPT_KEYSIZE;
        byte[] decryptData = null;

        for (int i = 0; i < srcData.length; i += blockSize) {
            byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
            decryptData = addAll(decryptData, doFinal);
        }
        if (decryptData == null) {
            throw new Exception("");
        }
        return byte2Hex(decryptData);
    }

    private static String byte2Hex(byte[] srcBytes) {
        StringBuilder hexRetSB = new StringBuilder();
        for (byte b : srcBytes) {
            String hexString = Integer.toHexString(0x00ff & b);
            hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
        }
        return hexRetSB.toString();
    }

    private static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        byte[] subarray = new byte[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    private static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return array2.clone();
        } else if (array2 == null) {
            return array1.clone();
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }
}
