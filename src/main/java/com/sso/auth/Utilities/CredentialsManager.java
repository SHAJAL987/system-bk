package com.sso.auth.Utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class CredentialsManager {
    private static BCryptPasswordEncoder encoder;
    public static String passwordEncoder(String password){
        encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(password);
    }

    public static SecretKey generateHmacSHA256Key(String baseSecret) {
        try {
            String algorithm = "HmacSHA256";
            SecretKeySpec keySpec = new SecretKeySpec(baseSecret.getBytes(StandardCharsets.UTF_8), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(keySpec);
            byte[] hmacSha256 = mac.doFinal(baseSecret.getBytes(StandardCharsets.UTF_8));
            return new SecretKeySpec(hmacSha256, algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC-SHA256 key", e);
        }
    }
}
