package com.elgamal.cypher;

import java.io.Serializable;
import java.math.BigInteger;

public class PublicKey implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigInteger q;
	private BigInteger a;
	private BigInteger y;

	public PublicKey(BigInteger q, BigInteger a, BigInteger y) {
		super();
		this.q = q;
		this.a = a;
		this.y = y;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getA() {
		return a;
	}

	public BigInteger getY() {
		return y;
	}

	public void setQ(BigInteger q) {
		this.q = q;
	}

	public void setA(BigInteger a) {
		this.a = a;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

}
