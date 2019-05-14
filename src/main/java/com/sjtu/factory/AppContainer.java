package com.sjtu.factory;


public class AppContainer extends DefaultListableBeanfactory {

    public AppContainer(String fileName) {
        super.initialBean(fileName);
    }
}
