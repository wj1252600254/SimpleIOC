package com.sjtu.domain;

import com.sjtu.annotation.Inject;

public class A {
    @Inject("person")
    Person person;

    public A() {
    }

    public A(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
