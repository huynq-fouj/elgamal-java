package com.elgamal.cypher;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class Elgamal {

	private static final SecureRandom random = new SecureRandom();
    private BigInteger q; // Số nguyên lớn q
    private BigInteger a; // Số căn nguyên thủy của q
    private BigInteger y; // Khóa công khai
    private BigInteger x; // Khóa bí mật

    public Elgamal(int bitLength) {
        this.q = BigInteger.probablePrime(bitLength, random);
        this.a = new BigInteger(bitLength - 1, random);
        this.x = new BigInteger(bitLength - 2, random);

        // Tính toán y = a^x mod q
        this.y = a.modPow(x, q);
    }

    public String encrypt(String message) {
        BigInteger k = new BigInteger(q.bitLength() - 1, random);
        //C1 = a ^ k mod q
        BigInteger C1 = a.modPow(k, q);
        BigInteger M = new BigInteger(message.getBytes(StandardCharsets.UTF_8));
        //K = y ^ k mod q
        BigInteger K = y.modPow(k, q);
        //C2 = K * M mod q
        BigInteger C2 = K.multiply(M).mod(q);
        return C1.toString() + ":" + C2.toString();
    }

    public String decrypt(String encryptedMessage) {
        String[] parts = encryptedMessage.split(":");
        BigInteger C1 = new BigInteger(parts[0]);
        BigInteger C2 = new BigInteger(parts[1]);
        //K = C1 ^ x mod q;
        BigInteger K = C1.modPow(x, q);
        //M = C2 * K ^ -1 mod q
        BigInteger M = C2.multiply(K.modInverse(q)).mod(q);
        return new String(M.toByteArray(), StandardCharsets.UTF_8);
    }
    
    public static void main(String[] args) {
		Elgamal e = new Elgamal(512);
		String M = "Mã hóa Elgamal";
		String cypher = e.encrypt(M);
		System.out.println("Plain: " + M);
		System.out.println("Encode: " + cypher);
		System.out.println("Decode: " + e.decrypt(cypher));
		
	}
	
}
