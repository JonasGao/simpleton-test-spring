package org.jonas.test.tests;

import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jonas on 2017/1/13.
 */
public class Application2 {
    public static void main(String[] args) {
        System.out.println("-----------------2----------------");

        Set<ZSetOperations.TypedTuple<String>> set = new TreeSet<>();

        ZSetOperations.TypedTuple<String> a;

        set.add(a = new T1("v1", 3D));
        set.add(new T1("v2", 2D));
        set.remove(a);
        set.add(new T1("v1", 2D));
        set.add(new T1(null, 1D));

        for (ZSetOperations.TypedTuple<String> stringTypedTuple : set) {
            System.out.println(stringTypedTuple.getValue() + ":" + stringTypedTuple.getScore());
        }

        System.out.println("-----------------2----------------");
    }

    static class T1 extends DefaultTypedTuple<String> {

        /**
         * Constructs a new <code>DefaultTypedTuple</code> instance.
         *
         * @param value
         * @param score
         */
        public T1(String value, Double score) {
            super(value, score);
        }

        @Override
        public int compareTo(ZSetOperations.TypedTuple<String> o) {

            int a = super.compareTo(o);
            return a == 0 ? this.getValue().compareTo(o.getValue()) : a;
        }
    }
}
