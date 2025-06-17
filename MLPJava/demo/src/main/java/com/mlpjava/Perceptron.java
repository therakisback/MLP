package com.mlpjava;

import java.util.ArrayList;

public class Perceptron {

    private ArrayList<ArrayList<Neuron>> nodes = new ArrayList<>();

    public Perceptron(int inputNodes, int[] hiddenNodes, int outputNodes) {

        // Constructing neuron objects as specified by inputs
        for (int i = 0; i < hiddenNodes.length + 2; i++) {
            int nodeCount;
            if (i == 0) nodeCount = inputNodes;
            else if (i == hiddenNodes.length + 1) nodeCount = outputNodes;
            else nodeCount = hiddenNodes[i - 1];

            for (int j = 0; j < nodeCount; j++) {
                nodes.get(i).set(j, new Neuron());
            }
        }
    }

    /**
     * Propogates the input forward in the perceptron
     * @param input double array of inputs from 0 to 1 inclusive that is fed into the perceptron
     * @return double array consisting of output values from perceptron between 0 and 1
     */
    public double[] forward(double[] input) {
        return new double[0];
    }

    public void train() {

    }

    private void back() {

    }


}
