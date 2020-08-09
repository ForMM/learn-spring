package com.example.demo.util.storage.meituan;

import com.example.demo.controller.AccountController;
import com.fadada.storage.FossException;
import com.fadada.storage.FossObject;
import com.fadada.storage.FossObjectMetadata;
import com.fadada.storage.FossService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;

public class MeituanFOSServiceImpl implements FossService {
    private static Logger logger = LoggerFactory.getLogger(MeituanFOSServiceImpl.class);
    private static String contractId;
    private StorageService client;
    
    public MeituanFOSServiceImpl(StorageService client){
    	this.client=client;
    }
    
	@Override
	public String putObject(String bucketName, String key, InputStream input,
			String contentType) throws FossException, IOException{
	        byte []data=new byte[input.available()];
	        input.read(data);
	        input.close();
        client.set(data, key);
        return key;
	}


	@Override
	public FossObject getObject(String bucketName, String key) throws FossException{
		FossObject object=new FossObject();
		if(client.exists(key)){
			byte[] content=client.get(key);
			object.setObjectContent(new ByteArrayInputStream(content));
		}
        return object;
	}

	@Override
	public String getPrivateUrl(String bucketName, String key, int expires) {
//        String sourcepath = LightApiConstants.PDF_TEMP_PATH + UUIDGenerator.getUUID() + LightApiConstants.END_PDF;
//        logger.info("========生成文件路径为：" + sourcepath);
//        boolean ffile = FileUtil.mkDirectory();
//        if (!ffile) {
//            logger.error("文件夹生成失败");
//        }
//        FileOutputStream fos = null;
//        InputStream is = null;
//        try {
////			if (client.exists(key)) {
//            byte[] content = client.get(key);
//            if (content == null) {
//                logger.info("byte[] is null !!!!!!");
//            }
//            fos = new FileOutputStream(new File(sourcepath));
//            is = new ByteArrayInputStream(content);
//            byte[] buf = new byte[2048];
//            int index = 0;
//            while ((index = is.read(buf)) != -1) {
//                fos.write(buf, 0, index);
//            }
////			}
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return sourcepath;
        return "";
    }

	@Override
	public FossObjectMetadata getObjectMetaData(String bucketName, String key) {
		return null;
	}

	@Override
	public void deleteObject(String bucketName, String key) throws FossException {
		
	}

	@Override
	public void copyObject(String sourceBucketName, String sourceKey,
			String destinationBucketName, String destinationKey) {
		
	}

    @Override
    public void createBucket(String s) throws FossException {

    }

    @Override
    public void deleteBucket(String s) throws FossException {

    }

    @Override
    public boolean doesBucketExist(String s) throws FossException {
        return false;
    }

    @Override
    public String putObject(String s, String s1, InputStream inputStream, String s2, String s3) throws FossException, IOException {
        return null;
    }

    public static String getContractId() {
        return contractId;
    }

    public static void setContractId(String contractId) {
        MeituanFOSServiceImpl.contractId = contractId;
    }
}
