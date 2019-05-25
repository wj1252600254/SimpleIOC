package com.sjtu.json;

import com.sjtu.exception.NoSuchBeanDefinitionException;
import com.sjtu.factory.DefaultListableBeanfactory;
import org.junit.Test;
import org.mockito.Mockito;

public class JsonBeanDefinitionReaderTest {

    @Test
    public void testLoadBeanDefiniton() {
        DefaultListableBeanfactory defaultListableBeanfactory = Mockito.mock(DefaultListableBeanfactory.class);
        JsonBeanDefinitionReader jsonBeanDefinitionReader = new JsonBeanDefinitionReader(defaultListableBeanfactory);

        DefaultListableBeanfactory defaultListableBeanfactory1 = new DefaultListableBeanfactory();
        defaultListableBeanfactory1.initialBean("com/sjtu/test.json");
        try {
            System.out.println(defaultListableBeanfactory1.getBeanDefinition("test").getBeanClassName());
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
    }
}
