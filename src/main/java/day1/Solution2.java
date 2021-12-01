package day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Solution2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Solution1.class.getResource("/day1.txt").toURI());
        var list = new ArrayList<Integer>();
        Files.lines(input).forEach(x -> list.add(Integer.parseInt(x)));
        int counter = (int) IntStream.range(0, list.size() - 3).filter(i -> list.get(i)+list.get(i+1)+list.get(i+2) < list.get(i+1)+list.get(i+2)+list.get(i+3)).count();
        System.out.println(counter);
    }
}