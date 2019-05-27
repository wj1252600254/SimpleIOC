package com.sjtu.factory;

import com.sjtu.domain.A;
import com.sjtu.exception.NoSuchBeanDefinitionException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DefaultBeanFactoryTest {
    @Test
    public void testInitialBean() {
        DefaultListableBeanfactory defaultListableBeanfactory = Mockito.mock(DefaultListableBeanfactory.class);
        defaultListableBeanfactory.initialBean("test.json");
        Mockito.verify(defaultListableBeanfactory, Mockito.times(1)).initialBean("test.json");
        //测试各个函数
        DefaultListableBeanfactory defaultListableBeanfactory1 = new DefaultListableBeanfactory();
        defaultListableBeanfactory1.initialBean("test.json");
        defaultListableBeanfactory1.containsBeanDefination("test");
        try {
            String id = defaultListableBeanfactory1.getBeanDefinition("test").getId();
            String clazz = defaultListableBeanfactory1.getBeanDefinition("test").getBeanClassName();
            Assert.assertEquals(id, "test");
            Assert.assertEquals(clazz, "com.sjtu.utils.Utils");
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
        //测试removeBeanDefinition
        try {
            defaultListableBeanfactory1.removeBeanDefinition("test");
            Assert.assertNull(defaultListableBeanfactory1.getBeanDefinition("test"));
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
        //测试@Inject注解
        A a = (A) defaultListableBeanfactory1.getBean("A");
        Assert.assertEquals(a.getPerson().getPid(), "117031910031");
        Assert.assertEquals(a.getPerson().getName(), "WJJ");
    }
}
