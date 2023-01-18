package sudoku.contest.Models;

public class Sudoku {

    private final int SIZE = 9;
    private int[][] gameBoard = new int[SIZE][SIZE];


    public Sudoku() {
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public int[][] getGameBoard() {
        return gameBoard;
    }
}
