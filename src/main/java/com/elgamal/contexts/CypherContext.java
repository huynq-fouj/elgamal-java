package com.elgamal.contexts;

import java.math.BigInteger;

import com.elgamal.cypher.PublicKey;

public class CypherContext {

	private static PublicKey publicKey;
	private static BigInteger privateKey;
	
	public static void setPublicKey(PublicKey key) {
		publicKey = key;
	}
	
	public static void setPublicKey(BigInteger q, BigInteger a, BigInteger y) {
		publicKey = new PublicKey(q, a, y);
	}
	
	public static PublicKey getPublicKey() {
		return publicKey;
	}
	
	public static void setPrivateKey(BigInteger key) {
		privateKey = key;
	}
	
	public static BigInteger getPrivateKey() {
		return privateKey;
	}
	
}
