package com.mlpjava;

import java.util.ArrayList;
import java.util.List;

// I should note, for the time being I am keeping performance on the backburner - the ever etherial refactor that might never come
public class Perceptron {

    private final ArrayList<ArrayList<Neuron>> nodes = new ArrayList<>();
    private final double[] biases = new double[2];
    private int inputNodes;

    /**
     * Creates a new perceptron using the number of each node specified.
     * Can currently only handle a simple perceptron of 3 layers (including input)
     * TODO Add complexity by allowing > 1 layer in the hidden nodes
     */
    public Perceptron(int inputNodes, int hiddenNodes, int outputNodes) {
        // Constructing neuron objects as specified by inputs
        this.inputNodes = inputNodes;

        // Create hidden Neurons
        biases[0] = Math.random();

        ArrayList<Neuron> newNeurons = new ArrayList<>();
        for (int i = 0; i < hiddenNodes; i++) {
            newNeurons.add(new Neuron(inputNodes));
        }

        nodes.add(newNeurons);

        // Create output Neurons
        biases[1] = Math.random();
        newNeurons.clear();
        for (int j = 0; j < outputNodes; j++) {
            newNeurons.add(new Neuron(hiddenNodes));
        }
        nodes.add(newNeurons);
    }

        /**
     * Creates a new perceptron using the number of each node specified.
     * Can currently only handle a simple perceptron of 3 layers (including input)
     * With no amount of hidden nodes specified it is assume there will only be 2 layers (input - output)
     */
    public Perceptron(int inputNodes, int outputNodes) {
        this(inputNodes, 0 , outputNodes);
    }

    /**
     * Propogates the input forward in the perceptron
     * @param input Double list of inputs from 0 to 1 inclusive that is fed into the perceptron
     * @return Double list consisting of output values from perceptron between 0 and 1
     */
    public List<Double> forward(List<Double> input) {
        if (input.size() != inputNodes) throw new IllegalArgumentException("Incorrect amount of input values given to forward function.");
        List<Double> output = new ArrayList<>();
        output.addAll(input);

        for (int i = 0; i < nodes.size(); i++) {
            ArrayList<Double> tempOutput = new ArrayList<>();
            for (Neuron n : nodes.get(i)) {
                tempOutput.add(n.propogate(output, biases[i]));
            }
            output.clear();
            output.addAll(tempOutput);
        }
        return output;
    }

    /** TODO
     * Uses backprogation to train the perceptron to data provided
     */
    public void train() {

    }

    /** TODO
     * Private method for a single round of backpropagation
     */
    private void back() {
        // Calculate output delta and store values for use in hidden layer
        ArrayList<Neuron> outputNodes = nodes.get(1);
        double[] outputDelta = new double[outputNodes.size()];
        for (int i = 0; i < outputDelta.length; i++) {
            outputDelta[i] = outputNodes.get(i).deltaO();
        }

        // Change weights between hidden - output

        // Calculate hidden delta 

        // Change weights between input - hidden
    }


}
