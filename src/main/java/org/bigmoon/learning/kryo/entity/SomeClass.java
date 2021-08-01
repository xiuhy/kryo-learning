package org.bigmoon.learning.kryo.entity;

import java.io.Serializable;

public class SomeClass implements Serializable {

    String value;
    Integer age;

    public SomeClass() {
    }

    public SomeClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SomeClass{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
