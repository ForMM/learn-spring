package com.example.demo.util.storage;

import com.example.demo.util.storage.meituan.MeituanFOSServiceImpl;
import com.example.demo.util.storage.meituan.StorageService;
import com.fadada.storage.FossException;
import com.fadada.storage.FossObject;
import com.fadada.storage.FossObjectMetadata;
import com.fadada.storage.FossService;
import com.fadada.storage.impl.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.UUID;

public class FOSClient {
//	public static final String BUCKET_PDF = SystemConfig.getOssBucketPdf();
//	public static final String BUCKET_DOC = SystemConfig.getOssBucketDoc();

	private static Logger logger = LoggerFactory.getLogger(FOSClient.class);

	private static String OSS_FLAG = "";
	private static FossService fosService = null;
	private static FossService fosSrtpService = null;

	private static final String OSS_URL = "SystemConfig.getOssUrl()";
	private static final String ACCESS_KEY_ID = "SystemConfig.getOssIdentityId()";
	private static final String ACCESS_KEY_SECRET = "SystemConfig.getOssIdentitySecret()";
	private static final String OSS_REGION = "SystemConfig.getOssRegion()";
	private static final int EXPIRE_DATE = 1000 * 30 * 60;

	static{
		if ("ali".equals(OSS_FLAG)) {
			logger.info("==============ali===============");
			fosService = new AliyunFossServiceImpl(OSS_URL, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		} else if ("aws".equals(OSS_FLAG)) {
			logger.info("==============aws===============");
			fosService = new AwsFossServiceImpl(OSS_URL, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		} else if ("tencent".equals(OSS_FLAG)) {
			logger.info("==============tencent===============");
			fosService = new TencentFossServiceImpl(OSS_REGION, OSS_URL, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		}  else if("huawei".equals(OSS_FLAG)){
			logger.info("==============huawei===============");
			fosService = new HuaweiFossServiceImpl(OSS_URL, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
		}else{
			logger.info("配置文件没有配置，按默认的设置");
		}

	}

	public static void setFosService(StorageService service){
		logger.info("==============StorageService===============");
		fosService = new MeituanFOSServiceImpl(service);
	}

	/**
	 * 生成oss私有化的访问url
	 * @param bucketName 桶名
	 * @param uuid       标志
	 */
	public static String getPrivateUrlEndPoint(String bucketName, String uuid) {
		logger.info("uuid:"+uuid);
		if (null == uuid || "".equals(uuid)) {
			throw new FossException("key is null");
		}
		return fosService.getPrivateUrl(bucketName, uuid, EXPIRE_DATE);
	}

	public static String putObject(String bucketname,String key,InputStream input,String contenttype){
		logger.info("bucketname:"+bucketname+",key:"+key+",contenttype:"+contenttype);
		try {
			String pbresult = fosService.putObject(bucketname, key, input, contenttype);
			logger.info("putObject:"+pbresult);
			if(StringUtils.isBlank(pbresult)){
				throw new FossException("putObject result is null.");
			}
		} catch (Throwable e) {
			throw new FossException("putObject exception.",e);
		}
		return key;
	}

	public static String putObject(String bucketname,InputStream input,String contenttype){
		String key= UUID.randomUUID().toString();
		try {
			putObject(bucketname,key,input,contenttype);
		} catch (Exception e) {
			throw new FossException("putObject exception",e);
		}
		return key;
	}

	public static String putObject(String bucketname,String filepath,String contenttype){
		try {
			File file = new File(filepath);
			InputStream input = new FileInputStream(file);
			return putObject(bucketname,input,contenttype);
		} catch (FileNotFoundException e) {
			throw new FossException("putObject exception",e);
		}

	}

	public static String putObject(String bucketname,File file,String contenttype){
		try {
			InputStream input = new FileInputStream(file);
			return putObject(bucketname,input,contenttype);
		} catch (FileNotFoundException e) {
			throw new FossException("putObject exception",e);
		}
	}

	public static String putObject(String bucketname, String key, String filepath, String contenttype) {
		if(null==key||"".equals(key)){
			throw new FossException("key is null");
		}
		try {
			File file = new File(filepath);
			InputStream input = new FileInputStream(file);
			key=putObject(bucketname,key,input,contenttype);
		} catch (FileNotFoundException e) {
			throw new FossException("putObject exception",e);
		}catch (FossException e){
			throw new FossException("putObject exception",e);
		}
		return key;
	}

	public static FossObject getObject(String bucketName, String key) {
		logger.info("key:"+key);
		try {
			return fosService.getObject(bucketName, key);
		} catch (Throwable e) {
			throw new FossException("putObject exception",e);
		}
	}

//	public static InputStream getObjectAsStream(String bucketName, String key) {
//		String url = FOSClient.getPrivateUrlEndPoint(bucketName, key);
//		if(StringUtils.isNotBlank(url) && url.startsWith("http")){
//			ReturnBase<byte[]> returnBase=HttpUtil.downloadFile(url);
//			if(!returnBase.checkReturn()){
//				throw new FossException(returnBase.getMsg());
//			}
//			ByteArrayInputStream bais=new ByteArrayInputStream(returnBase.getData());
//			return bais;
//		}else{
//			FossObject object=FOSClient.getObject(bucketName, key);
//			return object.getObjectContent();
//		}
//	}
//	public static byte[] getObjectAsByte(String bucketName, String key) {
//
//		FossObject object = getObject(bucketName, key);
//		if(object==null){
//			String url = getPrivateUrlEndPoint(bucketName, key);
//			try {
//				if (StringUtils.isNotBlank(url) && url.startsWith("http")) {
//					ReturnBase<byte[]> returnBase = HttpUtil.downloadFile(url);
//					if (!returnBase.checkReturn()) {
//						throw new FossException(returnBase.getMsg());
//					} else {
//						return (byte[])returnBase.getData();
//					}
//				}else{
//					throw new FossException("getObject fail");
//				}
//			}catch (Exception e){
//				throw new FossException("getObject fail", e);
//			}finally {
//				FileUtil.delFile(url);
//			}
//		}else {
//			try {
//				InputStream is = object.getObjectContent();
//
//				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//				//创建一个Buffer字符串
//				byte[] buffer = new byte[1024];
//				//每次读取的字符串长度，如果为-1，代表全部读取完毕
//				int len = 0;
//				//使用一个输入流从buffer里把数据读取出来
//				while ((len = is.read(buffer)) != -1) {
//					//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
//					outStream.write(buffer, 0, len);
//				}
//				//关闭输入流
//				is.close();
//				//把outStream里的数据写入内存
//				return outStream.toByteArray();
//			} catch (IOException var6) {
//				throw new FossException("getObject fail", var6);
//			}
//		}
//	}
//
//	public static boolean getObject(String bucketName, String key,String filePath) {
//		FossObject object = getObject(bucketName, key);
//		if(object==null){
//			String url = getPrivateUrlEndPoint(bucketName, key);
//			if (StringUtils.isNotBlank(url) && url.startsWith("http")) {
//				return HttpUtil.downloadFile(filePath, url);
//			}else{
//				logger.info("url is error");
//				return false;
//			}
//		}else{
//			FileOutputStream fos = null;
//			InputStream is = null;
//
//			boolean var7;
//			try {
//				fos = new FileOutputStream(new File(filePath));
//				is = object.getObjectContent();
//				byte[] buf = new byte[2048];
//				boolean var8 = false;
//
//				int index;
//				while((index = is.read(buf)) != -1) {
//					fos.write(buf, 0, index);
//				}
//
//				boolean var9 = true;
//				return var9;
//			} catch (IOException var23) {
//				var7 = false;
//			} finally {
//				if (null != fos) {
//					try {
//						fos.close();
//					} catch (IOException var22) {
//						var22.printStackTrace();
//					}
//				}
//
//				if (null != is) {
//					try {
//						is.close();
//					} catch (IOException var21) {
//						var21.printStackTrace();
//					}
//				}
//			}
//			return var7;
//		}
//	}
//
//	/**
//	 * 复制一个Object，返回复制后的uuid
//	 * @param sourceBucketName      源桶
//	 * @param sourceUUid            源UUID
//	 * @param destinationBucketName 目标桶
//	 * @return 目标UUID
//	 */
//	public static void copyObject(String sourceBucketName, String sourceUUid, String destinationBucketName)
//			throws Exception {
//		String destinationUUid = UUIDGenerator.getUUID();
//		copyObject(sourceBucketName, sourceUUid, destinationBucketName, destinationUUid);
//	}

	public static void copyObject(String sourceBucketName, String sourceUUid
			, String destinationBucketName, String destinationUUid) {
		if (StringUtils.isBlank(sourceBucketName) || StringUtils.isBlank(sourceUUid)
				|| StringUtils.isBlank(destinationBucketName) || StringUtils.isBlank(destinationUUid)) {
			throw new FossException("parameter is null.");
		}
		try {
			fosService.getObject(sourceBucketName, sourceUUid);
			fosService.copyObject(sourceBucketName, sourceUUid, destinationBucketName, destinationUUid);
		} catch (Throwable e) {
			throw new FossException("copyObject exception.",e);
		}
	}

	public static Long getObjectSize(String bucketName, String uuid) {
		if (null == uuid || "".equals(uuid)) {
			return 0L;
		}
		FossObjectMetadata metadata = fosService.getObjectMetaData(bucketName, uuid);
		return metadata.getContentLength();
	}

}
