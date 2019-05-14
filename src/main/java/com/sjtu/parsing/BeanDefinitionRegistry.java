package com.sjtu.parsing;

import com.sjtu.exception.BeanNameDuplicateException;
import com.sjtu.exception.BeanNameEmpryException;
import com.sjtu.exception.NoSuchBeanDefinitionException;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanNameEmpryException, BeanNameDuplicateException;

    void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

    boolean containsBeanDefination(String name);

}
