package com.rufus.bumblebee.utils.log.configurer;

import com.rufus.bumblebee.utils.log.LogInputParameters;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.Objects;

@Component
public class MapperAnnotationProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(@Nullable Object bean, String beanName) {
        return Objects.nonNull(bean) ? init(bean) : null;
    }

    @Override
    public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) {
        return bean;
    }

    private Object init(Object bean) {
        Class<?> managedBeanClass = bean.getClass();
        LogInputParameters mapper = managedBeanClass.getAnnotation(LogInputParameters.class);
        if(Objects.nonNull(mapper)){
            ReflectionUtils.doWithMethods(managedBeanClass,method -> {

            });
        }
        return bean;
    }

}
