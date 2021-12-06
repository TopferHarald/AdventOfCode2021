package day6;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Part2 {

    public static void main(String[] args) throws URISyntaxException, IOException
    {
        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day6.txt")).toURI());
        var list = Files.readAllLines(input);
        String[] fish = list.get(0).split(",");

        long[] fishes = new long[9];

        long newFish;

        for (String s : fish) {
            fishes[Integer.parseInt(s)]++;
        }

        for(int i = 1; i <= 256; i++){
            newFish = fishes[0];
            for (int j = 0; j <= 5; j++) {
                fishes[j] = fishes[j+1];
            }
            fishes[6] = newFish + fishes[7];
            fishes[7] = fishes[8];
            fishes[8] = newFish;
        }

        long totalFishes = 0;
        for(long x : fishes)
            totalFishes += x;
        System.out.println(totalFishes);
    }
}