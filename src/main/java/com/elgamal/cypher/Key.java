package com.elgamal.cypher;

import java.io.Serializable;
import java.math.BigInteger;

public class Key implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PublicKey publicKey;
	private BigInteger privateKey;

	public Key() {
	}

	public Key(PublicKey publicKey, BigInteger privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public BigInteger getPrivateKey() {
		return privateKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public void setPrivateKey(BigInteger privateKey) {
		this.privateKey = privateKey;
	}

}
