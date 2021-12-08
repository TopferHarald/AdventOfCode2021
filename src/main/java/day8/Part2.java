package day8;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Part2 solver = new Part2();
        Path input = Paths.get(Objects.requireNonNull(day5.Part1.class.getResource("/day8.txt")).toURI());
        var list = Files.readAllLines(input);
        List<String> inputD;
        List<String> outputD;
        HashMap<Integer, String> decoded = new HashMap<>();
        int result = 0;
        for (String line : list) {
            inputD = solver.convert(line.split("\\|")[0]);
            outputD = solver.convert(line.split("\\|")[1]);
            inputD.sort(Comparator.comparingInt(String::length).reversed());

            decoded.put(1, inputD.stream().filter(s -> s.length() == 2).findFirst().get());
            decoded.put(7, inputD.stream().filter(s -> s.length() == 3).findFirst().get());
            decoded.put(4, inputD.stream().filter(s -> s.length() == 4).findFirst().get());
            decoded.put(8, inputD.stream().filter(s -> s.length() == 7).findFirst().get());

            for (String s : inputD) {
                if (s.length() == 5) {
                    if(containsAllChars(s, decoded.get(1)) && containsAllChars(s, decoded.get(7)))
                        decoded.put(3, s);
                    else if(containsAllChars(decoded.get(6), s))
                        decoded.put(5, s);
                    else
                        decoded.put(2, s);
                }if (s.length() == 6) {
                    if(containsAllChars(s, decoded.get(1)) && containsAllChars(s, decoded.get(7)) && containsAllChars(s, decoded.get(4)))
                        decoded.put(9, s);
                    else if(containsAllChars(s, decoded.get(1)) && containsAllChars(s, decoded.get(7)))
                        decoded.put(0, s);
                    else
                        decoded.put(6, s);

                }
            }
            Map<String, Integer> swapped = decoded.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
            StringBuilder temp = new StringBuilder();
            for (String s : outputD) {
                if(decoded.containsValue(s))
                    temp.append(swapped.get(s));
            }
            result += Integer.parseInt(temp.toString());
        }
        System.out.println(result);
    }

    public static Set<Character> stringToCharacterSet(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public static boolean containsAllChars
            (String container, String containee) {
        return stringToCharacterSet(container).containsAll
                (stringToCharacterSet(containee));
    }

    public List<String> convert(String string) {
        return Arrays.stream(string.split(" "))
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .map(Part2::orderString)
                .collect(Collectors.toList());
    }

    public static String orderString(String input) {
        char[] tempArray = input.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
}
