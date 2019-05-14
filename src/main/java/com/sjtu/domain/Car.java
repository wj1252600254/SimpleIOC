package com.sjtu.domain;

import com.sjtu.annotation.Inject;

public class Car {
    @Inject("engine")
    private Engine engine;

    private String name;

    private int speed;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", name='" + name + '\'' +
                ", speed=" + speed +
                '}';
    }
}
