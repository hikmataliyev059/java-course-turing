package az.classes.module01.games;

import java.util.Random;
import java.util.Scanner;

public class ShipBattleGame {

    private static final int BOARD_SIZE = 5;
    private static final char EMPTY = '~';
    private static final char SHIP = 'S';
    private static final char HIT = 'X';
    private static final char MISS = 'O';

    private static int shipRow = -1;
    private static int shipCol = -1;

    public static void main(String[] args) {
        char[][] playerBoard = initializeBoard();
        char[][] opponentBoard = initializeBoard();

        placeShip(opponentBoard);

        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            printBoard(playerBoard);
            System.out.println("Attack the opponent! Enter coordinates (row and column):");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
                System.out.println("Entered coordinates are out of bounds! Please try again.");
                continue;
            }

            if (opponentBoard[row][col] == SHIP) {
                System.out.println("Ship hit!");
                opponentBoard[row][col] = HIT;
            } else if (opponentBoard[row][col] == EMPTY) {
                System.out.println("Empty spot!");
                opponentBoard[row][col] = MISS;

                System.out.println("Previous ship coordinates: (" + shipRow + ", " + shipCol + ")");

                moveShip(opponentBoard);
                System.out.println("Ship has been moved to a new location.");
                System.out.println("New ship coordinates: (" + shipRow + ", " + shipCol + ")");
            } else {
                System.out.println("This point has already been attacked. Choose another coordinate.");
                continue;
            }

            gameOver = checkGameOver(opponentBoard);
            if (gameOver) {
                System.out.println("You won!");
                printBoard(opponentBoard);
            }
        }
        scanner.close();
    }

    private static char[][] initializeBoard() {
        char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        return board;
    }

    private static void placeShip(char[][] board) {
        Random random = new Random();
        shipRow = random.nextInt(BOARD_SIZE);
        shipCol = random.nextInt(BOARD_SIZE);

        board[shipRow][shipCol] = SHIP;
    }

    private static void moveShip(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == SHIP) {
                    board[i][j] = EMPTY;
                }
            }
        }

        Random random = new Random();
        shipRow = random.nextInt(BOARD_SIZE);
        shipCol = random.nextInt(BOARD_SIZE);

        board[shipRow][shipCol] = SHIP;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean checkGameOver(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == SHIP) {
                    return false;
                }
            }
        }
        return true;
    }

}
