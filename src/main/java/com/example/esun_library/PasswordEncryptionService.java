package com.example.esun_library;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class PasswordEncryptionService {

    // 生成鹽值的長度
    private static final int SALT_LENGTH = 16;

    // 生成鹽值的方法
    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    // 將密碼和鹽值進行結合並進行雜湊運算
    public String encryptPassword(String password) {
        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);
        return bytesToHex(salt) + ":" + bytesToHex(hash);
    }

    // 驗證用戶提供的密碼是否正確
    public boolean verifyPassword(String password, String storedPassword) {
        String[] parts = storedPassword.split(":");
        byte[] salt = hexToBytes(parts[0]);
        byte[] storedHash = hexToBytes(parts[1]);
        byte[] hash = hashPassword(password, salt);
        return MessageDigest.isEqual(storedHash, hash);
    }

    // 將密碼和鹽值進行雜湊運算
    private byte[] hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt);
            return digest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 將 byte 陣列轉換為十六進制表示
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // 將十六進制字符串轉換為 byte 陣列
    private byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}