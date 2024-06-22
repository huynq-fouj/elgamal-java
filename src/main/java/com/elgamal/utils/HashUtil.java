package com.elgamal.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Logger;

public class HashUtil {

	private HashUtil() {}
	
	public static String createBase64Hash(String str) {
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(HashUtil.class.getName()).warning(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Không thể băm chuỗi văn bản!");
        }
	}
	
	public static boolean verify(String base64Hash, String str) {
		return createBase64Hash(str).equals(base64Hash);
	}
	
}
