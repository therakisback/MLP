package com.mlpjava;

import java.util.ArrayList;
import java.util.List;

// I should note, for the time being I am keeping performance on the backburner - the ever etherial refactor that might never come
public class Perceptron {

    private final ArrayList<ArrayList<Neuron>> nodes = new ArrayList<>();

    public Perceptron(int inputNodes, int[] hiddenNodes, int outputNodes) {

        // Constructing neuron objects as specified by inputs
        for (int i = 0; i < hiddenNodes.length + 2; i++) {
            nodes.add(new ArrayList<>());

            int nodeCount;
            if (i == 0) nodeCount = inputNodes;
            else if (i == hiddenNodes.length + 1) nodeCount = outputNodes;
            else nodeCount = hiddenNodes[i - 1];

            for (int j = 0; j < nodeCount; j++) {
                nodes.get(i).add(new Neuron());
            }
        }
    }

    public Perceptron(int inputNodes, int outputNodes) {
        this(inputNodes, new int[0], outputNodes);
    }

    /** TODO
     * Propogates the input forward in the perceptron
     * @param input Double list of inputs from 0 to 1 inclusive that is fed into the perceptron
     * @return Double list consisting of output values from perceptron between 0 and 1
     */
    public List<Double> forward(List<Double> input) {
        List<Double> output = new ArrayList<>(input);
        for (List<Neuron> neurs : nodes) {
            double sum = output.stream().reduce(0d, (a, b) -> a + b);
            output.clear();
            for (Neuron n : neurs) {
                output.add((Double)n.propogate(sum));
            }
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
