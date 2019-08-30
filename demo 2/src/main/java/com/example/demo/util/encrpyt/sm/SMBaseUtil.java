package com.example.demo.util.encrpyt.sm;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SMBaseUtil {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
}
