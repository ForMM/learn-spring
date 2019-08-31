package com.example.demo.util.encrpyt.sm;

import java.util.Arrays;

import org.bouncycastle.crypto.digests.SM3Digest;

public class SM3Util {
	
	private SM3Util() {}
	
	/**
	 * sm3获取哈希
	 * @param params
	 * @return
	 */
	public static byte[] hash(byte[] params) {
		SM3Digest digest = new SM3Digest();
		digest.update(params,0,params.length);
		byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
	}
	
	/**
	 * 简单验签
	 * @param params
	 * @param sm3Hash
	 * @return
	 */
	public static boolean verify(byte[] params, byte[] sm3Hash) {
        byte[] newHash = hash(params);
        if (Arrays.equals(newHash, sm3Hash)) {
            return true;
        } else {
            return false;
        }
    }
	
}
