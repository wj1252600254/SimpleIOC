package com.sjtu.support;

import com.sjtu.parsing.GeneraticBeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantionStrategy implements InstantiationStrategy {
    @Override
    public Object instantite(GeneraticBeanDefinition gb) {
        Object object = null;
        try {
            Class clazz = Class.forName(gb.getBeanClassName());
            Constructor constructor = clazz.getConstructor();
            object = constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }
}
