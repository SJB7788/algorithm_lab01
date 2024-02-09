/***
 * SeungJae Baek
 * A01339455
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class JobAssignmentFinder {
    private int[][] matrix;
    private int inputSize;

    public void readDataFile(String matrix) {
        try {
            File file = new File(matrix);
            Scanner scanner = new Scanner(file);
            if (inputSize == 0) {
                inputSize = scanner.nextInt();
                this.matrix = new int[inputSize][inputSize];
            }
            while (scanner.hasNextInt()) {
                for (int i = 0; i < inputSize; i++) {
                    for (int j = 0; j < inputSize; j++) {
                        this.matrix[i][j] = scanner.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public int getInputSize() {
        if (inputSize == 0) {
            return -1;
        }
        return this.inputSize;
    }

    public int[][] getBenefitMatrix() {
        return this.matrix;
    }

    public String benefitMatrixToString() {
        return Arrays.deepToString(this.matrix);
    }

    public ArrayList<Integer> getMaxAssignment() {
        ArrayList<Integer> foo = new ArrayList<>();
        foo.add(2);
        return foo;
    }

    public int getMaxAssignmentTotalValue() {
        return 0;
    }

    public int getBenefit(int person, int job) {
        return person + job;
    }

    private ArrayList<ArrayList<Integer>> getPermutations(int N) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();

        /**
         * This isn't a "base case", it's a "null case". This function does not call
         * itself with an argument of zero, but we can't prevent another caller from
         * doing so. It's a weird result, though. The list of permutations has one
         * permutation, but the one permutation is empty (0 elements).
         */
        if (N == 0) {
            ArrayList<Integer> emptyList = new ArrayList<Integer>();
            results.add(emptyList);

        } else if (N == 1) {
            /**
             * Now THIS is the base case. Create an ArrayList with a single integer, and add
             * it to the results list.
             */
            ArrayList<Integer> singleton = new ArrayList<Integer>();
            singleton.add(0);
            results.add(singleton);

        } else {
            /**
             * And: the main part. First a recursive call (this is a decrease and conquer
             * algorithm) to get all the permutations of length N-1.
             */
            ArrayList<ArrayList<Integer>> smallList = getPermutations(N - 1);

            /**
             * Iterate over the list of smaller permutations and insert the value 'N-1' into
             * every permutation in every possible position, adding each new permutation to
             * the big list of permutations.
             */
            for (ArrayList<Integer> perm : smallList) {

                /**
                 * Add 'N-1' -- the biggest number in the new permutation -- at each of the
                 * positions from 0..N-1.
                 */
                for (int i = 0; i < perm.size(); i++) {
                    ArrayList<Integer> newPerm = (ArrayList<Integer>) perm.clone();
                    newPerm.add(i, N - 1);
                    results.add(newPerm);
                }

                /**
                 * Add 'N-1' at the end (i.e. at position "size").
                 */
                ArrayList<Integer> newPerm = (ArrayList<Integer>) perm.clone();
                newPerm.add(N - 1);
                results.add(newPerm);

            }

        }

        /**
         * Nothing left to do except:
         */
        return results;
    }
}