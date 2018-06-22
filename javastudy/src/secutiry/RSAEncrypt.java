package secutiry;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;

/**
 * Created by Administrator on 2017/12/15.
 */
public class RSAEncrypt {
    //指定加密算法为RSA
    private static final String ALGORITHM = "RSA";
    //指定密钥长度
    private static final int KEY_SIZE = 1024;
    //指定私钥存放文件
    private static final String PRIVATE_KEY_FILE = "PrivateKey";
    //指定公钥存放文件
    private static final String PUBLIC_KEY_FILE = "PublicKey";

    /**
     * 生成密钥
     */
    private void generateKey() {
        try {
            //指定随机数源
            SecureRandom secureRandom = new SecureRandom();
            //为RSA算法创建一个KeyPairGenerator对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            //初始化KeyPairGenerator对象
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
            //生成密钥对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //获取私钥
            Key privateKey = keyPair.getPrivate();
            //获取公钥
            Key publicKey = keyPair.getPublic();
            //将私钥和公钥写入文件
            ObjectOutputStream privateKeyStream = new ObjectOutputStream(
                    new FileOutputStream(PRIVATE_KEY_FILE));
            ObjectOutputStream publicKeyStream = new ObjectOutputStream(
                    new FileOutputStream(PUBLIC_KEY_FILE));
            privateKeyStream.writeObject(privateKey);
            publicKeyStream.writeObject(publicKey);
            privateKeyStream.close();
            publicKeyStream.close();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密算法
     * @param plaintext  明文
     * @return  密文
     */
    public String encrypt(String plaintext){
        try {
            //读取文件获取公钥
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(PUBLIC_KEY_FILE));
            Key publicKey = (Key) inputStream.readObject();
            inputStream.close();
            //得到Cipher对象来实现RSA加密算法
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(plaintext.getBytes());
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(bytes);
        } catch (IOException | ClassNotFoundException | NoSuchPaddingException
                | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密算法
     * @param ciphertext  密文
     * @return  明文
     */
    public String decrypt(String ciphertext){
        try {
            //读取文件获取私钥
            ObjectInputStream inputStream = new ObjectInputStream(
                    new FileInputStream(PRIVATE_KEY_FILE));
            Key privateKey = (Key) inputStream.readObject();
            inputStream.close();
            //得到Cipher对象来实现RSA解密算法
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] bytes = base64Decoder.decodeBuffer(ciphertext);
            return new String(cipher.doFinal(bytes));
        } catch (IOException | ClassNotFoundException | NoSuchPaddingException
                | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        RSAEncrypt rsaEncrypt = new RSAEncrypt();
        rsaEncrypt.generateKey();
        String message = "Hello World";
        String ciphertext = rsaEncrypt.encrypt(message);
        System.out.println("ciphertext: " + ciphertext);

        String plaintext = rsaEncrypt.decrypt(ciphertext);
        System.out.println("plaintext: " + plaintext);
    }
}

