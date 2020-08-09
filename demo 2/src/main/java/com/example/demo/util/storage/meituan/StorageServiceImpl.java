package com.example.demo.util.storage.meituan;

import java.io.File;

import com.example.demo.config.MyConfig;
import com.fadada.storage.FossException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class StorageServiceImpl implements StorageService {

	public String coursePath;

	public StorageServiceImpl(){
	}

	public StorageServiceImpl(String coursePath){
		this.coursePath=coursePath;
	}

	@Override
	public boolean exists(String key) throws FossException {
		
		return true;
	}

	/**
	 * 下载模板
	 */
	@Override
	public byte[] get(String key) throws FossException {
		System.out.println("key="+key);
		byte[] result = null;
		String filepath = coursePath;
		File file = new File(filepath+key);
		result = FileUtil.getBytesByFile(file);
		return result;
	}

	/**
	 * 上传模板
	 * @param data 文件数组
	 * @param key 返回的uuid
	 */
	@Override
	public String set(byte[] data, String key) throws FossException {
		String filename = key;
		String filepath = coursePath;
		FileUtil.getFileByBytes(data, filepath, key);
		return filename;
	}


	public String getCoursePath() {
		return coursePath;
	}

	public void setCoursePath(String coursePath) {
		this.coursePath = coursePath;
	}
}
