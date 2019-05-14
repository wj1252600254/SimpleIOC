package com.sjtu.propertyEditors;

public interface PropertyEditorRegistry {
    void registerCustomEditor(Class requiredType, PropertyEditor propertyEditor);

    PropertyEditor getCustomEditor(Class requiredType);
}
