package com.sjtu.propertyEditors;

public class PropertyEditorSupport implements PropertyEditor {
    private Object value;

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object newValue) {
        this.value = newValue;
    }

    @Override
    public void setAsText(String text) {
        if (value instanceof String) {
            setValue(text);
            return;
        }
    }

    @Override
    public String getAsText() {
        return (this.value != null) ? this.value.toString() : null;
    }
}
