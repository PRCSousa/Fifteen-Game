package algos;
import java.util.Stack;

import game.Moves;
import game.Board;

public class DFS {
    private int maxdepth = 12;
    private int size;
    private Board root;

    public DFS(Board initBoard) {
        size = initBoard.getSize();
        root = new Board(initBoard.getBoard(), initBoard.getSize());
    }


    public Stack<Board> solve() {
        root.setDepth(0);
        Stack<Board> stack = new Stack<Board>();
        stack.push(root);

        if (root.isFinished())
            return Board.rewind(root);

        System.out.println("Beginning DFS Search\n");

        while (!stack.empty()) {
            Board currState = stack.pop();
            int depth = currState.getDepth();
            for (Moves move : Moves.values()) {

                Board newNode = new Board(currState.getBoard(), size);
                newNode = newNode.move(move);

                if (newNode == null)
                    continue;

                newNode.setParent(currState);
                newNode.setDepth(depth + 1);

                if (newNode.isFinished())
                    return Board.rewind(newNode);

                if (!stack.contains(newNode) && depth < maxdepth) {
                    stack.push(newNode);

                }
            }
        }
        return null;
    }
}
