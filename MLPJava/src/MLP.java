


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
    public void train(int epochs, double[][] input, double[][] expected) {
        double currentRate = 0.1;
        double delta = currentRate / (epochs + 1);
        if (epochs < 1) throw new IllegalArgumentException("Must have at least one epoch");
        if(input.length != expected.length) throw new IllegalArgumentException("Input and expected dataset sizes are different");

        for (int currentEpoch = 0; currentEpoch < epochs; currentEpoch++) {
            for(int i = 0; i < input.length; i++) {
                p.forward(input[i]);
                p.backwards(input[i], expected[i], currentRate);
            }
            currentRate -= delta;
        }
    }

    /**
     * Trains the perceptron based off of a dataset of inputs and expected outputs.
     * @param epochs int: Number of iterations AKA 'Epochs' to run the MLP through.
     * @param input double[]: Input data for MLP.
     * @param expected double[]: Expected output for a given input. Input and expected arrays must match index or training will be running on incorrect information.
     */
    public void train(int epochs, double[] input, double[] expected) {
        double currentRate = 0.1;
        double delta = currentRate / (epochs + 1);

        if (epochs < 1) throw new IllegalArgumentException("Must have at least one epoch");
        if(input.length != expected.length) throw new IllegalArgumentException("Input and expected dataset sizes are different");

        for (int currentEpoch = 0; currentEpoch < epochs; currentEpoch++) {
            p.forward(input);
            p.backwards(input, expected, currentRate);
            currentRate -= delta;
        }
    }

    /**
     * A function that runs an input through the MLP
     * @param input Input data for perceptron
     * @return Output from perceptron in current state
     */
    public double[] run(double[] input) {
        return p.forward(input);
    }

}
