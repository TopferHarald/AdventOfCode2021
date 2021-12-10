package day9;

import org.javatuples.Pair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {

    int[][] heights = new int[102][102];

    public static void main(String[] args) throws IOException, URISyntaxException {
        Part2 solver = new Part2();
        Path input = Paths.get(Objects.requireNonNull(day9.Part2.class.getResource("/day9.txt")).toURI());
        var list = Files.readAllLines(input);

        for (int i = 0; i < solver.heights.length; i++) {
            for (int j = 0; j < solver.heights.length; j++) {
                if (i == 0 || i == solver.heights.length - 1 || j == 0 || j == solver.heights.length - 1) {
                    solver.heights[i][j] = 99;
                } else {
                    int x = Integer.parseInt(String.valueOf(list.get(i-1).charAt(j-1)));
                    solver.heights[i][j] = x;
                }
            }
        }
        List<Integer> basins = new ArrayList<>();
        for (int i = 1; i < solver.heights.length-1; i++) {
            for (int j = 1; j < solver.heights.length-1; j++) {
                if (solver.heights[i][j] < solver.heights[i+1][j] &&
                    solver.heights[i][j] < solver.heights[i-1][j] &&
                    solver.heights[i][j] < solver.heights[i][j+1] &&
                    solver.heights[i][j] < solver.heights[i][j-1]) {
                    List<Pair<Integer, Integer>> alrdyVisited = new ArrayList<>();
                    int x = solver.checkBasin(i, j, alrdyVisited);
                    basins.add(x);
                }
            }
        }
        int result = basins.stream().sorted(Comparator.reverseOrder()).limit(3).reduce(1, (a, b) -> a * b);
        System.out.println(result);
    }


    private int checkBasin(int i, int j, List<Pair<Integer, Integer>> alrdyVisited) {
		if (heights[i][j] == 99 || heights[i][j] == 9 || alrdyVisited.contains(Pair.with(i,j)))
			return 0;

		alrdyVisited.add(Pair.with(i,j));
        return 1 + checkBasin(i-1, j, alrdyVisited) + checkBasin(i+1, j,alrdyVisited) + checkBasin(i, j-1,alrdyVisited) + checkBasin(i, j+1, alrdyVisited);
	}

}
