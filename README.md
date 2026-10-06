# Sudoku Solver

A simple **Java console application** that solves Sudoku puzzles using the **Backtracking Algorithm**.

## Features

- Takes a 9×9 Sudoku puzzle as input
- Uses `0` to represent empty cells
- Validates numbers according to Sudoku rules
- Automatically finds a solution
- Displays the solved Sudoku
- Uses recursion and backtracking

## Technologies Used

- Java
- 2D Arrays
- Recursion
- Backtracking
- Object-Oriented Programming basics

## How It Works

The program searches for an empty cell and tries numbers from `1` to `9`.

For every number, it checks:

1. Whether the number already exists in the row
2. Whether the number already exists in the column
3. Whether the number already exists in the corresponding 3×3 box

If the number is valid, it is placed in the cell and the program recursively continues solving the puzzle.

If the choice eventually leads to an invalid state, the program removes the number and tries another possibility.

This process is called **backtracking**.

## Example Input

```text
5 3 0 0 7 0 0 0 0
6 0 0 1 9 5 0 0 0
0 9 8 0 0 0 0 6 0
8 0 0 0 6 0 0 0 3
4 0 0 8 0 3 0 0 1
7 0 0 0 2 0 0 0 6
0 6 0 0 0 0 2 8 0
0 0 0 4 1 9 0 0 5
0 0 0 0 8 0 0 7 9
```

## Example Output

```text
5 3 4 6 7 8 9 1 2
6 7 2 1 9 5 3 4 8
1 9 8 3 4 2 5 6 7
8 5 9 7 6 1 4 2 3
4 2 6 8 5 3 7 9 1
7 1 3 9 2 4 8 5 6
9 6 1 5 3 7 2 8 4
2 8 7 4 1 9 6 3 5
3 4 5 2 8 6 1 7 9
```

## How to Run

Compile the program:

```bash
javac src/SudokuSolver.java
```

Run it:

```bash
java -cp src SudokuSolver
```

Then enter the Sudoku puzzle row by row.

## Algorithm

**Backtracking**

### Time Complexity

In the worst case, the algorithm has exponential time complexity because it may need to try many possible combinations.

### Space Complexity

The recursion stack requires space proportional to the number of cells being processed.

## Future Improvements

- Add a graphical user interface
- Add input validation
- Support multiple Sudoku solutions
- Add difficulty levels
- Create a web-based version
- Add a Sudoku puzzle generator

## Author

**Subhajit Paik**

If you found this project useful, consider giving it a ⭐ on GitHub.
