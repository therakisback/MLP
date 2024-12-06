import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        MLP mlp = new MLP(2, 6, 1);

        File error = new File("XORerror.txt");


        // XOR
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

        //printComplicatedList(mlp.train(100000, input, expected, error));


        // Vectors
        mlp = new MLP(4, 6, 1);

        error = new File("VectorError2.txt");

        input = new double[400][4];
        expected = new double[400][1];
        double[][] testInput = new double[100][4];
        double[][] testExpected = new double[100][1];

        for (int i = 0; i < 400; i++) {
            input[i] = new double[] {randomVector(), randomVector(), randomVector(), randomVector()};
        }
        for (int i = 0; i < 100; i++) {
            testInput[i] = new double[] {randomVector(), randomVector(), randomVector(), randomVector()};
        }

        for (int i = 0; i < 400; i++) {
            expected[i][0] = (Math.sin(input[i][0] - input[i][1] + input[i][2] - input[i][3]) + 1) / 2;
        }

        for (int i = 0; i < 100; i++) {
            testExpected[i][0] = (Math.sin(testInput[i][0] - testInput[i][1] + testInput[i][2] - testInput[i][3]) + 1) / 2;
        }

        // Initial Training
        //printVectorList(mlp.train(1000000, input, expected, error));

        // Test Error calculation
        error = new File("VectorTestError2.txt");
        //printVectorList(mlp.train(1, testInput, testExpected, error));



        // Letter Recognition
        mlp = new MLP(16, 15, 26);
        error = new File("LetterError.txt");
        Scanner scan;
        try {
            scan = new Scanner(new File("LetterData.csv"));

            // I expect memory issues here, so I'm going to try and make it easy to change these values
            final int trainSize = 16000;
            final int testSize = 4000;
            input = new double[trainSize][16];
            expected = new double[trainSize][26];
            testInput = new double[testSize][16];
            testExpected = new double[testSize][26];

            for (int i = 0; i < trainSize; i++) {
                Arrays.fill(expected[i], 0);
            }

            for (int i = 0; i < testSize; i++) {
                Arrays.fill(testExpected[i], 0);
            }

            for (int i = 0; i < trainSize; i++) {
                String inputString = scan.nextLine();
                String[] s = inputString.split(",");
                expected[i][s[0].charAt(0) - 65] = 1;
                for (int j = 1; j < 17; j++) {
                    input[i][j-1] = Double.parseDouble(s[j]);
                }
            }

            for (int i = 0; i < testSize; i++) {
                String inputString = scan.nextLine();
                String[] s = inputString.split(",");
                testExpected[i][s[0].charAt(0) - 65] = 1;
                for (int j = 1; j < 17; j++) {
                    testInput[i][j-1] = Double.parseDouble(s[j]);
                }
            }
            mlp.train(1000, input, expected, error);
            error = new File("LetterTestError.txt");
            printComplicatedList(mlp.train(1, testInput, testExpected, error));

            scan.close();
        }
        catch (FileNotFoundException e) {System.out.println(e.getMessage());}
    }

    /**
     * Prints a List of outputs from the MLP
     * NOTE: This is making this specific use case look better above in main testing,
     * as implement JUnit tests takes time I do not desire to give
     * @param list List<double[][]> to be printed
     */
    private static void printComplicatedList(List<double[][]> list) {
        for (double[][] dd: list) {
            for (double[] d: dd) {
                String s = Arrays.toString(d);
                System.out.println(s.substring(1, s.length() - 1));
            }
        }
    }

    /**
     * Prints a List of vector outputs from the MLP
     * NOTE: This is making this specific use case look better above in main testing,
     * as implement JUnit tests takes time I do not desire to give
     * @param list List<double[][]> to be printed
     */
    private static void printVectorList(List<double[][]> list) {
        for (double[][] ddd: list) {
            for (double[] dd: ddd) {
                for(double d: dd) {
                    System.out.println((d * 2) - 1);
                }
            }
        }
    }

    /**
     * Stores a List of vector outputs from the MLP onto a specified file
     * NOTE: This is making this specific use case look better above in main testing,
     * as implement JUnit tests takes time I do not desire to give
     * @param list List<double[]> to be store in the file
     * @param fi File to store vector data on.
     */
    private static void storeVector(List<double[]> list, File fi) {
        try {
            FileWriter fw = new FileWriter(fi);
            for (double[] dd : list) {
                for (double d : dd) {
                    fw.write(d + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {System.out.println(e.getMessage());}
    }

    /**
     * Generates a random double between the values of -1 and 1
     * @return double value between (-1, -1)
     */
    public static double randomVector() {
        return (Math.random() * 2) - 1;
    }
}