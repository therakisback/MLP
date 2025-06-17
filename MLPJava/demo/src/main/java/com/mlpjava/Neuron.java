package com.mlpjava;


/** A class designed to be able to handle the functions of neurons in a network */
public class Neuron {
    
    double weight;
    double bias;

    public Neuron() { 
        weight = Math.random();
        bias = Math.random();
    }

    public double propogate(double input) {
        double z = bias + weight * input;
        return 1/(1+Math.exp(-z));
    }

    




}
