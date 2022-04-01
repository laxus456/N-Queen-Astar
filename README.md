
# N-Queens

Java implementation of a program which solves the N Queens problem using  A* search Algorithm with the number of conflicting queen pairs as the heuristic.

The input for the program should consist of a single number, indicating the size of the board (for example, 8 means a standard 8 by 8 chessboard with 8 superqueens). The output will draw an optimal positioning of the superqueens on the board, and it should state the number of pairs of superqueens that attack each other.

## Running the N-Queens Program

This project was tested on VS Code with a java version "17.0.2" 2022-01-18 LTS. To run the program, type in
the command

```
javac nQueens.java

java nQueens.java
```

The program will ask to input the dimension of the n x n board, then will display the
initial boardstate and followed the solution generated by the A* Algorithm
(With Heuristic based on number of conflicts of Queen Pairs).

## Sample outputs
```
Please enter the size of the problem (n >= 4): 4

A* Search      

Initial Board: 
3       1       2       2
_       Q       _       _

_       _       Q       Q

Q       _       _       _

_       _       _       _

=====================
Solution found.
=====================
3       1       4       2
_       Q       _       _

_       _       _       Q

Q       _       _       _

_       _       Q       _

Number of Conflicts: 4
```

```
Please enter the size of the problem (n >= 4): 6

A* Search

Initial Board: 
5       5       5       2       1       3
_       _       _       _       Q       _

_       _       _       Q       _       _

_       _       _       _       _       Q

_       _       _       _       _       _

Q       Q       Q       _       _       _

_       _       _       _       _       _

=====================
Solution found.
=====================
4       1       5       2       6       3
_       Q       _       _       _       _

_       _       _       Q       _       _

_       _       _       _       _       Q

Q       _       _       _       _       _

_       _       Q       _       _       _

_       _       _       _       Q       _

Number of Conflicts: 4
```

