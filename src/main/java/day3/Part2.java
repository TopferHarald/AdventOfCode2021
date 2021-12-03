package day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Objects.requireNonNull(day1.Part1.class.getResource("/day3.txt")).toURI());
        var data = Files.readAllLines(input);

        int o2 = new Part2().calcSolution(data, true);
        int co2 = new Part2().calcSolution(data, false);
        System.out.println(o2*co2);
    }

    private int calcSolution(List<String> data, boolean o2) {
        List<String> x = new ArrayList<>(data);
        int pos = 0;
        while (x.size() > 1) {
            String mostCommon = getMostCommon(x);
            for (Iterator<String> it = x.iterator(); it.hasNext();) {
                String next = it.next();
                if(next.charAt(pos) != mostCommon.charAt(pos) && o2)
                    it.remove();
                else if(next.charAt(pos) == mostCommon.charAt(pos) && !o2)
                    it.remove();
            }
            pos++;
        }
        return Integer.parseInt(x.get(0),2);
    }

    private String getMostCommon(List<String> list) {
        int counter = 0;
        StringBuilder gammaBuilder = new StringBuilder();
        for (int i = 0; i < list.get(0).length(); i++) {
            for (String s : list) {
                if(s.charAt(i) == '0')
                    counter++;
            }
            if (counter > list.size() / 2) gammaBuilder.append("0");
            else gammaBuilder.append("1");
            counter = 0;
        }
        return gammaBuilder.toString();
    }
}