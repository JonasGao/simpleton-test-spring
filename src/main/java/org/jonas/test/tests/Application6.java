package org.jonas.test.tests;

import java.util.stream.Stream;

/**
 * Created by jonas on 2017/2/9.
 */
public class Application6 {
    public static void main(String[] args) {
        Integer i = Stream.of(1,2,3,4,5,6).filter(it -> {
            System.out.println("filter1 => " + it);
            return it > 3;
        }).map(it -> {
            System.out.println("map 1 => " + it);
            return it - 1;
        }).filter(it -> {
            System.out.println("filter2 => " + it);
            return it > 1;
        }).findFirst().orElse(-1);
        System.out.println(i);
    }
}
