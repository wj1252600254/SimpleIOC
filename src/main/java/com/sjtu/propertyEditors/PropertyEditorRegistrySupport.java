package com.sjtu.propertyEditors;

import java.util.HashMap;
import java.util.Map;

public class PropertyEditorRegistrySupport implements PropertyEditorRegistry {
    private Map<Class, PropertyEditor> propertyEditorMap = new HashMap<>();


    //simple version
    @Override
    public void registerCustomEditor(Class requiredType, PropertyEditor propertyEditor) {
        //should add inspection of requiredType
        propertyEditorMap.put(requiredType, propertyEditor);
    }

    @Override
    public PropertyEditor getCustomEditor(Class requiredType) {
        return propertyEditorMap.get(requiredType);
    }
}
