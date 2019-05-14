package com.sjtu.propertyEditors;

import com.sjtu.parsing.PropertyValue;

public interface PropertyAccessor {
    public Object getProperty(String propertyName);

    public void setProperty(String propertyName, Object value);

    public void setProperty(PropertyValue property);
}
