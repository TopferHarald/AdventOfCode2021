package day8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day8.txt")).toURI());
        var list = Files.readAllLines(input);
        List<String> digits = new ArrayList<>();
        for (String s : list) {
            String[] x = s.split("\\|");
            digits.addAll(Arrays.asList(s.split("\\|")[1].trim().split(" ")));
        }
        int counter = 0;
        for (String s : digits) {
            if(s.length() == 2 || s.length() == 4 || s.length() == 3 || s.length() == 7)
                counter++;
        }
        System.out.println(counter);
    }
}
