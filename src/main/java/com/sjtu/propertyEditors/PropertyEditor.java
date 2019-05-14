package com.sjtu.propertyEditors;

public interface PropertyEditor {
    public Object getValue();

    public void setValue(Object newValue);

    public void setAsText(String text);

    public String getAsText();

}
