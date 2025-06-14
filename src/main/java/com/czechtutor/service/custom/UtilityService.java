package com.czechtutor.service.custom;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {
    public String MD5DateTimeHash(LocalDateTime dateTime) {
        // create hash value
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = dateTime.format(formatter);
        try {
            byte[] bytesOfMessage = formattedDateTime.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theMD5digest = md.digest(bytesOfMessage);
            String datetimeHash = Base64.getEncoder().encodeToString(theMD5digest);
            return datetimeHash;
        }catch(NoSuchAlgorithmException e) {
            System.out.println("Something is wrong");
            return formattedDateTime;
        }
    }
}