package com.example.demo.config;

import com.fadada.utils.storage.meituan.StorageService;

import java.io.File;

public class StorageServiceImpl implements StorageService {


	public StorageServiceImpl(){
	}

	@Override
	public boolean exists(String key) throws Throwable {
		
		return true;
	}

	/**
	 * 下载模板
	 */
	@Override
	public byte[] get(String key) throws Throwable {
		System.out.println("key="+key);
		byte[] result = null;
		String filepath = "D:\\identity\\";
		File file = new File(filepath+key);
		result = FileUtil.getBytesByFile(file);
		return result;
	}

	/**
	 * 上传模板
	 * @param data 文件数组
	 * @param key 返回的uuid
	 * @param id 合同编号
	 */
	@Override
	public String set(byte[] data, String key, String id) throws Throwable {
		String filename = key;
		String filepath = "D:\\identity\\";
		FileUtil.getFileByBytes(data, filepath, filename);
		return filename;
	}
}
