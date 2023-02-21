
public class Board {
    private int[] board[];
    private int size;
    private int zeroX, zeroY;
    private Board parent;

    Board(int[][] b,int s)
    {
        size = s;
        board = new int[size][size];

        // Insert board setup into Board object
        for (int i = 0; i < s; i++){
            for (int j = 0; j < s; j++){
                board[i][j] = b[i][j];
                if (board[i][j] == 0){
                    zeroY = i; zeroX = j;
                }
            }
        }
    }

    /*--------------------------------------------- */

    public int[][] getBoard(){

        int[] gBoard[] = new int[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                gBoard[i][j] = board[i][j];
            }
        }

        return gBoard;
    }

    public String printBoard(){

        String s = " ";
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                s += board[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

}
