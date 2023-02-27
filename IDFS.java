import java.util.Stack;

public class IDFS {
    private int maxdepth = 15;
    private int size;
    private Board root;

    IDFS(Board initBoard) {
        size = initBoard.getSize();
        root = new Board(initBoard.getBoard(), initBoard.getSize());
    }

    public Stack<Board> solve() {

        if (root.isFinished())
            return Board.rewind(root);

        System.out.println("Beginning IDFS Search\n");
        for (int iter = 1; iter < maxdepth; iter++) {
            root.setDepth(0);

            Stack<Board> stack = new Stack<Board>();

            stack.push(root);

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

                    if (!stack.contains(newNode) && depth < iter) {
                        stack.push(newNode);

                    }
                }
            }
        }
        return null;
    }
}