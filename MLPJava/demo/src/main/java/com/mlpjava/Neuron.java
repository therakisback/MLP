package com.mlpjava;

import java.util.ArrayList;
import java.util.List;


/** A class designed to be able to handle the functions of neurons in a network */
public class Neuron {
    
    public ArrayList<Double> weights = new ArrayList<>();
    public List<Double> lastInputs;   // Recorded for error propogation
    public double lastOutput;  // ^

    /**
     * Constructor for a Neuron object
     * @param inputs The number of inputs the neuron should expect. This should essentially be the number of neurons one layer higher than the layer of this neuron.
     */
    public Neuron(int inputs) { 
        if (inputs <= 0) throw new IllegalArgumentException("Number of inputs specified for neuron must be greater than 0.");
        for (int i = 0; i < inputs; i++) {
            weights.add(Math.random());
        } 
    }

    /**
     * Constructor for a Neuron object with set weight values rather than randomized
     * @param inputs The number of inputs the neuron should expect. This should essentially be the number of neurons one layer higher than the layer of this neuron.
     * @param weights double array containing the values of the weights leading *into* this neuron. weights.length must be equal to inputs. If a value in the array is un-initialized it will be random.
     */
    public Neuron(int inputs, double[] weights) {
        if (weights.length != inputs) throw new IllegalArgumentException("Number of weights given to neuron is not the same as the number of inputs specified for this neuron.");
        for (int i = 0; i < inputs; i++) {
            this.weights.add(weights[i]);
        }
        //System.out.println("Neuron created, weights: " + Arrays.toString(weights));
    }

    /**
     * Propogates an input through the neuron with a sigmoid function
     * @param inputs The list of input values for this neuron. They must be in the same order as the weights of the neuron.
     * @return output value of the neuron
     */
    public double propogate(List<Double> inputs, double bias) {
        lastInputs = inputs;
        double z = 0;
        for (int i = 0; i < inputs.size(); i++) {
            z += inputs.get(i) * weights.get(i);
        }
        z += bias;
        lastOutput =  1/(1+Math.exp(-z));
        //System.out.println("Propogation finished, z = " + z + ", output = " + lastOutput);
        return lastOutput;
    }

    /**
     * Calculates the delta value of a neuron given an expected input or the errors of the output nodes
     * For an output neuron an expected value (the target) must be provided
     * For the hidden neuron the list of errors for the output nodes connecting must be provided in order
     * @return double value of neuron delta value (delta * input of last node = error)
     */
    public double deltaO(double expectedValue) {
        return (-(expectedValue - lastOutput) * lastOutput * (1-lastOutput));
    }

    /**
     * Calculates the delta value of a neuron given an expected input or the errors of the output nodes
     * For an output neuron an expected value (the target) must be provided
     * For the hidden neuron the list of errors for the output nodes mutliplied by their weight to the hidden node must be provided
     * @return double value of neuron delta value (delta * input of last node = error)
     */
    public double deltaH(double outputErrorSum) {
        return outputErrorSum * lastOutput * (1 - lastOutput);
    }

    /**
     * Function to simplify the calculation of a total error from a set of neurons
     * and a set of expected values.
     * @param expectedValues IMPORTANT: The expected values of the list MUST be in the same order of the neurons
     */
    public static double totalDelta(List<Neuron> neurons, List<Double> expectedValues) {
        if (neurons.size() != expectedValues.size()) throw new IllegalArgumentException("Amount of neurons and expected outputs do not match.");
        double total = 0;
        // Personal thought: A language with functional aspects would be able to implement this loop better
        for (int i = 0; i < neurons.size(); i++) {
            Neuron n = neurons.get(i);
            double val = expectedValues.get(i);
            total += n.deltaO(val);
        }
        return total;
    }

    @Override
    public String toString() {
        return "Neuron: \n\t Weights: " + weights + "\n\t Last Inputs: " + lastInputs + "\n\t Last Output: " + lastOutput + "\n";
    }
}
