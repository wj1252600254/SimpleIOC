package com.sjtu.propertyEditors;

public class IntEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) {
        setValue(Integer.valueOf(text));
    }

    @Override
    public String getAsText() {
        return String.valueOf(getValue());
    }

}
