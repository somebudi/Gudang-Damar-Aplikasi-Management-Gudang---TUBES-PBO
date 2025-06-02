package com.gudangdamar.main.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {

    // Generate salt baru (dipakai saat register)
    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash password dengan salt menggunakan SHA-256
    public static String hashPassword(String password, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Base64.getDecoder().decode(salt));  // decode salt base64 dulu
        byte[] hashed = md.digest(password.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(hashed);
    }

    // Verifikasi password input sama dengan hash tersimpan
    public static boolean verifyPassword(String inputPassword, String storedSalt, String storedHash) {
        try {
            String inputHash = hashPassword(inputPassword, storedSalt);
            return inputHash.equals(storedHash);
        } catch (Exception e) {
            return false;
        }
    }
}
