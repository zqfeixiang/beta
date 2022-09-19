package com.dong.beta.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.dong.beta.utils.BondCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurationSelector;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ValuePreFilter implements ValueFilter {

    private static final String PROXY_JCACHE_CONFIGURATION_CLASS =
            "org.springframework.cache.jcache.config.ProxyJCacheConfiguration";
    private static final boolean jsr107Present;

    private static final boolean jcacheImplPresent;

    static {
        ClassLoader classLoader = CachingConfigurationSelector.class.getClassLoader();
        jsr107Present = ClassUtils.isPresent("javax.cache.Cache1", classLoader);
        jcacheImplPresent = ClassUtils.isPresent(PROXY_JCACHE_CONFIGURATION_CLASS, classLoader);
        log.info("jsr107Present: {}, jcacheImplPresent: {}", jsr107Present, jcacheImplPresent);
    }

    @Override
    public Object process(Object object, String name, Object value) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (value instanceof String && BondCodeUtil.isNeedToConvert(name, request)) {
            return BondCodeUtil.convertWdToGbs(value.toString());
        }
        if (value instanceof List && BondCodeUtil.isNeedToConvert(name, request)){
            return BondCodeUtil.convertWdToGbList((List<String>)value);
        }
        return value;
    }

}
