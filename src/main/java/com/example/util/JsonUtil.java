package com.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jodd.util.StringUtil;

import java.lang.reflect.Type;

/**
 * json工具类
 *
 * @author xingce
 * @date 2022/7/22 14:52
 */
public class JsonUtil {
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder()
                //当Map的key为复杂对象时,需要开启该方法
                .enableComplexMapKeySerialization()
                //当字段值为空或null时，依然对该字段进行转换
                .serializeNulls()
                //打开Export注解，但打开了这个注解,副作用，要转换和不转换都要加注解
                //.excludeFieldsWithoutExposeAnnotation()
                //序列化日期格式  "yyyy-MM-dd HH:mm:ss"
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                //防止特殊字符出现乱码
                .disableHtmlEscaping()
                .create();
    }

    /**
     * 根据对象返回json  过滤空值字段
     */
    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    /**
     * @param json json
     * @param <T>  T
     * @param type new TypeToken<Map<String, T>>() {}.getType()
     *             new TypeToken<List<Map<String, T>>>() {}.getType()
     * @return T
     */
    public static <T> T fromJson(String json, Type type) {
        if (StringUtil.isEmpty(json)) {
            return null;
        }
        return GSON.fromJson(json, type);
    }
}
