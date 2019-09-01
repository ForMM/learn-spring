package com.example.demo.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cglib.beans.BeanMap;

import com.example.demo.controller.dto.EncryptDto;

public class BeanMapUtils extends  org.springframework.beans.BeanUtils{

	public BeanMapUtils() {}
	
	/**
     * 实例化对象：传入类对类进行实例化对象
     *
     * @param clazz 类
     * @return 对象
     */
    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiateClass(clazz);
    }

    /**
     * 实例化对象，传入类名对该类进行实例化对象
     *
     * @param clazzStr 类名,传入是必须传入全路径，com...
     * @return 对象
     */
    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 把对象封装成Map形式
     *
     * @param src 需要封装的实体对象
     */
    public static Map toMap(Object src) {
        return BeanMap.create(src);
    }

    /**
     * 把Map转换成bean对象
     *
     */
    public static <T> T toBean(Map<String, Object> beanMap, Class<T> valueType) {
        // 对象实例化
        T bean = newInstance(valueType);
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptors(valueType);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String properName = propertyDescriptor.getName();
            // 过滤class属性
            if ("class".equals(properName)) {
                continue;
            }
            if (beanMap.containsKey(properName)) {
                Method writeMethod = propertyDescriptor.getWriteMethod();
                if (null == writeMethod) {
                    continue;
                }
                Object value = beanMap.get(properName);
                if (!writeMethod.isAccessible()) {
                    writeMethod.setAccessible(true);
                }
                try {
                    writeMethod.invoke(bean, value);
                } catch (Throwable throwable) {
                    throw new RuntimeException("Could not set property '" + properName + " ' to bean" + throwable);
                }
            }
        }
        return bean;
    }
    
    
    public static void main(String[] args) {
        // Map To Bean
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("name", "lijkkkk");
        EncryptDto user = toBean(map, EncryptDto.class);
        System.out.println(user.getName());

        // Bean To Map
        EncryptDto user1 = new EncryptDto();
        user1.setName("653333000@qq.com");
        Map userMap = toMap(user1);
        System.out.println(userMap);
    }


}
