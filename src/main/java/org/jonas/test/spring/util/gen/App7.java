package org.jonas.test.spring.util.gen;

import org.springframework.core.GenericTypeResolver;

public class App7 {
    public static void main(String[] args) {
        Class<?> typeArgument = GenericTypeResolver.resolveTypeArgument(MyClass.class, MyInterface.class);
        System.out.println(typeArgument);
    }
}

class MyClass implements MyInterface<String> {
}

interface MyInterface<T> {
}
