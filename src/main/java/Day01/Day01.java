package Day01;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day01 {

    public static void main(String[] args) throws IOException {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader("src/main/resources/Day01-Input.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("   ");
                left.add(lineScanner.nextInt());
                right.add(lineScanner.nextInt());

                lineScanner.close();
            }
        }

        // Part 1: Calculate total distance between sorted lists
        System.out.println("Part 1 Solution: " + part1(left, right));

        // Part 2: Calculate similarity score
        System.out.println("Part 2 Solution: " + part2(left, right));
    }

    private static int part1(List<Integer> left, List<Integer> right) {
        List<Integer> sortedLeft = new ArrayList<>(left);
        List<Integer> sortedRight = new ArrayList<>(right);
        sortedLeft.sort(Integer::compareTo);
        sortedRight.sort(Integer::compareTo);

        int totalDistance = 0;
        for (int i = 0; i < sortedLeft.size(); i++) {
            totalDistance += Math.abs(sortedLeft.get(i) - sortedRight.get(i));
        }

        return totalDistance;
    }

    private static int part2(List<Integer> left, List<Integer> right) {
        Map<Integer, Integer> rightMap = new HashMap<>();
        for (int num : right) {
            if (rightMap.containsKey(num)) {
                rightMap.put(num, rightMap.get(num) + 1);
            } else {
                rightMap.put(num, 1);
            }
        }

        int similarityScore = 0;
        for (int num : left) {
            if (rightMap.containsKey(num)) {
                similarityScore += num * rightMap.getOrDefault(num, 0);
            }
        }

        return similarityScore;
    }
}