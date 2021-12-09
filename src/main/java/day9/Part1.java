package day9;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day9.txt")).toURI());
        var list = Files.readAllLines(input);
        int[][] heights = new int[102][102];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights.length; j++) {
                if (i == 0 || i == heights.length - 1 || j == 0 || j == heights.length - 1) {
                    heights[i][j] = 99;
                } else {
                    int x = Integer.parseInt(String.valueOf(list.get(i-1).charAt(j-1)));
                    heights[i][j] = x;
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < heights.length-1; i++) {
            for (int j = 1; j < heights.length-1; j++) {
                    if(heights[i][j] < heights[i+1][j] && heights[i][j] < heights[i-1][j] && heights[i][j] < heights[i][j+1] && heights[i][j] < heights[i][j-1])
                        result.add(heights[i][j]);
            }
        }
        System.out.println(result.stream().mapToInt(x -> x).sum()+result.size());

    }
}
