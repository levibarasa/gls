/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Stanley Mungai
 */
public class Encode {

    private KeySpec keySpec;
    private SecretKey key;
    private IvParameterSpec iv;
    private static final char[] map1 = new char[64];

    static {
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            map1[i++] = c;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            map1[i++] = c;
        }
        for (char c = '0'; c <= '9'; c++) {
            map1[i++] = c;
        }
        map1[i++] = '+';
        map1[i++] = '/';
    }
//Mapping table from Base64 characters to 6-bit nibbles.
    private static final byte[] map2 = new byte[128];

    static {
        for (int i = 0; i < map2.length; i++) {
            map2[i] = -1;
        }
        for (int i = 0; i < 64; i++) {
            map2[map1[i]] = (byte) i;
        }
    }

    /**
     * Encodes a string into Base64 format. No blanks or line breaks are
     * inserted.
     *
     * @param s a String to be encoded.
     * @return A String with the Base64 encoded data.
     */
    public static String encodeString(String s) {
        return new String(encode(s.getBytes()));
    }

    /**
     * Encodes a byte array into Base64 format. No blanks or line breaks are
     * inserted.
     *
     * @param in an array containing the data bytes to be encoded.
     * @return A character array with the Base64 encoded data.
     */
    public static char[] encode(byte[] in) {
        return encode(in, in.length);
    }

    /**
     * Encodes a byte array into Base64 format. No blanks or line breaks are
     * inserted.
     *
     * @param in an array containing the data bytes to be encoded.
     * @param iLen number of bytes to process in <code>in</code>.
     * @return A character array with the Base64 encoded data.
     */
    public static char[] encode(byte[] in, int iLen) {
        int oDataLen = (iLen * 4 + 2) / 3;       // output length without padding
        int oLen = ((iLen + 2) / 3) * 4;         // output length including padding
        char[] out = new char[oLen];
        int ip = 0;
        int op = 0;
        while (ip < iLen) {
            int i0 = in[ip++] & 0xff;
            int i1 = ip < iLen ? in[ip++] & 0xff : 0;
            int i2 = ip < iLen ? in[ip++] & 0xff : 0;
            int o0 = i0 >>> 2;
            int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
            int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
            int o3 = i2 & 0x3F;
            out[op++] = map1[o0];
            out[op++] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : '=';
            op++;
            out[op] = op < oDataLen ? map1[o3] : '=';
            op++;
        }
        return out;
    }

    /**
     * Decodes a string from Base64 format.
     *
     * @param s a Base64 String to be decoded.
     * @return A String containing the decoded data.
     * @throws IllegalArgumentException if the input is not valid Base64 encoded
     * data.
     */
    public static String decodeString(String s) {
        return new String(decode(s));
    }

    /**
     * Decodes a byte array from Base64 format.
     *
     * @param s a Base64 String to be decoded.
     * @return An array containing the decoded data bytes.
     * @throws IllegalArgumentException if the input is not valid Base64 encoded
     * data.
     */
    public static byte[] decode(String s) {
        return decode(s.toCharArray());
    }

    /**
     * Decodes a byte array from Base64 format. No blanks or line breaks are
     * allowed within the Base64 encoded data.
     *
     * @param in a character array containing the Base64 encoded data.
     * @return An array containing the decoded data bytes.
     * @throws IllegalArgumentException if the input is not valid Base64 encoded
     * data.
     */
    public static byte[] decode(char[] in) {
        int iLen = in.length;
        if (iLen % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (iLen > 0 && in[iLen - 1] == '=') {
            iLen--;
        }
        int oLen = (iLen * 3) / 4;
        byte[] out = new byte[oLen];
        int ip = 0;
        int op = 0;
        while (ip < iLen) {
            int i0 = in[ip++];
            int i1 = in[ip++];
            int i2 = ip < iLen ? in[ip++] : 'A';
            int i3 = ip < iLen ? in[ip++] : 'A';
            if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int b0 = map2[i0];
            int b1 = map2[i1];
            int b2 = map2[i2];
            int b3 = map2[i3];
            if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int o0 = (b0 << 2) | (b1 >>> 4);
            int o1 = ((b1 & 0xf) << 4) | (b2 >>> 2);
            int o2 = ((b2 & 3) << 6) | b3;
            out[op++] = (byte) o0;
            if (op < oLen) {
                out[op++] = (byte) o1;
            }
            if (op < oLen) {
                out[op++] = (byte) o2;
            }
        }
        return out;
    }

    public Encode(String keyString, String ivString) {
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest(Base64.decodeBase64(keyString.getBytes("utf-8")));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            for (int j = 0, k = 16; j < 8;) {
                keyBytes[k++] = keyBytes[j++];
            }
            keySpec = new DESedeKeySpec(keyBytes);
            key = SecretKeyFactory.getInstance("DESede").generateSecret(keySpec);
            iv = new IvParameterSpec(ivString.getBytes());
        } catch (UnsupportedEncodingException asd) {
            System.out.println(asd.getMessage());
        } catch (InvalidKeyException asd) {
            System.out.println(asd.getMessage());
        } catch (NoSuchAlgorithmException asd) {
            System.out.println(asd.getMessage());
        } catch (InvalidKeySpecException asd) {
            System.out.println(asd.getMessage());
        }
    }

    public String encrypt(String value) {
        try {
            Cipher ecipher = Cipher.getInstance("DESede/CBC/PKCS5Padding", "SunJCE");
            ecipher.init(Cipher.ENCRYPT_MODE, key, iv);
            if (value == null) {
                return null;
            }
            //Encode the String into Bytes Using utf-8
            byte[] utf8 = value.getBytes("UTF-8");
            //Encrypt
            byte[] enc = ecipher.doFinal(utf8);
            //Encode Bytes to Base64 to get a String
            return new String(Base64.encodeBase64(enc), "UTF-8");

        } catch (UnsupportedEncodingException asd) {
            System.out.println(asd.getMessage());
        } catch (InvalidAlgorithmParameterException asd) {
            System.out.println(asd.getMessage());
        } catch (InvalidKeyException asd) {
            System.out.println(asd.getMessage());
        } catch (NoSuchAlgorithmException asd) {
            System.out.println(asd.getMessage());
        } catch (NoSuchProviderException asd) {
            System.out.println(asd.getMessage());
        } catch (BadPaddingException asd) {
            System.out.println(asd.getMessage());
        } catch (IllegalBlockSizeException asd) {
            System.out.println(asd.getMessage());
        } catch (NoSuchPaddingException asd) {
            System.out.println(asd.getMessage());
        }
        return null;
    }

    public String decrypt(String value) {
        try {
            Cipher dcipher = Cipher.getInstance("DESede/CBC/PKCS5Padding", "SunJCE");
            dcipher.init(Cipher.DECRYPT_MODE, key, iv);
            if (value == null) {
                return null;
            }
            //Decode Base 64 to get Bytes
            byte[] dec = Base64.decodeBase64(value.getBytes());
            //Decrypt
            byte[] utf8 = dcipher.doFinal(dec);
            //Decode String using utf8
            return new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException asd) {
            System.out.println(asd.getMessage());
        } catch (InvalidAlgorithmParameterException asd) {
            System.out.println(asd.getMessage());
        } catch (InvalidKeyException asd) {
            System.out.println(asd.getMessage());
        } catch (NoSuchAlgorithmException asd) {
            System.out.println(asd.getMessage());
        } catch (NoSuchProviderException asd) {
            System.out.println(asd.getMessage());
        } catch (BadPaddingException asd) {
            System.out.println(asd.getMessage());
        } catch (IllegalBlockSizeException asd) {
            System.out.println(asd.getMessage());
        } catch (NoSuchPaddingException asd) {
            System.out.println(asd.getMessage());
        }
        return null;
    }

//Dummy constructor.
    public Encode() {
    }

    public static String generateUserKey(String username, String password) {
        return username.substring(0, 2) + password.substring(0, 4);
    }

    public static String generateUserIV(String username, String password) {
        return username.substring(0, 3) + password.substring(0, 5);
    }

    public static String EncodeUserPassword(String username, String password) {
        Encode enc = new Encode(generateUserKey(username, password), generateUserIV(username, password));
        return enc.encrypt(password);
    }

    public static String DecodeUserPassword(String username, String password, String encpass) {
        Encode enc = new Encode(generateUserKey(username, password), generateUserIV(username, password));
        return enc.decrypt(encpass);
    }
}
