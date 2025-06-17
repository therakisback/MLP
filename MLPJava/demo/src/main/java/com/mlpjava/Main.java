package com.mlpjava;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Perceptron p = new Perceptron(2, 2);
        Double[] inputs = new Double[] {
            0.5, 0.5
        };
        System.out.println(p.forward(Arrays.asList(inputs)).toString());
    }
}
