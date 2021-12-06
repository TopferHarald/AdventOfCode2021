package day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Part1 {

    List<Integer> fishList = new ArrayList<>();

    public static void main(String[] args) throws URISyntaxException, IOException
    {
        Part1 solver = new Part1();
        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day6.txt")).toURI());
        var list = Files.readAllLines(input);
        String[] fish = list.get(0).split(",");
        for (String s : fish) {
            solver.fishList.add(Integer.parseInt(s));
        }
        for (int i = 1; i <= 80; i++) {
            solver.fishList = solver.calcDayGrowth();
        }

        System.out.println(solver.fishList.size());
    }

    public List<Integer> calcDayGrowth() {
        List<Integer> newList = new ArrayList<>();
        for (int fish : fishList) {
            if (fish == 0) {
                newList.add(6);
                newList.add(8);
            }else
                newList.add(--fish);
        }
        return newList;
    }
}