import java.util.Set;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFS {
    private int size;
    private Board currState;
    private Set<Board> explored;

    BFS(Board initBoard)
    {
        size = initBoard.getSize();
        currState = new Board(initBoard.getBoard(), initBoard.getSize());
        explored = new TreeSet<Board>();
    }

    public Stack<Board> rewind(Board c)
    {
        Stack<Board> solution = new Stack<Board>();
        while(c != null)
        {
            solution.push(c);
            c = c.getParent();
        }
        printSolution(solution);
        return solution;
    } // After finding the solution in the search, 
      // it will return a stack containing the initial 
      // state on top and the final on the bottom.

    public Stack<Board> solve(){
        Queue<Board> Q = new LinkedList<Board>();
        Q.offer(currState);

        while(Q.size() > 0){

            for(Moves move: Moves.values())
            {
                Board newNode = new Board(currState.getBoard(), size);

                newNode = newNode.move(move);

                if (newNode == null) continue;
                newNode.setParent(currState);

                if(newNode.isFinished())return rewind(newNode);


                if(!explored.contains(newNode)) {
                    explored.add(newNode);
                    Q.offer(newNode);
                }
            }
            currState = Q.remove();
        }
        return null;
    }

    public void printSolution(Stack<Board> stack){
        while (!stack.empty())
        {
            Board g = stack.pop();
            System.out.println(g.printBoard());
        }
    }
}
