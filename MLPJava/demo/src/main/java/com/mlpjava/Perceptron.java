package com.mlpjava;

import java.util.ArrayList;
import java.util.List;

// I should note, for the time being I am keeping performance on the backburner - the ever etherial refactor that might never come
public class Perceptron {

    private final ArrayList<ArrayList<Neuron>> nodes = new ArrayList<>();
    private ArrayList<Double> biases;
    private int inputNodes;

    public Perceptron(int inputNodes, int[] hiddenNodes, int outputNodes) {
        // Constructing neuron objects as specified by inputs
        this.inputNodes = inputNodes;

        // Create hidden Neurons
        for (int i = 0; i < hiddenNodes.length; i++) {
            biases.add(Math.random());

            int in;
            if (i == 0) in = inputNodes;
            else in = hiddenNodes[i - 1];

            ArrayList<Neuron> newNeurons = new ArrayList<>();
            for (int j = 0; j < hiddenNodes[i]; j++) {
                newNeurons.add(new Neuron(in));
            }
            nodes.add(newNeurons);
            newNeurons.clear();
        }

        // Create output Neurons
        biases.add(Math.random());
        ArrayList<Neuron> newNeurons = new ArrayList<>();
        for (int j = 0; j < outputNodes; j++) {
            newNeurons.add(new Neuron(hiddenNodes[hiddenNodes.length - 1]));
        }
        nodes.add(newNeurons);
    }

    public Perceptron(int inputNodes, int outputNodes) {
        this(inputNodes, new int[0], outputNodes);
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
                tempOutput.add(n.propogate(output, biases.get(i)));
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

    }


}
