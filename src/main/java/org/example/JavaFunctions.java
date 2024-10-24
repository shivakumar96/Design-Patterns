package org.example;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class JavaFunctions {
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> add = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer+integer2;
            }
        };

        Function<Integer,Float> half = new Function<Integer, Float>() {
            @Override
            public Float apply(Integer int1) {
                return int1/(float)2.0;
            }
        };

        System.out.println(half.apply(3));

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

        BinaryOperator<Integer> operator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };



        Stream<Integer> stream = Arrays.asList(1,2,3).stream();
        int res = stream.reduce(0,(a,b)->a+b);
        System.out.println(res);
    }

    
}