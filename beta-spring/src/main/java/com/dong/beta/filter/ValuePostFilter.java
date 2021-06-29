package com.dong.beta.filter;

import com.alibaba.fastjson.serializer.ValueFilter;
import com.dong.beta.utils.BondCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class ValuePostFilter implements ValueFilter {

    @Autowired
    HttpServletRequest request;

    @Override
    public Object process(Object object, String name, Object value) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (value instanceof String && BondCodeUtil.isNeedToConvert(name, request)) {
            return BondCodeUtil.convertGbToWd(value.toString());
        }
        if (value instanceof List && BondCodeUtil.isNeedToConvert(name, request)){
            return BondCodeUtil.convertGbToWdList((List<String>)value);
        }
        return value;
    }

}
