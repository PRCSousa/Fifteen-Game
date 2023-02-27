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

        stdin.close();

        if(initBoard.isSolvable()){

           /*  BFS Solver = new BFS(initBoard);
            Stack<Board> solution = Solver.solve();
            BFS.printSolution(solution);*/

            IDFS Solver = new IDFS(initBoard);
            Stack<Board> solution = Solver.solve();
            Board.printSolution(solution);

        }else{
        
            System.out.println("Not solvable");
        }
    }
}
