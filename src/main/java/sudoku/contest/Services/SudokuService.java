package sudoku.contest.Services;

import org.springframework.stereotype.Service;
import sudoku.contest.Models.Sudoku;

@Service
public class SudokuService {

    public Sudoku generateGame(Sudoku sudoku) {
        boolean solvable = false;
        while (!solvable) {
            int amount = 0;
            final int SIZE = sudoku.getSIZE();
            int[][] gameBoard = new int[SIZE][SIZE];
            while (amount < 36) {
                int randomColumn = (int) Math.floor(Math.random() * SIZE);
                int randomLine = (int) Math.floor(Math.random() * SIZE);
                if (gameBoard[randomLine][randomColumn] == 0) {
                    int randomValue = (int) Math.floor(Math.random() * SIZE) + 1;
                    if (isSafe(gameBoard, randomLine, randomColumn, randomValue)) {
                        gameBoard[randomLine][randomColumn] = randomValue;
                        ++amount;
                    }
                }
            }
            int[][] test = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; ++i) {
                for (int j = 0; j < SIZE; ++j) {
                    test[i][j] = gameBoard[i][j];
                }
            }
            solvable = solvable(test);
            if (solvable) {
                sudoku.setGameBoard(gameBoard);
            }
        }
        return sudoku;
    }

    private boolean isSafe(int[][] gamaBoard, int line, int column, int value) {

        //line and column check
        for (int i = 0; i < gamaBoard.length; ++i) {
            if (gamaBoard[line][i] == value && i != column) {
                return false;
            }
            if (gamaBoard[i][column] == value && i != line) {
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(gamaBoard.length);
        int lineStart = line - line % sqrt;
        int columnStart = column - column % sqrt;

        //block check
        for (int i = lineStart; i < lineStart + sqrt; ++i) {
            for (int j = columnStart; j < columnStart + sqrt; ++j) {
                if (gamaBoard[i][j] == value && (i != line || j != column)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solvable(int[][] sudoku) {
        int line = -1;
        int column = -1;
        boolean filled = true;

        //empty cell check
        for (int i = 0; i < sudoku.length; ++i) {
            for (int j = 0; j < sudoku.length; ++j) {
                if (sudoku[i][j] == 0) {
                    line = i;
                    column = j;
                    filled = false;
                    break;
                }
            }
            if (!filled) {
                break;
            }
        }
        if (filled) {
            return true;
        }

        //backtrack solution
        for (int value = 1; value <= sudoku.length; ++value) {
            if (isSafe(sudoku, line, column, value)) {
                sudoku[line][column] = value;
                if (solvable(sudoku)) {
                    return true;
                } else {
                    sudoku[line][column] = 0;
                }
            }
        }
        return false;
    }

    public void print(int[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public boolean solutionCheck(int[][] gameBoard) {
        for (int i = 0; i < gameBoard.length; ++i) {
            for (int j = 0; j < gameBoard.length; ++j) {
                if (!isSafe(gameBoard, i, j, gameBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
