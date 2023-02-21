import java.util.Scanner;

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
        
        System.out.println(initBoard.printBoard());

        for(Moves move: Moves.values())
        {

            Board teste = initBoard.move(move);
            if (teste == null)
            {
                System.out.println("Invalid Move\n");
            }else{
            System.out.println(teste.printBoard());
            }
        }
    }

}
