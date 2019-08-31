package com.example.demo;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.util.encrpyt.sm.SM2Util;

@SpringBootTest
public class SMtest {
	
	private static Logger logger = LoggerFactory.getLogger(SMtest.class);

	public static final byte[] SRC_DATA = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
	public static final byte[] SRC_DATA_16B = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
	public static final byte[] SRC_DATA_24B = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5,
			6, 7, 8};
	public static final byte[] SRC_DATA_32B = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5,
			6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
	public static final byte[] WITH_ID = new byte[]{1, 2, 3, 4};


	@Test
	public void testSignAndVerify() {
		try {
			AsymmetricCipherKeyPair keyPair = SM2Util.generateKeyPairParameter();
			ECPrivateKeyParameters priKey = (ECPrivateKeyParameters) keyPair.getPrivate();
			ECPublicKeyParameters pubKey = (ECPublicKeyParameters) keyPair.getPublic();

			System.out.println("Pri Hex:" + ByteUtils.toHexString(priKey.getD().toByteArray()).toUpperCase());
			System.out.println(
					"Pub X Hex:" + ByteUtils.toHexString(pubKey.getQ().getAffineXCoord().getEncoded()).toUpperCase());
			System.out.println(
					"Pub Y Hex:" + ByteUtils.toHexString(pubKey.getQ().getAffineYCoord().getEncoded()).toUpperCase());
			System.out.println("Pub Point Hex:" + ByteUtils.toHexString(pubKey.getQ().getEncoded(false)).toUpperCase());

			String aa = "hhhhhhhhh爱情";
			byte[] sign = SM2Util.sign(priKey, WITH_ID, aa.getBytes());
			System.out.println("SM2 sign with withId result:\n" + ByteUtils.toHexString(sign));
			byte[] rawSign = SM2Util.decodeDERSM2Sign(sign);
			sign = SM2Util.encodeSM2SignToDER(rawSign);
			System.out.println("SM2 sign with withId result:\n" + ByteUtils.toHexString(sign));
			boolean flag = SM2Util.verify(pubKey, WITH_ID, aa.getBytes(), sign);
			if (!flag) {
				Assert.fail("verify failed");
			}

			sign = SM2Util.sign(priKey, aa.getBytes());
			System.out.println("SM2 sign without withId result:\n" + ByteUtils.toHexString(sign));
			flag = SM2Util.verify(pubKey, aa.getBytes(), sign);
			if (!flag) {
				Assert.fail("verify failed");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			Assert.fail();
		}
	}
	
	
	@Test
    public void testEncryptAndDecrypt() {
        try {
            AsymmetricCipherKeyPair keyPair = SM2Util.generateKeyPairParameter();
            ECPrivateKeyParameters priKey = (ECPrivateKeyParameters) keyPair.getPrivate();
            ECPublicKeyParameters pubKey = (ECPublicKeyParameters) keyPair.getPublic();

            System.out.println("Pri Hex:"
                + ByteUtils.toHexString(priKey.getD().toByteArray()).toUpperCase());
            System.out.println("Pub X Hex:"
                + ByteUtils.toHexString(pubKey.getQ().getAffineXCoord().getEncoded()).toUpperCase());
            System.out.println("Pub X Hex:"
                + ByteUtils.toHexString(pubKey.getQ().getAffineYCoord().getEncoded()).toUpperCase());
            System.out.println("Pub Point Hex:"
                + ByteUtils.toHexString(pubKey.getQ().getEncoded(false)).toUpperCase());

            String aa = "hhhhhhhhh爱情";
            byte[] encryptedData = SM2Util.encrypt(pubKey, aa.getBytes());
            logger.info("SM2加密后:\n{}",ByteUtils.toHexString(encryptedData));
            byte[] decryptedData = SM2Util.decrypt(priKey, encryptedData);
            String decry = new String(decryptedData);
            logger.info("SM2解密后:{}",decry);
            if (!decry.equals(decry)) {
                Assert.fail();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail();
        }
    }

	

}
