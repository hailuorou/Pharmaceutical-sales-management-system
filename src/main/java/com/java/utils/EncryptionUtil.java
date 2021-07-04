package com.java.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * 数据加密的工具类
 * 用于加密用户输入的密码保证用户密码在数据库中的安全性
 * 功能：输入用户的密码明文并以密码明文作为种子生成密钥加密密码后返回
 * */
public class EncryptionUtil {


    public String getEncryption(String key) {

        String cipherText = null;
        try {
            //创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");

            //用户自己的密码作为种子生成密钥
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());

            //利用输入的密码作为随机数初始化出128位的key生产者
            kgen.init(128,random);

            //根据密文生产密钥
            SecretKey secretKey = kgen.generateKey();
            //将生成的密钥转化为AES专用密钥
            SecretKey aesKey = new SecretKeySpec(secretKey.getEncoded(), "AES");

            //创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            //初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE,aesKey);
            //进行加密
            byte[] keyBytes = cipher.doFinal(key.getBytes());
            cipherText = new BASE64Encoder().encode(keyBytes);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return cipherText;

    }
}
