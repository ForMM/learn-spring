package com.example.demo.util.encrpyt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.enums.HashType;

public class FileHashUtil {
	private static Logger logger = LoggerFactory.getLogger(FileHashUtil.class);
	private FileHashUtil() {}
	
	/**
	 * 根据文件路径获取对应哈希值
	 * @param fileName
	 * @param type (MD5,SHA-1,SHA-256)
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getFileHash(String fileName,String type) throws IOException, NoSuchAlgorithmException {
		InputStream inputStream = new FileInputStream(fileName);
		byte[] buffer = new byte[1024];
		int byteRead = 0;
		
		MessageDigest messageDigest = MessageDigest.getInstance(type);
		while((byteRead = inputStream.read(buffer)) != -1) {
			messageDigest.update(buffer, 0, byteRead);
		}
		inputStream.close();
		return Hex.encodeHexString(messageDigest.digest());
	}
	
	/**
	 * 获取流的哈希值
	 * @param inputStream
	 * @param type (MD5,SHA-1,SHA-256)
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public static String getFileHashFromIO(InputStream inputStream,String type) throws NoSuchAlgorithmException, IOException {
		byte[] buffer = new byte[1024];
		int byteRead = 0;
		
		MessageDigest messageDigest = MessageDigest.getInstance(type);
		while((byteRead = inputStream.read(buffer)) != -1) {
			messageDigest.update(buffer, 0, byteRead);
		}
		inputStream.close();
		return Hex.encodeHexString(messageDigest.digest());
	}
	
	
	public static void main(String[] args) {
		String path = "/Users/hayashika/Desktop/软考/企业信息化战略与实施.docx";
		try {
			String fileHash = getFileHash(path,HashType.MD5.getValue());
			logger.info("file md5 hash:{},length:{}",fileHash,fileHash.length());
			
			fileHash = getFileHash(path,HashType.SHA1.getValue());
			logger.info("file sha1 hash:{},length:{}",fileHash,fileHash.length());
			
			fileHash = getFileHash(path,HashType.SHA256.getValue());
			logger.info("file sha256 hash:{},length:{}",fileHash,fileHash.length());
			
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException",e);
		} catch (IOException e) {
			logger.error("IOException",e);
		}
	}
}
