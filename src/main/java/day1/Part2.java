package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Objects.requireNonNull(Part2.class.getResource("/day1.txt")).toURI());
        var list = Files.readAllLines(input).stream().map(Integer::parseInt).collect(Collectors.toList());
        int counter = (int) IntStream.range(0, list.size() - 3).filter(i -> list.get(i) < list.get(i+3)).count();
        System.out.println(counter);
    }
}