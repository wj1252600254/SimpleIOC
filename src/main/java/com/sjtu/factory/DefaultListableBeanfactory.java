package com.sjtu.factory;

import com.sjtu.annotation.AnnotationBeanDefinition;
import com.sjtu.annotation.Inject;
import com.sjtu.exception.BeanNameDuplicateException;
import com.sjtu.exception.BeanNameEmpryException;
import com.sjtu.exception.NoSuchBeanDefinitionException;
import com.sjtu.json.JsonBeanDefinitionReader;
import com.sjtu.parsing.BeanDefinition;
import com.sjtu.parsing.BeanDefinitionRegistry;
import com.sjtu.parsing.GeneraticBeanDefinition;
import com.sjtu.parsing.PropertyValue;
import com.sjtu.propertyEditors.*;
import com.sjtu.support.SimpleInstantionStrategy;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultListableBeanfactory extends BeanWrapperImpl implements BeanDefinitionRegistry, BeanFactory {
    private final List<AnnotationBeanDefinition> annotationBeanDefinitions = new CopyOnWriteArrayList<>();
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public void initialBean(String fileName) {
        PropertyEditorRegistry registry = new PropertyEditorRegistrySupport();
        registry.registerCustomEditor(String.class, new StringEditor());
        registry.registerCustomEditor(int.class, new IntEditor());
        JsonBeanDefinitionReader jsonBeanDefinitionReader = new JsonBeanDefinitionReader(this);
        jsonBeanDefinitionReader.loadBeanDefiniton(fileName);

        Iterator<Map.Entry<String, BeanDefinition>> iterator = beanDefinitionMap.entrySet().iterator();
        SimpleInstantionStrategy simpleInstantionStrategy = new SimpleInstantionStrategy();
        while (iterator.hasNext()) {
            Map.Entry<String, BeanDefinition> entry = iterator.next();
            //for instance
            if (entry.getValue() instanceof GeneraticBeanDefinition) {
                GeneraticBeanDefinition gb = (GeneraticBeanDefinition) entry.getValue();
                Object bean = simpleInstantionStrategy.instantite(gb);
                BeanWrapperImpl beanWrapper = new BeanWrapperImpl(bean, registry);
                List<PropertyValue> list = gb.getPropertValues();
                for (PropertyValue temp : list) {
                    beanWrapper.setProperty(temp);
                }
                beanMap.put(gb.getId(), beanWrapper.getBean());
                //to scan package
            } else if (entry.getValue() instanceof AnnotationBeanDefinition) {
                annotationBeanDefinitions.add((AnnotationBeanDefinition) entry.getValue());
            }
        }
        //@Inject  simple implemention
        for (Object object : beanMap.values()) {
            Class clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    Inject inject = field.getAnnotation(Inject.class);
                    Object value = beanMap.get(inject.value());
                    field.setAccessible(true);
                    try {
                        field.set(object, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    //simple version
    @Override
    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanNameEmpryException, BeanNameDuplicateException {
        if (beanName == null || beanName.length() == 0) {
            throw new BeanNameEmpryException("Bean name must not be empty");
        }
        if (beanDefinitionMap.get(beanName) != null) {
            throw new BeanNameDuplicateException("Bean name must not be duplicated");
        }
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefination(String name) {
        return beanDefinitionMap.containsKey(name);
    }

    @Override
    public void removeBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        if (!containsBeanDefination(beanName)) {
            throw new NoSuchBeanDefinitionException(beanName + "is not exist");
        }
        beanDefinitionMap.remove(beanName);

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        if (!containsBeanDefination(beanName)) {
            throw new NoSuchBeanDefinitionException(beanName + "is not exist");
        }
        return beanDefinitionMap.get(beanName);
    }
}
