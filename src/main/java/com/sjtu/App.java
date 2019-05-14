package com.sjtu;


import com.sjtu.domain.Car;
import com.sjtu.domain.Engine;
import com.sjtu.factory.AppContainer;
import com.sjtu.propertyEditors.StringEditor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class App {
    private int a;

    public static void main(String[] args) {

    }


    public static void f1() {
        try {
            Class clazz = Class.forName("com.sjtu.App");
            Field field = clazz.getDeclaredField("a");
            field.setAccessible(true);
            System.out.println(field.getType());
            System.out.println(field.getGenericType().toString());
            Constructor constructor = clazz.getConstructor();
            App app = (App) constructor.newInstance();
            Object b = constructor.newInstance();
            System.out.println(b.getClass());
            app.f();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(String.class);
    }

    public void f() {
        StringEditor stringEditor = new StringEditor();
        stringEditor.setAsText("110");
        System.out.println(stringEditor.getAsText());
        System.out.println(stringEditor.getValue());
        AppContainer appContainer = new AppContainer("conf.json");
        Car car = (Car) appContainer.getBean("car");
        System.out.println(car);
        Engine engine = (Engine) appContainer.getBean("engine");
        System.out.println(engine);
    }
}
