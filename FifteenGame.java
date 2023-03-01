import java.util.Scanner;
import java.util.Stack;

import algos.BFS;
import algos.DFS;
import algos.Greedy;
import algos.IDFS;
import game.Board;

public class FifteenGame {
    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);
        int size = stdin.nextInt();

        int[] inptBoard[] = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inptBoard[i][j] = stdin.nextInt();
            }
        }

        int[] endBoard[] = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                endBoard[i][j] = stdin.nextInt();
            }
        }

        Board initBoard = new Board(inptBoard, size);
        Board.setFinalBoard(endBoard, size);
        System.out.println("Initial Board:\n");
        System.out.println(initBoard.printBoard());
        System.out.println("\nFinal Board:\n");
        System.out.println(initBoard.printFinalBoard());

        Stack<Board> solution = null;

        if (initBoard.isSolvable()) {
            switch (args[0]) {

                case "BFS":
                    BFS SolverBFS = new BFS(initBoard);
                    solution = SolverBFS.solve();
                    break;

                case "DFS":
                    DFS SolverDFS = new DFS(initBoard);
                    solution = SolverDFS.solve();
                    break;

                case "IDFS":
                    IDFS SolverIDFS = new IDFS(initBoard);
                    solution = SolverIDFS.solve();
                    break;

                case "Greedy-Misplaced":
                    Greedy SolverGreedy = new Greedy(initBoard, 1, endBoard);
                    solution = SolverGreedy.solve();
                    break;

                case "Greedy-Manhattan":
                    Greedy SolverGreedy2 = new Greedy(initBoard, 2, endBoard);
                    solution = SolverGreedy2.solve();
                    break;

                default:
                    break;
            }
            Board.printSolution(solution);
        } else {
            System.out.println("Not Solvable.");
        }
        stdin.close();
    }
}
