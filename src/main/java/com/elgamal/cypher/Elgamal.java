package com.elgamal.cypher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
public class Elgamal {

	private Elgamal() {}

    public static String encrypt(String message, PublicKey key, BigInteger k) {
    	BigInteger q = key.getQ();
    	BigInteger a = key.getA();
    	BigInteger y = key.getY();
        // C1 = a ^ k mod q
        BigInteger c1 = a.modPow(k, q);
        // K = y ^ k mod q
        BigInteger K = y.modPow(k, q);
		String c2 = calculateC2(message, q, K);
		return Base64.getEncoder().encodeToString(compressData(c1.toString() + ":" + c2));
    }

	private static String calculateC2(String message, BigInteger q, BigInteger K) {
		StringBuilder out = new StringBuilder();
		// Mã hóa từng ký tự và nối lại
		for(int i = 0; i < message.length(); i++) {
			char ci = message.charAt(i);
			BigInteger m = BigInteger.valueOf(ci);
			// C2 = K * M mod q
			BigInteger c2 = K.multiply(m).mod(q);
    		out.append(c2.toString());
    		if(i < message.length() - 1) out.append(".");
    	}
		return out.toString();
	}

    public static String decryptCharacter(BigInteger c1, BigInteger c2, BigInteger x, BigInteger q) {
        // K = C1 ^ x mod q
        BigInteger k = c1.modPow(x, q);
        // M = C2 * K ^ -1 mod q
        BigInteger m = c2.multiply(k.modInverse(q)).mod(q);
		return ((char)(m.intValue())) + "";
    }
    
    public static String decrypt(String code, BigInteger x, BigInteger q) {
		byte[] encryptedBytes = Base64.getDecoder().decode(code);
		String encrypted = decompressData(encryptedBytes);
    	String[] arr = encrypted.split(":");
		BigInteger c1 = new BigInteger(arr[0]);
		String strC2 = arr[1];
    	StringBuilder out = new StringBuilder();
		String[] arrC2 = strC2.split("\\.");
    	for(int i = 0; i < arrC2.length; i++) {
    		try {
    			BigInteger c2 = new BigInteger(arrC2[i]);
    			String m = decryptCharacter(c1, c2, x, q);
    			if(m.length() > 1) m = m.substring(1);
    			out.append(m);
    		} catch(Exception e) {
    			out.append("?");
    		}
    	}
    	return out.toString();
		//return decompressData(out.toString());
    }


	private static byte[] compressData(String data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
			gzip.write(data.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	
	private static String decompressData(byte[] compressedData) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (GZIPInputStream gzip = new GZIPInputStream(new ByteArrayInputStream(compressedData))) {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = gzip.read(buffer)) > 0) {
				baos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(baos.toByteArray(), StandardCharsets.UTF_8);
	}
	
}
