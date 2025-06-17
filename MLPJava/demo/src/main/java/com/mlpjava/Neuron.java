package com.mlpjava;

import java.util.List;


/** A class designed to be able to handle the functions of neurons in a network */
public class Neuron {
    
    double weight;
    double bias;        // Bias is often a non-independent variable for neurons, im trying it here anyway.
    double lastInput;   // Recorded for error propogation
    double lastOutput;  // ^

    public Neuron() { 
        weight = Math.random();
        bias = Math.random();
    }

    /**
     * Propogates an input through the neuron with a sigmoid function
     * @return output value of the neuron
     */
    public double propogate(double input) {
        lastInput = input;
        double z = bias + weight * input;
        lastOutput =  1/(1+Math.exp(-z));
        return lastOutput;
    }

    /**
     * Calculates the error of the neuron given an expected result and an actual result
     * @return double value of neuron error
     */
    // TODO
    public double error(double expectedValue) {
        return 0.5 * Math.pow(expectedValue - lastOutput, 2);
    }

    /**
     * Function to simplify the calculation of a total error from a set of neurons
     * and a set of expected values.
     * In future I could use a map input for expectedValues to remove the need for the order to be precise
     * but that seems needlessly complicated
     * @param expectedValues IMPORTANT: The expected values of the list MUST be in the same order of the neurons
     */
    public double totalError(List<Neuron> neurons, List<Double> expectedValues) {
        if (neurons.size() != expectedValues.size()) throw new IllegalArgumentException("Amount of neurons and expected outputs do not match.");
        double total = 0;
        // Personal thought: A language with functional aspects would be able to implement this loop better
        for (int i = 0; i < neurons.size(); i++) {
            Neuron n = neurons.get(i);
            double val = expectedValues.get(i);
            total += n.error(val);
        }
        return total;
    }
    




}
