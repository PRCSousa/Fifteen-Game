
public class Board implements Comparable<Board> {
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

        parent = null;
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

    public int getSize(){
        return size;
    }

    public void setParent(Board p){
        parent = p;
    }
    
    public Board getParent()
    {
        return parent;
    }

    public String printBoard(){

        String s = "";
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){

                // this if is meant to align the output correctly

                if (board[i][j] <= 9){
                    s += board[i][j] + "  ";
                }else{
                    s += board[i][j] + " ";
                }
            }
            s += "\n";
        }
        return s;
    }

    public boolean isFinished(){
        if (board[size-1][size-1] != 0) return false;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
            if (i == size-1 && j == size-1) continue;
            if (board[i][j] != (i*size + j + 1)) return false;
            }
        }
        return true;
    }

    public Board move(Moves m)
    {
        int x = zeroX + m.dx;
        int y = zeroY + m.dy;

        if (x < 0 || x > size-1 || y < 0 || y > size-1)return null;  //If invalid
        
        int[] newBoard[] = getBoard();
        newBoard[zeroY][zeroX] = newBoard[y][x];
        newBoard[y][x] = 0;

        Board newState = new Board(newBoard, size);

        return newState;

    }

    public String getSequence(){
        String v = "";
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                v += board[i][j]+"_";
        return v;
    }

    // comparator
    public int compareTo(Board b){
        return getSequence().compareTo(b.getSequence());
    }

}
