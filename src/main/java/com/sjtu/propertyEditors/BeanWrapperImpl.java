package com.sjtu.propertyEditors;


import com.sjtu.parsing.PropertyValue;

import java.lang.reflect.Field;

public class BeanWrapperImpl extends PropertyEditorRegistrySupport implements PropertyAccessor {
    //Bean
    private Object bean;

    private PropertyEditorRegistry propertyEditorRegistry;

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public BeanWrapperImpl() {
    }

    public BeanWrapperImpl(Object bean, PropertyEditorRegistry propertyEditorRegistry) {
        this.bean = bean;
        this.propertyEditorRegistry = propertyEditorRegistry;
    }

    @Override
    public Object getProperty(String propertyName) {
        Class clazz = bean.getClass();
        Object object = null;
        try {
            Field field = clazz.getDeclaredField(propertyName);
            field.setAccessible(true);
            object = field.get(bean);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        Class clazz = bean.getClass();
        try {
            Field field = clazz.getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(bean, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setProperty(PropertyValue property) {
        Class clazz = bean.getClass();
        String key = property.getName();
        try {
            Field field = clazz.getDeclaredField(key);
            PropertyEditor propertyEditor = propertyEditorRegistry.getCustomEditor(field.getType());
            //should be improved
            if (property.getValue() instanceof String) {
                propertyEditor.setAsText((String) property.getValue());
            } else {
                propertyEditor.setAsText(String.valueOf(property.getValue()));
            }

            field.setAccessible(true);
            field.set(bean, propertyEditor.getValue());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
