package com.roxy.blog.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtils {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null){
            return null;
        }
        Object obj;
        try {
            obj = beanClass.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }
}
