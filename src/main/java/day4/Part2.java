package day4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class Part2 {

    List<int[][]> boards = new ArrayList<>();
    List<Integer> doneBoards = new ArrayList<>();
    boolean done;

    public static void main(String[] args) throws IOException, URISyntaxException {
        Part2 solver = new Part2();
        Path input = Paths.get(Objects.requireNonNull(day1.Part1.class.getResource("/day4.txt")).toURI());
        var list = Files.readAllLines(input);
        String[] draw = list.get(0).split(",");
        for (int i = 2; i < list.size() - 4;i += 6) {
            solver.fillBoard(list.subList(i,i+5));
        }
        for (String number : draw) {
            if (!solver.done) {
                solver.draw(Integer.parseInt(number));
            }
        }
    }

    public void draw(int number) {
        for(int[][] board : boards) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == number && !done) {
                        board[i][j] = 0;
                        checkWin(number);
                    }
                }
            }
        }
    }

    public void checkWin(int number) {
        int sum = 0;
        for(int[][] board : boards) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    sum += board[j][i];
                }
                if (IntStream.of(board[i]).sum() == 0 || sum == 0) {
                    if (!doneBoards.contains(boards.indexOf(board))) {
                        doneBoards.add(boards.indexOf(board));
                        if(doneBoards.size() == boards.size())
                            calcSolution(board, number);
                    }
                } else
                    sum = 0;
            }
        }
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
        this.boards.add(board);
    }
}
