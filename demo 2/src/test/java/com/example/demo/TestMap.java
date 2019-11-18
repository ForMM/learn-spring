package com.example.demo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestMap {

    @Test
    public void TestHashMap(){
        Map<String,String> ob = new HashMap<>();
        ob.put("aa","11");
        ob.put("bb","22");
        ob.put("aa","11");
        ob.put("cc","55");

        Set<Map.Entry<String, String>> entries = ob.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey());
        }

    }
}
