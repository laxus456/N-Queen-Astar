import java.util.PriorityQueue;

class AStar {

    /**
     * Function that executes a search through A* algorithm to find optimal solution to the N Queens
     * problem.
     * @param b Board to perform the search on.
     */
    public static void performSearch(Board initial, int n) {
        //Init variables
        int bestHV = Integer.MAX_VALUE, bestIndex = -1;
        Board bestChild;
        //Evaluate Board
        PriorityQueue<Board> closed = new PriorityQueue();
        //Evaluated Board
        PriorityQueue<Board> open = new PriorityQueue();

        open.add(initial); //Add initial board to list

        while (!open.isEmpty()) {
            //Apply Heuristic to first board against all other boards
            bestChild = open.poll();
            if (bestChild.getHV() == 0) { // if no conflicts from queen placement, print board
                System.out.println("=====================");
                System.out.println("Solution found. ");
                System.out.println("=====================");
                bestChild.printBoard();
                checkH(bestChild, n);
                return;
            }
            //Finished Evaluation
            closed.add(bestChild);
            PriorityQueue<Board> childrenOfCurrent = bestChild.generateChildren();
            childrenOfCurrent.stream().filter((b) -> !(closed.contains(b)))
                    .filter((b) -> (!closed.contains(b))).forEach((b) -> {
                open.add(b);
            });
            bestHV = Integer.MAX_VALUE; //Reset values
            bestIndex = -1;
        }
        //if not, no solution was found
        System.out.println("No solution found.");
    }

    /**
     * Function that calculates the number of conflicts of Queen's
     * placement on board
     */
    public static void checkH(Board initial, int n){
        int[] board = initial.getBState();
        int conflict = 0;
        for(int i=0; i<n; i++){
            for (int j= i+1; j < n; j++) {
                if (j!=i) { //skip current queen placement
                    //If two queens have the same row or If two queens can attack using knight's move and queen i j is not 0
                    if (Math.abs(board[j] - board[i]) == Math.abs(j - i) || Math.abs(board[j] - board[i]) + Math.abs(j - i) == 3 && board[i] != 0 && board[j] != 0) {
                        conflict++; //There is a conflict
                    }
                }
            }
        }
        System.out.printf("Number of Conflicts: %d",conflict);
    }
}
