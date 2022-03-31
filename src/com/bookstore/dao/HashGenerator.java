package com.bookstore.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class HashGenerator {
	
	private HashGenerator() {
   	 
    }
 
    public static String generateMD5(String message) throws HashGenerationException {
        return hashString(message, "MD5");
    }
 
    private static String hashString(String message, String algorithm)
            throws HashGenerationException {
 
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));
            String hashedHex = DatatypeConverter.printHexBinary(hashedBytes);
          
            return hashedHex;
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new HashGenerationException(
                    "Could not generate hash from String", ex);
        }
    }
 
   

}
