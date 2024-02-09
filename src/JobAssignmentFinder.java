/***
 * SeungJae Baek
 * A01339455
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/***
 * This class is used to find the maximum benefit of a job assignment. It reads
 * a file that contains a matrix of benefits and finds the maximum benefit of
 * the job assignment. It also finds the maximum benefit of a specific person
 * and job.
 * pls 20/20 ty
 */
public class JobAssignmentFinder {
    private int[][] matrix;
    private int inputSize;
    private ArrayList<Integer> maxAssignment;

    /***
     * This method reads the data file and stores the matrix of benefits in a 2D
     * array.
     *
     * @param matrix the file that contains the matrix of benefits
     */
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

    /***
     * This method returns the size of the matrix.
     *
     * @return the size of the matrix as an integer
     */
    public int getInputSize() {
        if (inputSize == 0) {
            return -1;
        }
        return this.inputSize;
    }

    /***
     * This method returns the matrix of benefits.
     *
     * @return the matrix of benefits as a 2D array
     */
    public int[][] getBenefitMatrix() {
        return this.matrix;
    }

    /***
     * This method returns the matrix of benefits as a string.
     *
     * @return the matrix of benefits as a string
     */
    public String benefitMatrixToString() {
        return Arrays.deepToString(this.matrix);
    }

    /***
     * This method returns the maximum assignment of jobs.
     *
     * @return the maximum assignment of jobs as an ArrayList
     */
    public ArrayList<Integer> getMaxAssignment() {
        ArrayList<ArrayList<Integer>> allPermutations = getPermutations(inputSize);
        int maxBenefit = 0;
        for (ArrayList<Integer> permutation : allPermutations) {
            int totalBenefit = 0;
            for (int i = 0; i < inputSize; i++) {
                totalBenefit += matrix[i][permutation.get(i)];
            }
            if (totalBenefit > maxBenefit) {
                maxBenefit = totalBenefit;
                this.maxAssignment = permutation;
            }
        }
        return this.maxAssignment;
    }

    /***
     * This method returns the maximum benefit of the job assignment.
     *
     * @return the maximum benefit of the job assignment as an integer
     */
    public int getMaxAssignmentTotalValue() {
        int totalBenefit = 0;
        for (int i = 0; i < inputSize; i++) {
            totalBenefit += matrix[i][this.maxAssignment.get(i)];
        }
        return totalBenefit;
    }

    /***
     * This method returns the benefit of a specific person and job.
     *
     * @param person the person
     * @param job    the job
     * @return the benefit of the person and job as an integer
     */
    public int getBenefit(int person, int job) {
        ArrayList<ArrayList<Integer>> allPermutations = getPermutations(inputSize);
        int totalBenefit = 0;
        for (ArrayList<Integer> onePermutation : allPermutations) {
            if (onePermutation.get(person) == job) {
                int possibleBenefit = 0;
                for (int i = 0; i < inputSize; i++) {
                    possibleBenefit += matrix[i][onePermutation.get(i)];
                    if (possibleBenefit > totalBenefit) {
                        totalBenefit = possibleBenefit;
                    }
                }
            }
        }
        return totalBenefit;
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