package org.jonas.test.tests;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jonas on 2017/1/13.
 */
public class Application3 {
    public static void main(String[] args) {
        System.out.println("-----------------3----------------");

        Set<String> set = new TreeSet<>();

        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");

        int index = 0;
        for (String s : set) {
            if (++index < 2) {
                continue;
            }

            System.out.println(s);
        }

        System.out.println("-----------------3----------------");
    }
}
