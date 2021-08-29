package com.dong.beta.config.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.*;

public class CustomizeBeanSerializerModifier extends BeanSerializerModifier {

    public static final String ARRAY_TYPE = "array";
    public static final String STRING_TYPE = "string";
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String NUMBER_TYPE = "number";
    public static final String OBJECT_TYPE = "object";

    public static final Map<String, JsonSerializer<Object>> map = new HashMap<String, JsonSerializer<Object>>() {{
        put(ARRAY_TYPE, new CustomizeNullJsonSerializer.NullArrayJsonSerializer());
        put(STRING_TYPE, new CustomizeNullJsonSerializer.NullStringJsonSerializer());
        put(BOOLEAN_TYPE, new CustomizeNullJsonSerializer.NullBooleanJsonSerializer());
        put(NUMBER_TYPE, new CustomizeNullJsonSerializer.NullNumberJsonSerializer());
        put(OBJECT_TYPE, new CustomizeNullJsonSerializer.NullObjectJsonSerializer());
    }};


    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        //替换内部的方法
        for(BeanPropertyWriter beanPropertyWriter:beanProperties){
            String type = getType(beanPropertyWriter);
            //获取对应类型的空值转换器
            JsonSerializer<Object> objectJsonSerializer = map.get(type);
            if(objectJsonSerializer!=null){
                //分配空值转换器
                beanPropertyWriter.assignNullSerializer(objectJsonSerializer);
            }
        }
        return beanProperties;
    }

    public String getType(BeanPropertyWriter p) {
        if (isArrayType(p)) {
            return ARRAY_TYPE;
        }

        if (isStringType(p)) {
            return STRING_TYPE;
        }

        if (isBooleanType(p)) {
            return BOOLEAN_TYPE;
        }

        if (isNumberType(p)) {
            return NUMBER_TYPE;
        }
        return null;
    }

    /**
     * 是否是数组
     */
    private boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是String
     */
    private boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是数值类型
     */
    private boolean isNumberType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是boolean
     */
    private boolean isBooleanType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Boolean.class);
    }

}