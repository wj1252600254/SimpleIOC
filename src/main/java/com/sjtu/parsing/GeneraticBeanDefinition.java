package com.sjtu.parsing;

import com.sjtu.exception.ScopeException;

import java.util.ArrayList;
import java.util.List;

public class GeneraticBeanDefinition implements BeanDefinition {
    //bean Name
    private String id;
    //bean ClassName
    private String beanClassName;
    //Default is sigleton
    private int scope = SCOPE_SINGLETON;
    //propreties
    List<PropertyValue> propertyValues = new ArrayList<>();

    public GeneraticBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    @Override
    public boolean isSingleton() {
        return scope == 1;
    }

    @Override
    public boolean isPrototype() {
        return scope == 2;
    }

    @Override
    public int getScope() {
        return this.scope;
    }

    @Override
    public void setScope(int scope) throws ScopeException {
        if (this.scope != 1 && this.scope != 2) {
            throw new ScopeException("The value of scope is between 1 and 2");
        } else {
            this.scope = scope;
        }
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public List<PropertyValue> getPropertValues() {
        return this.propertyValues;
    }

    @Override
    public String getId() {
        return id;
    }

}
