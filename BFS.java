import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    private int size;
    private Board currState;

    // To avoid cycling through explored nodes, we will add them to a set to compare
    // with the new states.
    private Set<Board> explored;
    
    BFS(Board initBoard) {
        size = initBoard.getSize();
        currState = new Board(initBoard.getBoard(), initBoard.getSize());
        explored = new TreeSet<Board>();
    }

    public Stack<Board> rewind(Board c) {
        Stack<Board> solution = new Stack<Board>();
        while (c != null) {
            solution.push(c);
            c = c.getParent();
        }
        return solution;
    } // After finding the solution in the search,
      // it will return a stack containing the initial
      // state on top and the final on the bottom.

    public Stack<Board> solve() {
        int count = 0;
        Queue<Board> Q = new LinkedList<Board>();
        Q.offer(currState);

        if (currState.isFinished())
            return rewind(currState); // If the initial state is the solution, return it. Probaably not the best way
                                      // to do it, but it works.
        System.out.println("Beginning BFS Search\n");

        while (Q.size() > 0) {

            for (Moves move : Moves.values()) {
                // Create a new node with the current state.
                // Move the blank space to the new node.
                // If the move is invalid, continue to the next move.
                // If the move is valid, set the parent of the new node to the current state.
                // If the new node is the solution, return the solution node to the rewind
                // function.

                Board newNode = new Board(currState.getBoard(), size);
                newNode = newNode.move(move);

                if (newNode == null)
                    continue;
                newNode.setParent(currState);

                if (newNode.isFinished())
                    return rewind(newNode);

                if (!explored.contains(newNode)) {
                    count++;
                    if (count % 100000 == 0) {
                        System.out.println("Unique Positions Analised: " + count + " / 10461394944000");
                        System.out.println("Current Queue Size: " + Q.size());
                    }
                    explored.add(newNode);
                    Q.offer(newNode);
                }
            }
            // Remove the current state from the queue and set it to the next state.

            currState = Q.remove();
        }
        // If the queue is empty, return null. It means that the solution was not found.

        return null;
    }

    public static void printSolution(Stack<Board> stack) {

        if (stack == null) {
            System.out.println("No solution found.");
            return;
        } else {
            int count = -1; // -1 because the initial state is not counted as a move.
            System.out.println("\nSolution Found!\nInitial Position:");
            while (!stack.empty()) {
                Board g = stack.pop();
                System.out.println(g.printBoard());
                count++;
            }
            System.out.println("Number of moves: " + (count));
        }
    }
}
