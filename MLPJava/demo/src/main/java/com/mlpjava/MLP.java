package com.mlpjava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MLP{

    Perceptron p;

    /**
     * Creates new Multi Layer Perceptron object.
     * @param inputNodes int: Number of required inputs.
     * @param hiddenNodes int: Number of hidden nodes.
     * @param outputNodes int: Number of required outputs.
     */
    MLP(int inputNodes, int hiddenNodes, int outputNodes) {
        if (inputNodes <= 0 || hiddenNodes <= 0 || outputNodes <= 0) throw new IllegalArgumentException("Must have at least one of each node");
        p = new Perceptron(inputNodes, hiddenNodes, outputNodes);
    }

    /**
     * Trains the perceptron based off of a dataset of inputs and expected outputs.
     * @param epochs int: Number of iterations AKA 'Epochs' to run the MLP through.
     * @param input double[][]: Input datasets for MLP.
     * @param expected double[][]: Expected output for a given input. Input and expected arrays must match index or training will be running on incorrect information.
     */
    public List<double[][]> train(int epochs, double[][] input, double[][] expected, File error) {

        if (epochs < 1) throw new IllegalArgumentException("Must have at least one epoch");
        if (input.length != expected.length) throw new IllegalArgumentException("Input and expected dataset sizes are different");  double currentRate = 0.1;
        double delta = currentRate / (epochs + 1);
        // The following holds sets of output data to show training process
        ArrayList<double[][]> records = new ArrayList<>();
        // FileWriter to write error to a given error file
        FileWriter fw;
        try {
            fw = new FileWriter(error);

            for (int currentEpoch = 1; currentEpoch <= epochs; currentEpoch++) {
                double[][] record = new double[input.length][input[0].length];
                double eTotal = 0;
                if (Math.log10(currentEpoch) % 1 == 0) {
                    fw.write("Epoch = " + currentEpoch + "\n");
                }
                for (int i = 0; i < input.length; i++) {
                    if (Math.log10(currentEpoch) % 1 == 0) {
                        record[i] = (p.forward(input[i]));
                        eTotal += p.backwards(input[i], expected[i], currentRate);
                    }
                    else {
                        p.forward(input[i]);
                        p.backwards(input[i], expected[i], currentRate);
                    }
                }
                currentRate -= delta;
                if (Math.log10(currentEpoch) % 1 == 0) {
                    records.add(record);
                    fw.write((eTotal/input.length) + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {throw new IllegalArgumentException("Error writing to file " + e);}
        return records;
    }

    /**
     * Trains the perceptron based off of a dataset of inputs and expected outputs.
     * @param epochs int: Number of iterations AKA 'Epochs' to run the MLP through.
     * @param input double[]: Input data for MLP.
     * @param expected double[]: Expected output for a given input. Input and expected arrays must match index or training will be running on incorrect information.
     */
    public List<double[]> train(int epochs, double[] input, double[] expected, File error) {
        double currentRate = 0.1;
        double delta = currentRate / (epochs + 1);
        ArrayList<double[]> records = new ArrayList<>();
        FileWriter fw;

        try {
            fw = new FileWriter(error);
            if (epochs < 1) throw new IllegalArgumentException("Must have at least one epoch");
            if (input.length != expected.length)
                throw new IllegalArgumentException("Input and expected dataset sizes are different");

            for (int currentEpoch = 0; currentEpoch < epochs; currentEpoch++) {
                if (Math.log10(currentEpoch) % 1 == 0) {
                    records.add(p.forward(input));
                    fw.write("Epoch = " + currentEpoch + "\n");
                    fw.write(p.backwards(input, expected, currentRate) + "\n");
                }
                else {
                    p.forward(input);
                    p.backwards(input, expected, currentRate);
                }
                currentRate -= delta;
            }
            fw.close();
        } catch (IOException e) {throw new IllegalArgumentException("Error writing to file " + e);}
        return records;
    }

    /**
     * A function that runs an input through the MLP
     * @param input Input data for perceptron
     * @return Output from perceptron in current state
     */
    public double[] run(double[] input) {
        return p.forward(input);
    }

    public List<double[]> run (double[][] input) {
        ArrayList<double[]> out = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            out.add(run(input[i]));
        }
        return out;
    }

}
