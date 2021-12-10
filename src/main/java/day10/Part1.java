package day10;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Stack;

public class Part1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Path input = Paths.get(Objects.requireNonNull(day9.Part2.class.getResource("/day10.txt")).toURI());
        var list = Files.readAllLines(input);
        int result = 0;
        for (String s : list) {
            result += check(s);
        }
        System.out.println(result);
    }

    public static int check(String s) {
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '<') {
                brackets.push(s.charAt(i));
            }else{
                if(s.charAt(i) == ')'){
                    if(brackets.peek() == '(')
                        brackets.pop();
                    else
                        return 3;
                }else if(s.charAt(i) == ']'){
                    if(brackets.peek() == '[')
                        brackets.pop();
                    else
                        return 57;
                }else if(s.charAt(i) == '}'){
                    if(brackets.peek() == '{')
                        brackets.pop();
                    else
                        return 1197;
                }else if(s.charAt(i) == '>'){
                    if(brackets.peek() == '<')
                        brackets.pop();
                    else
                        return 25137;
                }
            }
        }
        return 0;
    }
}
