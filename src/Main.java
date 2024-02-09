public class Main {
    public static void main(String[] args) {
        JobAssignmentFinder jobAssignmentFinder = new JobAssignmentFinder();
        jobAssignmentFinder.readDataFile("src/data5.txt");
        String matrix = jobAssignmentFinder.benefitMatrixToString();
        System.out.println(matrix);
        System.out.println(jobAssignmentFinder.getMaxAssignment());
        System.out.println(jobAssignmentFinder.getMaxAssignmentTotalValue());
        System.out.println(jobAssignmentFinder.getBenefit(0, 4));
    }
}
