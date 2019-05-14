package com.sjtu.support;

import com.sjtu.parsing.GeneraticBeanDefinition;

public interface InstantiationStrategy {
    Object instantite(GeneraticBeanDefinition gb);
}
