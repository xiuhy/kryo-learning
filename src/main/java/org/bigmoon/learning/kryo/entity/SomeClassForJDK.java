package org.bigmoon.learning.kryo.entity;

import java.io.Serializable;

/**
 * 使用jdk自带序列化，必须实现Serializable接口否则无法序列化
 */

public class SomeClassForJDK extends SomeClass implements Serializable {


    private static final long serialVersionUID = 315421838034519785L;

    public SomeClassForJDK(String value) {
        super(value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SomeClassForJDK{");
        sb.append(", value='").append(value).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
