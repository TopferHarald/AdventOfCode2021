package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Part1.class.getResource("/day1.txt").toURI());
        var list = new ArrayList<Integer>();
        Files.lines(input).forEach(x -> list.add(Integer.parseInt(x)));
        int counter = (int) IntStream.range(0, list.size() - 1).filter(i -> list.get(i) < list.get(i + 1)).count();
        System.out.println(counter);
    }
}
