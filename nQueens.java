import java.util.*;

public final class nQueens {

    public static int n;
    Scanner scanner;

    public nQueens() {
        //Init variables
        getInput();
        //seeder to generate random value
        Random ra = new Random();
        int seed = 0;
        while(seed==0){
            seed = ra.nextInt(10);
            System.out.print(seed)
        }
        Random rand = new Random(seed);
        int[] board = new int[n]; //Array to represent seeded board
        //Filling array using Math.Random and seed value
        for (int i = 0; i < n; i++) {
            board[i] = rand.nextInt(n) + 1;
        }
        // Output for n = 4
        // board[0] = 2;
        // board[1] = 1;
        // board[2] = 3;
        // board[3] = 4;
        // number of conflicts: 4

        // Output for n=6
        // board[0] = 2;
        // board[1] = 5;
        // board[2] = 6;
        // board[3] = 1;
        // board[4] = 3;
        // board[5] = 4;
        // number of conflicts: 3
        Board initial = new Board(board); //Creating new Board object
        PriorityQueue<Board> PQ; //PriorityQueue to store children of b
        PQ = initial.generateChildren();
        System.out.println("\nA* Search\n");

        // Display init board
        System.out.println("Initial Board: ");
        initial.printBoard();

        //apply Astar to initial board
        AStar.performSearch(initial);

        //check conflicts
        AStar.checkH(initial, n);
    }

    /**
     * Method to get user input for problem size and seed.
     */
    private void getInput() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter the size of the problem (n >= 4): ");
            try {
                n = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("\nError: invalid input.");
            }
            if (n < 4) {
                System.out.println("Error: n must be greater than or equal to 4.");
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        nQueens q = new nQueens();
    }
}
