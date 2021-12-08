package day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class Part2 {

    HashMap<int[][], Boolean> boards = new HashMap<>();
    int counter = 0;

    public static void main(String[] args) throws IOException, URISyntaxException {
        Part2 solver = new Part2();
        Path input = Paths.get(Objects.requireNonNull(day1.Part1.class.getResource("/day4.txt")).toURI());
        var list = Files.readAllLines(input);
        String[] draw = list.get(0).split(",");
        for (int i = 2; i < list.size() - 4;i += 6) {
            solver.fillBoard(list.subList(i,i+5));
        }
        for (String number : draw) {
            solver.draw(Integer.parseInt(number));
        }
    }

    public void draw(int number) {
        for(int[][] board : boards.keySet()) {
            if(boards.get(board))
                continue;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == number) {
                        board[i][j] = 0;
                        checkWin(number, board);
                    }
                }
            }
        }
    }

    public void checkWin(int number, int[][] board) {
        if (check(board)) {
            if (counter == 99)
                calcSolution(board, number);
            boards.put(board, true);
            counter++;
        }
    }

    public static boolean check(int[][] input) {
        for(int i = 0; i<input.length; i++) {
            final int index = i;
            if(Arrays.stream(input[i]).allMatch(val -> val == 0))
                return true;
            if(Arrays.stream(input).allMatch(val -> val[index] == 0))
                return true;
        }
        return false;
    }

    public void calcSolution(int[][] board, int number) {
        int result = 0;
        for (int[] ints : board) {
            for (int j = 0; j < board[0].length; j++) {
                result += ints[j];
            }
        }
        System.out.println(result);
        System.out.println(number);
        System.out.println(result*number);
    }

    public void fillBoard(List<String> sublist) {
        int i = 0;
        int[][] board = new int[5][5];
        for (String s : sublist) {
            String[] helper = s.replaceAll("[ ]+", " ").trim().split(" ");
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = Integer.parseInt(helper[j]);
            }
            i++;
        }
        this.boards.put(board, false);
    }
}
