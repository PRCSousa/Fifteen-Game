import java.util.Set;
import java.util.TreeSet;
import java.util.Stack;

public class IDFS {
    public boolean end = false;
    public int maxdepth = 80;
    private int size;
    private Board root;
    private Set<Board> explored; // To avoid cycling through explored nodes, we will add them to a set to compare
                                 // with the new states.

    IDFS(Board initBoard) {
        size = initBoard.getSize();
        root = new Board(initBoard.getBoard(), initBoard.getSize());
        explored = new TreeSet<Board>();
    }

    public Stack<Board> solve() {
        int depth = 0;
        Stack<Board> stack = new Stack<Board>();
        stack.push(root);
        explored.add(root);

        if (root.isFinished())
            return Board.rewind(root);

        System.out.println("Beginning IDFS Search\n");

        while (!stack.empty()) {
            Board currState = stack.pop();
            depth--;
            for (Moves move : Moves.values()) {

                Board newNode = new Board(currState.getBoard(), size);
                newNode = newNode.move(move);

                if (newNode == null)
                    continue;
                newNode.setParent(currState);

                if (newNode.isFinished())
                    return Board.rewind(newNode);

                if (!explored.contains(newNode) && !stack.contains(newNode) && depth < maxdepth) {
                    depth++;
                    stack.push(newNode);
                    explored.add(newNode);

                }
            }
        }
        return null;
    }
}