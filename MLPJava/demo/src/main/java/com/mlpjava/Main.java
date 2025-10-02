package com.mlpjava;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        double[][] hidden = {   {.15, .20},
                                {.25, .30}};
        double[][] output = {   {.40, .45},
                                {.50, .55}};
        double[] biases = {.35, .60};
        Perceptron p = new Perceptron(2, 2, 2, hidden, output, biases);

        ArrayList<Double> input = new ArrayList<>();
        input.add(.05); 
            input.add(.10);

        System.out.println(p.forward(input));
    }
}
