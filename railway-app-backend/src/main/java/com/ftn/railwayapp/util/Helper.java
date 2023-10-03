package com.ftn.railwayapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Random;


public class Helper {

    private static final Random random = new Random();

    public static boolean checkStartTimeAfterCurrent(LocalDateTime startTime) {

        return startTime.isAfter(LocalDateTime.now());
    }

    public static boolean passwordsDontMatch(String password, String confirmationPassword){

        return !password.equals(confirmationPassword);
    }

    public static boolean oldPasswordsMatch(String password, String hashPassword) {
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, hashPassword);
    }

    public static String generateBalanceAccountNumber() {
        return String.valueOf((long) (Math.pow(10, 12) + random.nextDouble() * Math.pow(10, 12)));
    }

    public static int generateSecurityCode() {
        return (int)(Math.random() * (Constants.MAX_SECURITY_NUM - Constants.MIN_SECURITY_NUM + 1) + Constants.MIN_SECURITY_NUM);
    }

    public static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(Constants.CHARACTERS.length());
            char randomChar = Constants.CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public static String getHash(String password) {
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static String generateHashForURL(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            String base64Hash = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
            return base64Hash;
        } catch (NoSuchAlgorithmException e) {
            // Handle exception
            return null;
        }
    }

}
