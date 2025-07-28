package com.minhphung.taskmanagementsystem.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String md5Encryption(String input) {
        try {
            //creates a md5 algo instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            //converts input string -> byte array and compute the hash
            byte[] digest = md.digest(input.getBytes());

            // Convert byte array -> hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found");
        }
    }
}