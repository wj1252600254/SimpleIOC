package com.sjtu.json;

import com.sjtu.exception.NoSuchBeanDefinitionException;
import com.sjtu.factory.DefaultListableBeanfactory;
import org.junit.Assert;
import org.junit.Test;

public class JsonBeanDefinitionReaderTest {

    @Test
    public void testLoadBeanDefiniton() {
        DefaultListableBeanfactory defaultListableBeanfactory = new DefaultListableBeanfactory();
        JsonBeanDefinitionReader jsonBeanDefinitionReader = new JsonBeanDefinitionReader(defaultListableBeanfactory);
        jsonBeanDefinitionReader.loadBeanDefiniton("test.json");
        String id = "";
        String className = "";
        String scope = "";
        try {
            id = defaultListableBeanfactory.getBeanDefinition("test").getId();
            className = defaultListableBeanfactory.getBeanDefinition("test").getBeanClassName();
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(id, "test");
        Assert.assertEquals(className, "com.sjtu.utils.Utils");
    }

}
