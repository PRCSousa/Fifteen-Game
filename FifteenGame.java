import java.util.Scanner;
import java.util.Stack;

public class FifteenGame {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int size = stdin.nextInt();

        int[] inptBoard[] = new int[size][size];
        
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                inptBoard[i][j] = stdin.nextInt();
            }
        }

        Board initBoard = new Board(inptBoard, size);
        System.out.println("Choose Search Algorithm:\n 1 - BFS\n2 - DFS\n3 - IDFS");
        int opt = stdin.nextInt();
        if(initBoard.isSolvable()){
        switch(opt)
        {
            case 1:
            BFS SolverBFS = new BFS(initBoard);
            Stack<Board> solution1 = SolverBFS.solve();
            Board.printSolution(solution1);
            break;
            case 2:
            DFS SolverDFS = new DFS(initBoard);
            Stack<Board> solution2 = SolverDFS.solve();
            Board.printSolution(solution2);
            break;
            case 3:
            IDFS SolverIDFS = new IDFS(initBoard);
            Stack<Board> solution3 = SolverIDFS.solve();
            Board.printSolution(solution3);
            break;
            default:
                break;

        }}else{System.out.println("Not Solvable.");}
        stdin.close();
    }
}
