package com.example.demo.util.storage.meituan;

import com.fadada.storage.FossException;

public interface StorageService {
    String set(byte[] data, String key) throws FossException;
    byte[] get(String key) throws FossException;
    boolean exists(String key) throws FossException;
}
