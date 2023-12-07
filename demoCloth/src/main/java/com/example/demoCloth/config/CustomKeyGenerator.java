package com.example.demoCloth.config;

import jakarta.annotation.Nonnull;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class CustomKeyGenerator implements KeyGenerator {
    @Override
    @Nonnull
    public Object generate(Object target, Method method,@Nonnull Object... params) {
        return target.getClass().getSimpleName() + "_" + method.getName() + "_"
                + StringUtils.arrayToDelimitedString(params, "_");
    }
}
