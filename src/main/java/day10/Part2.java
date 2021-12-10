package day10;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;

public class Part2 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Objects.requireNonNull(day9.Part2.class.getResource("/day10.txt")).toURI());
        var list = Files.readAllLines(input);
        ArrayList<Long> scores = new ArrayList<>();
        for (String s : list) {
            long score = check(s);
            if(score != -1)
                scores.add(score);
        }
        Collections.sort(scores);
        System.out.println(scores.get(scores.size()/2));
    }

    public static long check(String s) {
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '<') {
                brackets.push(s.charAt(i));
            }else{
                if(s.charAt(i) == ')' && !brackets.pop().equals('(') ||
                    s.charAt(i) == ']' && !brackets.pop().equals('[') ||
                    s.charAt(i) == '}' && !brackets.pop().equals('{') ||
                    s.charAt(i) == '>' && !brackets.pop().equals('<'))
                        return -1;
            }
        }
        return getScore(brackets);
    }

    public static long getScore(Stack<Character> brackets) {
        long score = 0;
        while(!brackets.isEmpty()){
            char c = brackets.pop();
            if(c== '(')
                score = (score * 5) + 1;
            else if(c == '[')
                score = (score * 5) + 2;
            else if(c == '{')
                score = (score * 5) + 3;
            else if(c == '<')
                score = (score * 5) + 4;
        }
        return score;
    }
}
