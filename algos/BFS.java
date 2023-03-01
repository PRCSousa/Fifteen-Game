package algos;
import java.util.Set;
import java.util.TreeSet;

import game.Board;
import game.Moves;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    private int size;
    private Board currState;

    // To avoid cycling through explored nodes, we will add them to a set to compare
    // with the new states.
    private Set<Board> explored;
    
    public BFS(Board initBoard) {
        size = initBoard.getSize();
        currState = new Board(initBoard.getBoard(), initBoard.getSize());
        explored = new TreeSet<Board>();
    }

    public Stack<Board> solve() {
        int count = 0;
        Queue<Board> Q = new LinkedList<Board>();
        Q.offer(currState);

        if (currState.isFinished())
            return Board.rewind(currState); // If the initial state is the solution, return it. Probaably not the best way
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
                    return Board.rewind(newNode);

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
}
