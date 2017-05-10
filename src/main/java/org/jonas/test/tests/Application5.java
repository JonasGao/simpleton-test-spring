package org.jonas.test.tests;

/**
 * Created by jonas on 2017/1/13.
 */
public class Application5 {
    public static void main(String[] args) {
        System.out.println("-----------------3----------------");



        System.out.println("-----------------3----------------");
    }
}

class A {
    void say() {
        System.out.println("Im A");
    }
}

class B extends A {
    @Override
    void say() {
        System.out.println("Im B");
    }
}

class C extends B {
    void say() {
        System.out.println("Im C");
    }
}
