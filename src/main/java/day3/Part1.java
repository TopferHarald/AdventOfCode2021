package day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Objects.requireNonNull(day1.Part1.class.getResource("/day3.txt")).toURI());
        var list = Files.readAllLines(input);
        int counter = 0;
        StringBuilder gammaBuilder = new StringBuilder();
        for (int i = 0; i < list.get(0).length(); i++) {
            for (String s : list) {
                if(s.charAt(i) == '0')
                    counter++;
            }
            if (counter > list.size() / 2) {
                gammaBuilder.append("0");
            } else {
                gammaBuilder.append("1");
            }
            counter = 0;
        }
        String gamma = gammaBuilder.toString();
        String epsilon = gamma.replace('0', '2').replace('1', '0').replace('2', '1');
        System.out.println(Integer.parseInt(gamma, 2)*Integer.parseInt(epsilon, 2));
    }
}
