package algos;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import game.Board;
import game.Moves;

public class Astar {
    private int size;
    private Board root;
    private int[][] finalState;
    private int heur;
    private Set<Board> explored;
    private PriorityQueue<Board> PQ = new PriorityQueue<>(Comparator.comparingInt(this::heuristic));

    public int heuristic(Board a) {
        int[] board[] = a.getBoard();
        int count = 0;
        if (heur == 1) {
            // sum of squares in the wrong place
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] != finalState[i][j])
                        count++;
                }
            }
            return count;
        } else {
            // manhattan distance (is this the best way to do this?)
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] != finalState[i][j]) {
                        for (int k = 0; k < size; k++) {
                            for (int l = 0; l < size; l++) {
                                if (board[i][j] == finalState[k][l]) {
                                    count += Math.abs(i - k) + Math.abs(j - l);
                                }
                            }
                        }
                    }
                }
            }
            return count;
        }
    }
    public Astar(Board initBoard, int h, int[][] fs) {
        size = initBoard.getSize();
        root = new Board(initBoard.getBoard(), initBoard.getSize());
        finalState = fs;
        heur = h;
    }
    public Stack<Board> solve() {

        explored = new TreeSet<Board>();
        Stack<Board> solution = new Stack<Board>();
        PQ.add(root);

        while (!PQ.isEmpty()) {
            
            Board current = PQ.remove();

            if (current.isFinished()) {

                while (current != null) {

                    solution.push(current);
                    current = current.getParent();
                }
                return solution;
            }

            explored.add(current);

            for (Moves move: Moves.values()) {

                Board next = current.move(move);

                if (!explored.contains(next) && !PQ.contains(next)) {
                    next.setParent(current);
                    PQ.add(next);
                }
            }
            
        }
        return null;
    }
    
}
