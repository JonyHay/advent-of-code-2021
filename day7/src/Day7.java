import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 {

    public static void main(String... args) {
        new Day7("input.txt").findIdealPosition();
    }

    public Day7(String startingStateFilePath) {
        crabSubmarines = loadStartingState(startingStateFilePath);
    }

    private final long[] crabSubmarines;

    private long[] loadStartingState(String filePath) {

        long[] crabSubmarines = new long[0];

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            List<Integer> crabSubmarinePositions = Arrays.stream(bufferedReader.readLine().split(",")).map(Integer::parseInt).collect(Collectors.toList());
            int maxPosition = crabSubmarinePositions.stream().max(Integer::compareTo).get(); // Assumed to be present!
            crabSubmarines = new long[maxPosition + 1];

            for (int crabSubmarinePosition : crabSubmarinePositions) {
                crabSubmarines[crabSubmarinePosition] ++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return crabSubmarines;

    }

    public void findIdealPosition() {

        // Work out the fuel costs to move X positions
        // [1] = 1
        // [2] = 3 ...
        int[] fuelCostsByMoves = new int[crabSubmarines.length];
        for (int i = 1; i < fuelCostsByMoves.length; i++) {
            for (int j = i; j > 0; j--)
                fuelCostsByMoves[i] += j;
        }

        // Simple search, store the position of the best score so far
        int bestPositionFound = 0;
        long bestPositionScore = Long.MAX_VALUE;
        for (int suggestPosition = 0; suggestPosition < crabSubmarines.length; suggestPosition++) {

            // Calculate cost to get to the suggested position
            long positionScore = 0;
            for (int i = 0; i < crabSubmarines.length; i++) {

                int positionsDifference = Math.abs(suggestPosition - i);
                long fuelToPosition = fuelCostsByMoves[positionsDifference] * crabSubmarines[i];
                positionScore += fuelToPosition;

            }

            if (positionScore < bestPositionScore) {
                bestPositionScore = positionScore;
                bestPositionFound = suggestPosition;
            }

        }

        System.out.println("Best position found was " + bestPositionFound + " with a score of " + bestPositionScore);

    }

}
