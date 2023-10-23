package com.scu.uars.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @Program: UARS
 * @Description: web层工具类
 * @Author: MOATSH
 * @Create: 2023-07-15 09:58
 **/

public class WebUtils {

    /**
     * 将属性map映射到JavaBean对象
     * 返回JavaBean对象实例
     */
    public static <T> T copyParamToBean(Map<String, ?> map, T bean) {
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            System.out.println("WebUtils.copyParamToBean error!");
        }

        return bean;
    }

    public static int parseInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("ParseInt error!");
        }

        return defaultValue;
    }
}
