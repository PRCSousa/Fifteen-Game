import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Greedy {
    private int size;
    private Board currState;
    private Set<Board> explored; // To avoid cycling through explored nodes, we will add them to a set to compare
                                 // with the new states.

    Greedy(Board initBoard) {
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
        System.out.println("Beginning Greedy Search\n");

        while()

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
