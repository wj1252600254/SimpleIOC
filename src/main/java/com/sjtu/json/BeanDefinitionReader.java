package com.sjtu.json;

import java.io.FileNotFoundException;

public interface BeanDefinitionReader {
    public void loadBeanDefiniton(String filename) throws FileNotFoundException;
}
