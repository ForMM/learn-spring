package com.example.demo;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class TestX509Cert {

	@Test
	public void TestX509Cert() {
		try {
			// 读取证书文件
			String cert = "MIIELzCCAxegAwIBAgIFEAMBCAQwDQYJKoZIhvcNAQEFBQAwWDELMAkGA1UEBhMCQ04xMDAuBgNVBAoTJ0NoaW5hIEZpbmFuY2lhbCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEXMBUGA1UEAxMOQ0ZDQSBURVNUIE9DQTEwHhcNMTYwODA5MDMxMjQ1WhcNMTcwODA5MDMxMjQ0WjBuMQswCQYDVQQGEwJDTjEMMAoGA1UEChMDQk9DMQ0wCwYDVQQLEwRZUVBUMRUwEwYDVQQLEwxJbmRpdmlkdWFsLTExKzApBgNVBAMMIjA1MUDliJjlo6vkvJ9AMTMyODIyMTk3ODA0MTkyMDE1QDEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCAZCDo7nRGwQFSHWfzPytZq/FwjvAK5STt0FmnsbxbDBSzlvXvWN9zr+JvH0H7PRwUQBRFynZf9EvzhhVXoCErAMmpiALO+PBp2lhMVp6PriI8JaPqx4HH1l7IM92hUkEwEckaTR8Q7ZKuOfYs1DgRCQpZKCXUmtt5+RvrJiIBehrDrYWsGVBh2N2gb9OEE5YcAMCBrmMKltiyznbzfLec5I8wrgJLnFEfrkAGoLZ/ZdqIk0T7Ma77bPFs84qjdmDevGjagHjF8JMGP36dhffE6ZHPvR6xQpFMFBPalXpMbGJ2RpFqcCtUp5IfOVDvwMR1ZDz6QT9OLbR/VdemlU9NAgMBAAGjgekwgeYwHwYDVR0jBBgwFoAUz3CdYeudfC6498sCQPcJnf4zdIAwSAYDVR0gBEEwPzA9BghggRyG7yoBATAxMC8GCCsGAQUFBwIBFiNodHRwOi8vd3d3LmNmY2EuY29tLmNuL3VzL3VzLTE0Lmh0bTA4BgNVHR8EMTAvMC2gK6AphidodHRwOi8vdWNybC5jZmNhLmNvbS5jbi9SU0EvY3JsNTQ2MC5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBSEvbyyHhIkbcN2gW0Acp0Ru9altjATBgNVHSUEDDAKBggrBgEFBQcDAjANBgkqhkiG9w0BAQUFAAOCAQEAjWGW7bHOian0pqVw2SsjVusIIjw5EIx+7JPaoJ3gK6HyYdmlmX5xeMvKb9awrZHKZHrdWvkpiVW3ym0LkdY5gdYcU+iEhr6vIFqMoV+KlFLo7cZvs1bl9aj4iRCj0sAvCot2xsTX6T46WabS+cJQpKU+nj2Rdume7G4F5/KZQ8LGAxQovDE2Ecav1NYDjVhjn+efkNYTshh4qSg+Zte3rrr7s8rMoCqBRPbaTg6NgyVcYslmqMdlDPkUjcLpndc2eqYgZ/wqoAKoS6H5C0YIq+KIBDZvFwqCWc0npOOldJvxbNhiW8y5O87RYwjfrKp1K5BcBJKTEQmF+76ZUGofXQ==";
			Base64.Decoder decoder = Base64.getDecoder();
			InputStream inStream = new ByteArrayInputStream(decoder.decode(cert));
			// 创建X509工厂类
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			// 创建证书对象
			X509Certificate oCert = (X509Certificate) cf
					.generateCertificate(inStream);

			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
			String info = null;
			// 获得证书版本
			info = String.valueOf(oCert.getVersion());
			System.out.println("证书版本:" + info);
			// 获得证书序列号
			info = oCert.getSerialNumber().toString(16);
			System.out.println("证书序列号:" + info);
			// 获得证书有效期
			Date beforedate = oCert.getNotBefore();
			info = dateformat.format(beforedate);
			System.out.println("证书生效日期:" + info);
			Date afterdate = oCert.getNotAfter();
			info = dateformat.format(afterdate);
			System.out.println("证书失效日期:" + info);
			// 获得证书主体信息
			info = oCert.getSubjectDN().getName();
			System.out.println("证书拥有者:" + info);
			// 获得证书颁发者信息
			info = oCert.getIssuerDN().getName();
			System.out.println("证书颁发者:" + info);
			// 获得证书签名算法名称
			info = oCert.getSigAlgName();
			System.out.println("证书签名算法:" + info);

			PublicKey publicKey = oCert.getPublicKey();
			byte[] encoded = publicKey.getEncoded();


		} catch (Exception e) {
			System.out.println("解析证书出错！");
			e.printStackTrace();
		}

	}

}
