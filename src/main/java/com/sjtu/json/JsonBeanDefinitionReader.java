package com.sjtu.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sjtu.exception.BeanNameDuplicateException;
import com.sjtu.exception.BeanNameEmpryException;
import com.sjtu.exception.ScopeException;
import com.sjtu.parsing.BeanDefinition;
import com.sjtu.parsing.BeanDefinitionRegistry;
import com.sjtu.parsing.GeneraticBeanDefinition;
import com.sjtu.parsing.PropertyValue;
import com.sjtu.utils.Utils;

import java.util.Iterator;
import java.util.Map;


public class JsonBeanDefinitionReader implements BeanDefinitionReader {
    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";

    private static final String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistry beanDefinitionRegister;

    //DefaultListablefactory
    public JsonBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegister) {
        this.beanDefinitionRegister = beanDefinitionRegister;
    }

    //the file should be put on the classpath
    @Override
    public void loadBeanDefiniton(String filename) {
        String path = JsonBeanDefinitionReader.class.getClassLoader().getResource("").getPath() + filename;
        JSONArray jsonArray = JSON.parseArray(Utils.readFile(path));
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String id = jsonObject.getString(ID_ATTRIBUTE);
            String clazz = jsonObject.getString(CLASS_ATTRIBUTE);
            String scope = jsonObject.getString(SCOPE_ATTRIBUTE);
            BeanDefinition beanDefinition = new GeneraticBeanDefinition(id, clazz);
            if (scope != null) {
                if (scope.equalsIgnoreCase("prototype")) {
                    try {
                        beanDefinition.setScope(2);
                    } catch (ScopeException e) {
                        e.printStackTrace();
                    }
                }
            }
            Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                if (mapEntry.getKey().trim().equalsIgnoreCase("id") || mapEntry.getKey().trim().equalsIgnoreCase("class") ||
                        mapEntry.getKey().trim().equalsIgnoreCase("scope")) {
                    continue;
                }
                beanDefinition.getPropertValues().add(new PropertyValue(mapEntry.getKey(), mapEntry.getValue()));
            }
            try {
                this.beanDefinitionRegister.registerBeanDefinition(id, beanDefinition);
            } catch (BeanNameEmpryException e) {
                e.printStackTrace();
            } catch (BeanNameDuplicateException e) {
                e.printStackTrace();
            }
        }
    }


}
