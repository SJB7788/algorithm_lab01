import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public void readDataFile(String matrix) {

    }

    public int getInputSize() {
        return 0;
    }

    public int[][] getBenefitMatrix() {
        int[][] foo = {{1, 2}, {2, 3}};
        return foo;
    }

    public String benefitMatrixToString() {
        return "Hello world";
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
}