package com.sjtu.parsing;

import com.sjtu.exception.ScopeException;

import java.util.List;

public interface BeanDefinition {
    public static final int SCOPE_SINGLETON = 1;
    public static final int SCOPE_PROTOTYPE = 2;

    public boolean isSingleton();

    public boolean isPrototype();

    String getId();

    int getScope();

    void setScope(int scope) throws ScopeException;

    public String getBeanClassName();

    public List<PropertyValue> getPropertValues();

}
