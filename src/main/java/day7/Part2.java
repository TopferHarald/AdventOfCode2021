package day7;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day7.txt")).toURI());
        var list = Files.readAllLines(input);
        int[] numbers = Arrays.stream(list.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        List<Integer> positions = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        Collections.sort(positions);
        double mean = positions.stream().mapToDouble(a -> a).average().orElse(0.0);
        double result = positions.stream().mapToDouble(a -> Math.abs(a-mean)*(Math.abs(a-mean)+1)/2).sum();
        System.out.println(result);
    }

    //Bruteforce
    /*public int solve(List<Integer> positions) {
        int min = Collections.min(positions);
        int max = Collections.max(positions);
        int result = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            int temp = 0;
            for (int x : positions) {
                temp += Math.abs(x-i)*(Math.abs(x-i)+1)/2;
            }
            if(temp < result)
                result = temp;
        }
        return result;
    }*/
}
