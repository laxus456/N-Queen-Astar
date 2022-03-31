import java.util.PriorityQueue;
public class Board implements Comparable<Board> {

    //Variable declarations
    int[] boardState; //Current state of the board
    private int hv; //Heuristic value
    private final int n; //Number of queens
    private PriorityQueue<Board> children; //List of all next possible board states

    /*
     * Constructor for when there is already a board state to be used.
     */
    public Board(int[] board) {
        boardState = new int[board.length]; //Init boardState
        System.arraycopy(board, 0, boardState, 0, board.length);
        n = boardState.length;
        hv = calcHeuristic(); //Heuristic to verify conflicts of queen placement
        children = new PriorityQueue(); //Declare children
    }//constructor

    /*
     * Alternate constructor for when there is not already a board state to be 
     * used.
     */
    public Board(int n) {
        boardState = new int[n];
        for (int i : boardState) {
            boardState[i] = 0;
        }
        this.n = n;
    }

    public int[] getBState() {
        return this.boardState;
    }
    public int getHV() {
        return this.hv;
    }

    public Board getBestChild() {
        return this.children.peek();
    }

    /*
     * Generate a PriorityQueue of all next possible board states (children).
     * @return children A PriorityQueue containing this Board's children.
     */
    public PriorityQueue<Board> generateChildren() {
        //Make a copy of the board state that we can manipulate
        int[] boardCopy = new int[n];
        System.arraycopy(boardState, 0, boardCopy, 0, n);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                //Don't make a new board that doesn't move a queen
                if (boardCopy[i] != j) {
                    boardCopy[i] = j; //Move queen to new spot
                    //Create new child with new queen position
                    children.add(new Board(boardCopy));
                    //Reset copy
                    System.arraycopy(boardState, 0, boardCopy, 0, n);
                }
            }
        }
        return children;
    }

    /*
     * Calulate the heuristic value of this board.
     * @return H Integer result of the heuristic calculations.
     */
    private int calcHeuristic() {
        int H = 0; //Total number of conflicts
        for (int i = 0; i < n; i++) {
            //Add the number of conflicts that queen i has to total
            H += checkConflicts(i);
        }
        return H;
    }

    /*
    * Check the number of conflicts of a given queen.
    * @param c The index of the queen to check.
    * @return conflicts The number of conflicts that the given queen has.
     */
    public int checkConflicts(int c) {
        int conflicts = 0; //Number of conflicts that queen c has
        for (int i = 0; i < n; i++) {
            if (i != c) { //skip current queen placement
                //If two queens have the same row
                if (boardState[i] == boardState[c] && boardState[c] != 0) {
                    conflicts++; //There is a conflict
                }
            }
        }
        return conflicts; //Return the number of conflicts that this queen has
    } 

    /*
     * Print this Board with queens' placement
     */
    public void printBoard() {
        for (int i : boardState) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                if (boardState[i - 1] == j) {
                    System.out.print("Q\t");
                } else {
                    System.out.print("_\t");
                }
            }
            System.out.println("\n");
        }
    } 

    //Overriding compareTo function so that Boards are sorted based on their hv(heuristic result)
    @Override
    public int compareTo(Board b) {
        if (this.getHV() == b.getHV()) {
            return 0;
        }
        return (this.getHV() < b.getHV() ? -1 : 1);
    }

}
