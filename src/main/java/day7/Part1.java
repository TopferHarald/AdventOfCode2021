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

public class Part1 {

    public static void main(String[] args) throws URISyntaxException, IOException {
        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day7.txt")).toURI());
        var list = Files.readAllLines(input);
        int[] numbers = Arrays.stream(list.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        List<Integer> positions = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        Collections.sort(positions);
        int median = positions.get(positions.size()/2);
        int sol = positions.stream().mapToInt(a -> Math.abs(a - median)).sum();
        System.out.println(sol);
    }
}
