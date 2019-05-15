package com.sjtu.annotation;

import com.sjtu.exception.ScopeException;
import com.sjtu.parsing.BeanDefinition;
import com.sjtu.parsing.PropertyValue;

import java.util.ArrayList;
import java.util.List;

public class AnnotationBeanDefinition implements BeanDefinition {
    List<PropertyValue> list = new ArrayList<>();

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public boolean isPrototype() {
        return false;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public int getScope() {
        return 0;
    }

    @Override
    public void setScope(int scope) throws ScopeException {

    }

    @Override
    public String getBeanClassName() {
        return null;
    }

    @Override
    public List<PropertyValue> getPropertValues() {
        return list;
    }
}
