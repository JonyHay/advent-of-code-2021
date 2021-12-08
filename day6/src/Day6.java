import java.io.BufferedReader;
import java.io.FileReader;

public class Day6 {

    private static final int STARTING_AGE = 8;
    private static final int PARENT_AGE = 6;

    public static void main(String... args) {
        Day6 puzzle = new Day6("input.txt");
        puzzle.model(256);
    }

    private final long[] fish;

    Day6(String filePath) {
        fish = loadStartingState(filePath);
    }

    private long[] loadStartingState(String filePath) {

        long[] fish = new long[STARTING_AGE + 1];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            for (String ageString : bufferedReader.readLine().split(",")) {

                int age = Integer.parseInt(ageString);
                fish[age]++;

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return fish;

    }

    private void model(int days) {

        outputState("Initial State");
        for (int i = 0; i < days; i++) {

            long newBabies = fish[0];
            System.arraycopy(fish, 1, fish, 0, STARTING_AGE);
            fish[PARENT_AGE] += newBabies;
            fish[STARTING_AGE] = newBabies;

            outputState("After\t" + (i + 1) + " days");

        }

    }

    private void outputState(String message) {

        // Diagnostic end of day output
        System.out.print(message);
        System.out.print(":\t");
        System.out.print(sumArray(fish));
        System.out.println();

    }

    private static long sumArray(long[] numbers) {
        long result = 0;
        for (long n : numbers)
            result += n;
        return result;
    }

}
