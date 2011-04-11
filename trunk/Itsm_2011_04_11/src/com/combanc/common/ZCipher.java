package com.combanc.common;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;

import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.jce.provider.JCERSACipher;

/**
 * ������Ϊ�˱���ͨ��JCE��getIntance()�෽����̬�������������(Tomcat�¿��ܻ���ThreadDeath����)
 */

public class ZCipher extends JCERSACipher {
	public ZCipher() {
		super(new PKCS1Encoding(new RSABlindedEngine()));
	}

	public void init(int mode, Key key) {
		try {
			engineInit(mode, key, new SecureRandom());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public int doFinal(byte[] b1, int i1, int i2, byte[] b2, int i3) throws Exception {
		return engineDoFinal(b1, i1, i2, b2, i3);
	}
}
