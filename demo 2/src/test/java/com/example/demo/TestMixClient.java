package com.example.demo;

import com.fadada.lightapi.FDDSDKClient;
import com.fadada.lightapi.base.ReturnBase;
import com.fadada.lightapi.beans.DataSign.*;
import com.fadada.lightapi.beans.gencert.GenCertReq;
import com.fadada.lightapi.utils.RSAUtil;
import com.fadada.utils.crypt.CryptTool;
import com.fadada.utils.random.UUIDGenerator;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMixClient {


    GenCertReq genCertReq(){
        GenCertReq req = new GenCertReq();
        req.setClienttype(2);
        req.setEmail("linkeke_sea11555@163.com");
        req.setIdentno("");
        req.setIdenttype("0");
        req.setUsername("");
        req.setMobile("");
        return req;
    }

    PreSignDataReq createPreSignData(String origin,String resourceId){
        PreSignDataReq ino = new PreSignDataReq();
        ino.setCustomer_id("25521F8A4C90DDADC0EA2FCBFEB7626B");
        String base64Encode = CryptTool.base64Encode(origin.getBytes());
        System.out.println("base64Encode:"+base64Encode);
        ino.setData_hash(base64Encode);
        ino.setResource_id(resourceId);
//        ino.setSign_type("sha256");
//		ino.setSms_flag("1");
        System.out.println(ino.toString());
        return ino;
    }
    SmsDataSignReq smsDataSignReq(String tokenId){
        SmsDataSignReq ino = new SmsDataSignReq();
        ino.setSms_type("1");
        ino.setToken_id(tokenId);
        return ino;
    }

    PostSignDataReq postSignDataReq(String sms,String tokenId){
        PostSignDataReq ino = new PostSignDataReq();
        ino.setSms(sms);
        ino.setToken_id(tokenId);
        return ino;
    }
    GetSignatureCertReq getSignatureCertReq(String customerId){
        GetSignatureCertReq ino = new GetSignatureCertReq();
        ino.setCustomer_id(customerId);
        return ino;
    }

    VerifyDataSignReq verifyDataSign(String origin, String signedData){
        VerifyDataSignReq req = new VerifyDataSignReq();
        req.setCustomer_id("25521F8A4C90DDADC0EA2FCBFEB7626B");
        req.setData_hash(origin);
        req.setSigned_data(signedData);
        System.out.println(req.toString());
        return  req;
    }

    PreSignDataReq createSignData(String origin,String resourceId){
        PreSignDataReq ino = new PreSignDataReq();
        ino.setCustomer_id("25521F8A4C90DDADC0EA2FCBFEB7626B");
        ino.setData_hash(origin);
        ino.setResource_id(resourceId);
        ino.setSign_type("sha256");
        System.out.println(ino.toString());
        return ino;
    }

    @Test
    public void testDtaSign(){
//    	String aa = "2234234234";
//    	String base64Encode = CryptTool.base64Encode(aa.getBytes());
//    	System.out.println(base64Encode);

//		GenCertRsp gencert = new FDDSDKClient().gencert(genCertReq());
//		System.out.println(gencert.toString());

        //数据预签名提交
        String origin = "aaaaaaaaaaadfadfaf";
        String changedOrigin = "aaaaaaaaaaadfadfafddd";
        String base64Encode = CryptTool.base64Encode(origin.getBytes());
        String changedbase64Encode = CryptTool.base64Encode(changedOrigin.getBytes());
        String resourceId = UUIDGenerator.getUUID().toLowerCase();
//    	PreSignDataReq createPreSignData = createPreSignData(origin,resourceId);
//        ReturnBase preSignData = new FDDSDKClient().preSignData(createPreSignData);
//        String tokenId = "";
//        if(preSignData.checkReturn()) {
//        	tokenId = (String)preSignData.getData();
//            System.out.println("tokenId:"+tokenId);
//        }

        //静默签
        PreSignDataReq createSignData = createSignData(base64Encode,resourceId);
        String signedData="";
        ReturnBase signData = new FDDSDKClient().signData(createSignData);
        signedData = (String)signData.getData();
        System.out.println("signedData:"+signedData);

        //发送验证码
//		ReturnBase smsDataSign = new FDDSDKClient().smsDataSign(smsDataSignReq(tokenId));

        //确认签名
//    	String tokenId="595a441806034ef5a57627dc6354af50";
//		String signedData="";
//		ReturnBase postSignData = new FDDSDKClient().postSignData(postSignDataReq("313643",tokenId));
//		signedData = (String)postSignData.getData();
//		System.out.println("signedData:"+signedData);


//		VerifyDataSignReq req = verifyDataSign(base64Encode, signedData);
//		ReturnBase returnBase = new FDDSDKClient().verifyDataSign(req);
//		System.out.println("returnBase:"+returnBase.getCode()+","+returnBase.getMsg());
        //获取证书
        ReturnBase signatureCert = new FDDSDKClient().getSignatureCert(getSignatureCertReq("25521F8A4C90DDADC0EA2FCBFEB7626B"));
        if(signatureCert.checkReturn()) {
            String signature = (String)signatureCert.getData();
            System.out.println("signature:"+signature);

            //验签
            boolean testVerifySign = testVerifySign(signature,base64Encode,signedData);
            System.out.println("验签结果:"+testVerifySign);
        }
    }

    boolean testVerifySign(String cert,String orignStr,String signedData) {
        try {
            // 读取证书文件
//			String cert = "MIIFVjCCAz6gAwIBAgIMHM8AAAAAAAAAAhl9MA0GCSqGSIb3DQEBCwUAMGExITAfBgNVBAMMGENsb3VkIFNlY3VyaXR5IFQgUlNBIEVDQTEvMC0GA1UECgwmQ2xvdWQgU2VjdXJpdHkgQ2VydGlmaWNhdGlvbiBBdXRob3JpdHkxCzAJBgNVBAYTAkNOMB4XDTE5MTEwMzE2MDAwMFoXDTE5MTEwNTE1NTk1OVowgdoxCzAJBgNVBAYTAkNOMQ8wDQYDVQQIHgZef04cdwExDzANBgNVBAceBm3xVzNeAjEfMB0GA1UECx4WbNVZJ1knf1F+3HnRYoBnCZZQUWxT+DEnMCUGCSqGSIb3DQEJARYYbGlua2VrZV9zZWExMTU1NUAxNjMuY29tMV8wXQYDVQQDHlYAMgBAAFoAQAA3ADgAZAA0ADgAMgA5AGUAOQAzADIAYQA0ADUANABjADgANwA5AGEAYwA3ADYAZgBhADEANABmAGQAZQA3AGMAQAAzADcANQA0ADQANDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAJFb0IicIif2FxREpED/NEFnU56u/JIKfEDSaSZ3AL39Sapp1r0k4eziMrp0Rh6kENxxBDbaUZr6Ephg+oNPV8I8OPWSl4PDF971XMVEDIJtIJowEkT2BahFQWYaSeN4jyifkNW7Kw1pTTjW0RSWyFQEPZo29eZpX465ZtxlRgrDPXg288HY8GhWnBtpLaIHw8hZEaBZbFyPwZCFscMwIIhI6r+FrZEAyWj+dorjpZ5oOzE0yFf/7IS83bdNJPq0/oLd+u4uHgW1GF7yWd4dQMAcPxia8bqgYgC4SHHC43CKXRJuYeQe0OrBlW4XBxYPCpsIbeEnf8DOVxzcYTuyLa8CAwEAAaOBkzCBkDARBglghkgBhvhCAQEEBAMCAIAwCwYDVR0PBAQDAgDAMCAGA1UdJQEB/wQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDAMBgNVHRMEBTADAQEAMB8GA1UdIwQYMBaAFPSzugcRSoZyXff/0W7m1PpwGS7rMB0GA1UdDgQWBBQc7eK1eRMR6ncA4Yl2fb1xFu9TLDANBgkqhkiG9w0BAQsFAAOCAgEAA+hLZZ5iD0uKtrk96YhcXiSOL8uPmcwN7QckAtgJiU5wuuYLaObAJ18ZxxlgNm1IxCkXvxXj+afaeUAcWpLKocYBB+iTPTozr14MtaaWD5fpyzFnxuYI+oClJ+XZ1XIVYojKMhvv6HXo2rSk7C+EFtL+cZJYZFkw60kijY+TrHytkDidy8nK5ocIJSeZPGSCNDkHeg0v3pwmWwVqv+SzYiUZXPiJwlZhjgNUpmjYleBuiBbjCOxyN++1FSn86ncoyhAJE07SX4ktGu0QIQxexT+P1OBlhDyd/7xKLi+vXpEPxEzrAF2GtevD8Iom8lnuNJGoH3iSsHXAAU1vGrPYOKJQAtIO3TBXdgr8ET/0pSCfvzXkjaE0AWQcfvMAjvRNzWdHcy/Rb+kl7NdSNZutNuMtTZJVFN/A5oYcxhyxUWjL32x/t7vXdsfUJEhnJ1lbbEYq1Kr8zsdcdWPMGdTyN0PVA9/F0B/qAmB3rSupacBOvJsUmj4mzo3PU6wBGs5e0QLpYDNeh6saE71wXZncgGFCohLciJBJ/6hiRxGTOUUhwYAg0Yin07/2CprRwZMr5S4Mo9eYzy1Iov4f3S7gf9kWmMdeINZzorRIRc3Ltw63STvY4zjoVb8Od51qadihSBUVE6dyqpq5ePj9N/0mBEh0Anox2NY660LFor6Xb/4=";
            InputStream inStream = new ByteArrayInputStream(CryptTool.base64Decode(cert));
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
            String publicKeyStr = CryptTool.base64Encode(encoded);
            System.out.println("证书里公钥："+publicKeyStr);

//			String orignStr = "1423424234324";
//			String signedData = "A1V/bur/jhSS3fHWGwMt+NQX1Wu/EN1a2PlTzIx4aiN6Umm6augBfLBWTel6xeE+QQreYxabMckZ\\r\\n5Zq/4z6yNg1bREpjOcPgp9eEtIzPCmE9HkeIV+ZOHHIJyMU0CJRC2/tGcqkS337lTON3oMourGOF\\r\\na9alU84+BdpP17ghGTfFC9nLAvfC4m5fqi9fKBVuZI21WtazvimevspXxYB4kkEpfiLQ2d3lZIna\\r\\nV65NmyiQNSFTWbV7qCNJWGd74XbW2ZozHPoyJGVx3lShWpw1sN3dxfJCGWZrlEHuvS+09QsJ4wqu\\r\\nt/QHATF+nWwPZSmbHlKbeU2fRmiP04h7NiHsTQ==";

            return RSAUtil.checkSign(orignStr, signedData, publicKeyStr);
        } catch (Exception e) {
            System.out.println("解析证书出错！");
            return false;
        }
    }

}
