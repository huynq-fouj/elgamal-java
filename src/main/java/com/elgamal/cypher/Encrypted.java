package com.elgamal.cypher;

import java.io.Serializable;
import java.math.BigInteger;

public class Encrypted implements Serializable {

	private static final long serialVersionUID = 1L;
	private String encryptedText;
	private String hashPrivateKey;
	private String hashMessage;
	private BigInteger q;

	public Encrypted() {
		this.encryptedText = "";
		this.hashPrivateKey = "";
		this.q = new BigInteger("0");
	}

	public Encrypted(String encryptedText, String hashPrivateKey, BigInteger q) {
		super();
		this.encryptedText = encryptedText;
		this.hashPrivateKey = hashPrivateKey;
		this.q = q;
	}

	public String getEncryptedText() {
		return encryptedText;
	}

	public String getHashPrivateKey() {
		return hashPrivateKey;
	}

	public String getHashMessage() {
		return hashMessage;
	}

	public BigInteger getQ() {
		return q;
	}

	public void setEncryptedText(String encryptedText) {
		this.encryptedText = encryptedText;
	}

	public void setHashPrivateKey(String hashPrivateKey) {
		this.hashPrivateKey = hashPrivateKey;
	}

	public void setHashMessage(String hashMessage) {
		this.hashMessage = hashMessage;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}

}
