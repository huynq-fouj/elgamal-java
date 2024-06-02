package com.elgamal.cypher;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class Elgamal {

	/**
	 * PublicKey : {q, a, Ya}
	 * @param message
	 * @param key
	 * @param k
	 * @return
	 */
    public static String encrypt(String message, PublicKey key, BigInteger k) {
    	BigInteger q = key.getQ();
    	BigInteger a = key.getA();
    	BigInteger y = key.getY();
        //C1 = a ^ k mod q
        BigInteger C1 = a.modPow(k, q);
        
        byte[] byteArray = message.getBytes(StandardCharsets.UTF_8);
        
        BigInteger M = new BigInteger(byteArray);
        
        //K = y ^ k mod q
        BigInteger K = y.modPow(k, q);
        //C2 = K * M mod q
        BigInteger C2 = K.multiply(M).mod(q);
        return C1.toString() + ":" + C2.toString();
    }

    public static String decrypt(BigInteger C1, BigInteger C2, BigInteger x, BigInteger q) {
        //K = C1 ^ x mod q;
        BigInteger K = C1.modPow(x, q);
        //M = C2 * K ^ -1 mod q
        BigInteger M = C2.multiply(K.modInverse(q)).mod(q);
        
        byte[] byteArray = M.toByteArray();
        
        return new String(byteArray, StandardCharsets.UTF_8);
    }
    
    
    public static String encryptV2(String message, PublicKey key, BigInteger k) {
    	StringBuilder out = new StringBuilder();
    	for(int i = 0; i < message.length(); i++) {
    		String ci = message.charAt(i) + "";
    		if(new BigInteger(ci.getBytes(StandardCharsets.UTF_8)).bitLength() > 7) ci = " " + ci;
    		out.append(encrypt(ci, key, k));
    		if(i < message.length() - 1) out.append(".");
    	}
    	return out.toString();
    }
    
    /**
     * Code Example: "12123:3424234.1234234:231323...."
     * @param code
     * @param x
     * @param q
     * @return
     */
    public static String decryptV2(String code, BigInteger x, BigInteger q) {
    	String[] arr = code.split("\\.");
    	StringBuilder out = new StringBuilder();
    	for(int i = 0; i < arr.length; i++) {
    		try {
    			String[] C = arr[i].split(":");
    			BigInteger C1 = new BigInteger(C[0]);
    			BigInteger C2 = new BigInteger(C[1]);
    			String m = decrypt(C1, C2, x, q);
    			if(m.length() > 1) m = m.substring(1);
    			out.append(m);
    		} catch(Exception e) {
    			out.append("?");
    		}
    	}
    	
    	return out.toString();
    }
	
}
