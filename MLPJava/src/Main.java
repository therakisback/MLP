import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        MLP mlp = new MLP(2, 6, 1);

        // Input dataset for XOR
        double[][] input = {
                {0, 0},
                {1, 0},
                {0, 1},
                {1, 1}
                };
        double[][] expected = {
                {0},
                {1},
                {1},
                {0},
        };

        for (int i = 0; i < expected.length; i++) {
            System.out.println(Arrays.toString(mlp.run(input[i])));
        }
        System.out.println();
        mlp.train(1000000, input, expected);

        for (int i = 0; i < expected.length; i++) {
            System.out.println(Arrays.toString(mlp.run(input[i])));
        }



    }
}