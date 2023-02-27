import java.util.Set;
import java.util.TreeSet;
import java.util.Stack;

public class IDFS {
    private int maxdepth = 15;
    private int size;
    private Board root;
    private Set<Board> explored; // To avoid cycling through explored nodes, we will add them to a set to compare
                                 // with the new states.

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
            explored = new TreeSet<Board>();

            stack.push(root);
            explored.add(root);

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

                    if (!explored.contains(newNode) && !stack.contains(newNode) && depth < iter) {
                        stack.push(newNode);
                        explored.add(newNode);

                    }
                }
            }
        }
        return null;
    }
}