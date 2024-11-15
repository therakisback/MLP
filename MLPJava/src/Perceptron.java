import java.util.*;

public class Perceptron {
    int inputs, hidden, outputs;
    double[][] w1, w2, d1, d2;
    double[] a1, a2, hid, out;


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

        a1 = new double[hidden];
        a2 = new double[outputs];
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


    private double[] forward(double[] in) {
        if(in.length != inputs) throw new IllegalArgumentException();
        // Calculate output for each hidden node
        hid = nodes(inputs, hidden, w1, in);
        // Calculate output nodes
        out = nodes(hidden, outputs, w2, hid);
        return out;
    }

    private double[] backwards(double[] t, double temp) {
        if(t.length != outputs) throw new IllegalArgumentException();

        // Calculate change in weights
            // Calculate error
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < w2[0].length; j++) {

            }
        }
            // Calculations for output layer

            // Calculations for hidden layer


        // Combine weights with change in weights
    }




    private double sig(double z) {
        return 1/(1+Math.exp(-z));
    }

    private double[] nodes(int inputNode, int nodeType, double[][] weights, double[] inputValues) {
        double[] temp = new double[nodeType];
        for (int i = 0; i < nodeType; i++) {
            double z = 0;
            for (int j = 0; j < inputNode; j++) {z += (weights[i][j] * inputValues[j]);}
            temp[i] = sig(z);
        }
        return temp;
    }

    private double derivative(double num) {return num * (1-)}

}
