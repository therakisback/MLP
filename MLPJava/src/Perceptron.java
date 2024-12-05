import java.util.*;

public class Perceptron {
    // # of input, hidden, and output neurons
    private final int inputs, hidden, outputs;
    // Double arrays holding weights for links between input-hidden (w1), hidden-output (w2), and changes
    private final double[][] w1, w2, d1, d2;
    // Double array for values in hidden nodes and output nodes
    private double[] hid, out, bias1, bias2;


    /**
     * Constructor for a Perceptron object.
     * @param inputs int: Number of input nodes, must be >= 1
     * @param hidden int: Number of hidden nodes, must be >= 1
     * @param outputs int: Number of outputs, must be >= 1
     */
    public Perceptron(int inputs, int hidden, int outputs) {

        if (inputs < 1 || hidden < 1 || outputs < 1) throw new IllegalArgumentException("Must have at least one of each node.");

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
        bias1 = new double[hidden];
        bias2 = new double[outputs];

        for(int i = 0; i < inputs; i++) {
            for(int j = 0; j < hidden; j++) {
                w1[i][j] = Math.random();
            }
        }

        for(int i = 0; i < hidden; i++) {
            for(int j = 0; j < outputs; j++) {
                w2[i][j] = Math.random();
            }
        }

        for (double d: bias1) {d = Math.random();}
        for (double d: bias2) {d = Math.random();}
        for(double[] dx: d1) {Arrays.fill(dx, 0.0);}
        for(double[] dy: d2) {Arrays.fill(dy, 0.0);}
    }


    /**
     * Performs a round of forward propagation
     * @param in double[]: Input values
     * @return double[]: Output values
     */
    public double[] forward(double[] in) {
        if(in.length != inputs) throw new IllegalArgumentException("Input data doesn't match number of input nodes");
        // Calculate output for each hidden node
        hid = nodes(in, hidden, w1, bias1);
        // Calculate output nodes
        out = nodes(hid, outputs, w2, bias2); // ArrayIndexOutOfBoundsException
        return out;
    }

    /**
     * Private function to perform calculations needed for forward propagation to reduce repetition.
     * @param inputValues double[]: values being input at the input nodes
     * @param outputNode int: number of "output" nodes in this group - could be hidden or output
     * @param weights double[][]: weights for the given connections
     * @return double[]: output values of specified output node.
     */
    private double[] nodes(double[] inputValues, int outputNode, double[][] weights, double[] bias) {
        // Array to hold output values
        double[] temp = new double[outputNode];
        // Loop through each output node, determining sum of inputs and plugging into sigmoid function
        for (int i = 0; i < outputNode; i++) {
            double z = bias[i];
            for (int j = 0; j < inputValues.length; j++) {
                z += (weights[j][i] * inputValues[j]);
            }
            temp[i] = sig(z);
        }
        return temp;
    }

    /**
     * Performs an entire round of back-propagation through the neural network
     * A HUGE portion of my understanding of this and the functions comes from here:
     * <a href="https://mattmazur.com/2015/03/17/a-step-by-step-backpropagation-example/">Matt Mazur "A Step by Step Backpropagation Example" </a>
     * @param expected double[]: to contain expected outputs
     * @return double: Output error calculated in neural network
     */
    public double backwards(double[] in, double[] expected, double learningRate) {
        if(expected.length != outputs) throw new IllegalArgumentException("Length of data in expected dataset does not match number of outputs");
        if(in.length != inputs) throw new IllegalArgumentException("Length of data in input dataset does not match number of inputs");

        // Array to hold error
        double[] outputNodeDelta = new double[outputs];
        double eTotal = 0;

        // Calculate output error
        for (int i = 0; i < outputs; i++) {
            eTotal += 0.5 * Math.pow(expected[i] - out[i], 2);
        }

        // Calculate weight changes for hidden - output
        for (int i = 0; i < outputs; i++) {
            // Getting node delta for weight calcs and saving it for 'lower' weights
            double gradient = nodeDelta(expected[i], out[i]);
            outputNodeDelta[i] = gradient;
            bias2[i] -= learningRate * gradient;
            for (int j = 0; j < hidden; j++) {
                d2[j][i] = learningRate * gradient * hid[j];
            }
        }

        // Calculate weight changes for input - hidden
        for (int i = 0; i < hidden; i++) {
            for (int j = 0; j < inputs; j++) {
                // Old weight - learning rate * (gradient of eTotal with respect to old weight)
                double outputEffect = 0;
                for (int k = 0; k < outputNodeDelta.length; k++) {
                    outputEffect += outputNodeDelta[k] * w2[i][k];
                }
                double gradient = outputEffect * hid[i] * (1 - hid[i]);
                bias1[i] = learningRate * gradient;
                d1[j][i] = learningRate * gradient * in[j];
            }
        }

        // Combine weights with change in weights
        for (int i = 0; i < w1.length; i++) {
            for (int j = 0; j < w1[i].length; j++) {
                w1[i][j] -= d1[i][j];
            }
        }
        for (int i = 0; i < w2.length; i++) {
            for (int j = 0; j < w2[i].length; j++) {
                w2[i][j] -= d2[i][j];
            }
        }

        return eTotal;
    }


    /**
     * Gets the sigmoidal value at a given x value.
     * @param x double: x value to find sigmoidal y value at.
     * @return double: y value at specified x value
     */
    private double sig(double x) {
        return 1/(1+Math.exp(-x));
    }

    /**
     * Gets the 'node delta' of an output.
     * @param expected double: Expected value from the output node.
     * @param output double: Output value of the output node.
     * @return double: node 'delta' value
     */
    private double nodeDelta(double expected, double output) {
        return ((output - expected) * output * (1 - output));
    }

}
