package com.sjtu.propertyEditors;

public class StringEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        this.setValue(text);
    }
}
