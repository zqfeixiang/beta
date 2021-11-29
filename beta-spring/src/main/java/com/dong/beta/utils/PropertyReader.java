package com.dong.beta.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author dzq
 * @date 2021/11/25 10:48 上午
 **/
public class PropertyReader {
    private static Properties properties = new Properties();

    private PropertyReader(){

    }

    public static void init(InputStream is, Properties override) throws IOException {
        if (null != is){
            properties.load(is);
        }

        if (null != override){
            for (String key : override.stringPropertyNames()){
                properties.setProperty(key, override.getProperty(key));
            }
        }
    }

    public static final  String getProperty(final String key, final String defaultValue){
        return properties.getProperty(key, defaultValue);
    }

    public static final int getProperty(final String key, final int defaultValue){
        final String value =  properties.getProperty(key);
        if (null != value){
            try {
                return Integer.parseInt(value);
            }catch (Exception e){
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static final double getProperty(final String key, final double defaultValue){
        final String value =  properties.getProperty(key);
        if (null != value){
            try {
                return Double.parseDouble(value);
            }catch (Exception e){
                return defaultValue;
            }
        }
        return defaultValue;
    }

}
