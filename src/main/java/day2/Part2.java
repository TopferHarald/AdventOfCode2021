package day2;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Objects.requireNonNull(day1.Part1.class.getResource("/day2.txt")).toURI());
        var list = Files.readAllLines(input);
        int hor = 0, ver = 0, aim = 0;
        for (String s : list) {
            String[] command = s.split(" ");
            switch (command[0]) {
                case "forward":
                    hor += Integer.parseInt(command[1]);
                    ver += Integer.parseInt(command[1])*aim;
                    break;
                case "down":
                    aim += Integer.parseInt(command[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(command[1]);
                    break;
            }
        }
        System.out.println(hor*ver);
    }
}