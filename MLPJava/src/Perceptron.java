import java.util.*;
import java.util.stream.*;

/*
    I   -   H   -   O
        X       X
    I   -   H   -   O
    w[0][0]: i1 - h1, w[0][1]: i2 - h1, w[1][0]: i1 - h2
 */


public class Perceptron {
    // # of input, hidden, and output neurons
    int inputs, hidden, outputs;
    // Double arrays holding weights for links between input-hidden (w1), hidden-output (w2), and changes
    double[][] w1, w2, d1, d2;
    // Double array for values in hidden nodes and output nodes
    double[] hid, out;


    /**
     * Constructor for a Perceptron object.
     * @param inputs Int: Number of input nodes to be created
     * @param hidden Int: Number of hidden nodes
     * @param outputs Int: Number of outputs
     */
    public Perceptron(int inputs, int hidden, int outputs) {
        // init basic numbers
        this.inputs  = inputs;
        this.hidden  = hidden;
        this.outputs = outputs;

        // init weight arrays
        w1 = new double[inputs][hidden];
        w2 = new double[hidden][outputs];
        d1 = new double[inputs][hidden];
        d2 = new double[hidden][outputs];

        hid = new double[hidden];
        out = new double[outputs];

        for(int i = 0; i < inputs; i++) {
            for(int j = 0; j < hidden; j++) {
                w1[i][j] = (Math.random() * 20) - 10;
            }
        }

        for(int i = 0; i < hidden; i++) {
            for(int j = 0; j < outputs; j++) {
                w2[i][j] = (Math.random() * 20) - 10;
            }
        }

        for(double[] dx: d1) {Arrays.fill(dx, 0.0);}
        for(double[] dy: d2) {Arrays.fill(dy, 0.0);}
    }


    /**
     * Performs a round of forward propagation
     * @param in double[]: Input values
     * @return double[]: Output values
     */
    private double[] forward(double[] in) {
        if(in.length != inputs) throw new IllegalArgumentException();
        // Calculate output for each hidden node
        hid = nodes(in, hidden, w1);
        // Calculate output nodes
        out = nodes(hid, outputs, w2);
        return out;
    }

    /**
     * Private function to perform calculations needed for forward propagation to reduce repetition.
     * @param inputValues values being input at the input nodes
     * @param outputNode number of "output" nodes in this group - could be hidden or output
     * @param weights weights for the given connections
     * @return
     */
    private double[] nodes(double[] inputValues, int outputNode, double[][] weights) {
        // Array to hold output values
        double[] temp = new double[outputNode];
        // Loop through each output node, determining sum of inputs and plugging into sigmoid function
        for (int i = 0; i < outputNode; i++) {
            double z = 0;
            for (int j = 0; j < inputValues.length; j++) {z += (weights[i][j] * inputValues[j]);}
            temp[i] = sig(z);
        }
        return temp;
    }

    /**
     * Performs an entire round of back-propagation through the neural network
     * @param expected double[]: to contain expected outputs
     * @return double: Output error calculated in neural network
     */
    private double backwards(double[] expected, double learningRate) {
        if(expected.length != outputs) throw new IllegalArgumentException();

        // Array to hold error
        double[] outputNodeDelta = new double[outputs];
        double eTotal = 0;

        // Calculate output error
        for (int i = 0; i < outputs; i++) {
            eTotal += 0.5 * Math.pow(expected[i] - out[i], 2);
        }

        // Calculate weight changes for hidden - output
        for (int i = 0; i < outputs; i++) {
            double gradient = nodeDelta(expected[i], out[i]);
            outputNodeDelta[i] = gradient;
            for (int j = 0; j < hidden; j++) {
                // Old weight - learning rate * (gradient of eTotal with respect to old weight)
                d2[i][j] = w2[i][j] - (learningRate * gradient * hid[j]);
            }
        }

        // Calculate weight changes for input - hidden
        for (int i = 0; i < hidden; i++) {
            double gradient = nodeDelta(expected[i], hid[i]);
            for (int j = 0; j < inputs; j++) {
                // Old weight - learning rate * (gradient of eTotal with respect to old weight)
                d2[i][j] = w2[i][j] - (learningRate * gradient * input[j]);
            }
        }

        // Combine weights with change in weights



        return eTotal;
    }


    /**
     * Gets the sigmoidal value at a given x value
     */
    private double sig(double x) {
        return 1/(1+Math.exp(-x));
    }

    /**
     * Gets the partial derivative of total error with respect to a weight
     * I got this formula from here: https://mattmazur.com/2015/03/17/a-step-by-step-backpropagation-example/
     * I will admit partial derivatives are a function I don't understand
     * @param expected Expected value from the output node
     * @param output   Output value of the output node
     * @return Double value return of the function
     */
    private double nodeDelta(double expected, double output) {
        return ((output - expected) * output * (1 - output));
    }

}
