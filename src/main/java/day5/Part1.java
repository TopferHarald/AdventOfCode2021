package day5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Part1 {

    int[][] area = new int[999][999];

    public static void main(String[] args) throws URISyntaxException, IOException {
        Part1 solver = new Part1();
        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day5.txt")).toURI());
        var list = Files.readAllLines(input);
        for (String s : list) {
            solver.draw(s);
        }
        System.out.println(solver.checkSol());
    }

    public void draw(String input) {
        String[] s = input.replaceAll("\\s+","").split("->");
        int x1 = Integer.parseInt(s[0].split(",")[0]);
        int y1 = Integer.parseInt(s[0].split(",")[1]);
        int x2 = Integer.parseInt(s[1].split(",")[0]);
        int y2 = Integer.parseInt(s[1].split(",")[1]);
        if (x1 == x2) {
            while (y1 != y2) {
                area[x1][y1] += 1;
                if(y1 < y2) y1++;
                else y1--;
            }
            area[x1][y1] += 1;
        } else if (y1 == y2) {
            while (x1 != x2) {
                area[x1][y1] += 1;
                if(x1 < x2) x1++;
                else x1--;
            }
            area[x1][y1] += 1;
        }
    }

    public int checkSol() {
        int counter = 0;
        for (int[] ints : area) {
            for (int anInt : ints) {
                if (anInt >= 2) counter++;
            }
        }
        return counter;
    }
}
