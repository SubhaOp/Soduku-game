import java.util.Scanner;

public class SudokuSolver {

    private static final int SIZE = 9;

    // ANSI colors for better console output
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        printHeader();

        while (true) {

            System.out.println(CYAN + "\n1. Solve Sudoku" + RESET);
            System.out.println("2. Exit");

            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {

                int[][] board = readBoard(scanner);

                if (board == null) {
                    continue;
                }

                System.out.println("\n" + YELLOW + "Original Sudoku:" + RESET);
                printBoard(board);

                // Check whether the initial Sudoku is valid
                if (!isValidBoard(board)) {
                    System.out.println(
                            RED + "\nInvalid Sudoku puzzle!" + RESET
                    );
                    System.out.println(
                            "The given numbers violate Sudoku rules."
                    );
                    continue;
                }

                // Solve Sudoku
                if (solveSudoku(board)) {

                    System.out.println(
                            GREEN + "\n✓ Sudoku solved successfully!" + RESET
                    );

                    printBoard(board);

                } else {

                    System.out.println(
                            RED + "\n✗ No solution exists for this Sudoku." + RESET
                    );
                }

            } else if (choice.equals("2")) {

                System.out.println(
                        GREEN + "\nThank you for using Sudoku Solver!" + RESET
                );
                break;

            } else {

                System.out.println(
                        RED + "\nInvalid choice. Please enter 1 or 2." + RESET
                );
            }
        }

        scanner.close();
    }

    // Display application header
    private static void printHeader() {

        System.out.println(CYAN);
        System.out.println("=================================");
        System.out.println("        SUDOKU SOLVER");
        System.out.println("=================================");
        System.out.println(RESET);

        System.out.println(
                "Enter numbers from 1-9.");
        System.out.println(
                "Use 0 for empty cells.");
    }

    // Read Sudoku from user
    private static int[][] readBoard(Scanner scanner) {

        int[][] board = new int[SIZE][SIZE];

        System.out.println(
                "\nEnter 9 rows with 9 numbers each."
        );

        System.out.println(
                "Example: 5 3 0 0 7 0 0 0 0"
        );

        for (int row = 0; row < SIZE; row++) {

            while (true) {

                System.out.print("Row " + (row + 1) + ": ");

                String input = scanner.nextLine().trim();

                String[] values = input.split("\\s+");

                // Check number of values
                if (values.length != SIZE) {

                    System.out.println(
                            RED + "Please enter exactly 9 numbers."
                                    + RESET
                    );

                    continue;
                }

                boolean validRow = true;

                for (int col = 0; col < SIZE; col++) {

                    try {

                        int number = Integer.parseInt(values[col]);

                        // Numbers must be between 0 and 9
                        if (number < 0 || number > 9) {

                            validRow = false;
                            break;
                        }

                        board[row][col] = number;

                    } catch (NumberFormatException e) {

                        validRow = false;
                        break;
                    }
                }

                if (validRow) {
                    break;
                }

                System.out.println(
                        RED + "Invalid input. Use only numbers 0-9."
                                + RESET
                );
            }
        }

        return board;
    }

    // Print Sudoku board in a clean format
    private static void printBoard(int[][] board) {

        System.out.println("+-------+-------+-------+");

        for (int row = 0; row < SIZE; row++) {

            System.out.print("| ");

            for (int col = 0; col < SIZE; col++) {

                int value = board[row][col];

                if (value == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(value + " ");
                }

                if ((col + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }

            System.out.println();

            if ((row + 1) % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
        }
    }

    // Check whether the initial Sudoku is valid
    private static boolean isValidBoard(int[][] board) {

        for (int row = 0; row < SIZE; row++) {

            for (int col = 0; col < SIZE; col++) {

                int number = board[row][col];

                if (number == 0) {
                    continue;
                }

                // Temporarily remove the number
                board[row][col] = 0;

                boolean safe = isSafe(board, row, col, number);

                // Restore the number
                board[row][col] = number;

                if (!safe) {
                    return false;
                }
            }
        }

        return true;
    }

    // Check whether a number can be placed
    private static boolean isSafe(
            int[][] board,
            int row,
            int col,
            int number) {

        // Check row
        for (int j = 0; j < SIZE; j++) {

            if (board[row][j] == number) {
                return false;
            }
        }

        // Check column
        for (int i = 0; i < SIZE; i++) {

            if (board[i][col] == number) {
                return false;
            }
        }

        // Find beginning of 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        // Check 3x3 box
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[boxRow + i][boxCol + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    // Sudoku solving using backtracking
    private static boolean solveSudoku(int[][] board) {

        // Find an empty cell
        for (int row = 0; row < SIZE; row++) {

            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == 0) {

                    // Try numbers 1-9
                    for (int number = 1; number <= 9; number++) {

                        if (isSafe(board, row, col, number)) {

                            // Place number
                            board[row][col] = number;

                            // Recursively solve remaining cells
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack
                            board[row][col] = 0;
                        }
                    }

                    // No number worked
                    return false;
                }
            }
        }

        // No empty cells remain
        return true;
    }
}