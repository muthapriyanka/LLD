package tictactoe;

import java.util.Arrays;
import java.util.List;

import tictactoe.entities.Board;
import tictactoe.entities.GameStatus;
import tictactoe.entities.Player;
import tictactoe.strategy.ColumnWinning;
import tictactoe.strategy.DiagonalWinning;
import tictactoe.strategy.RowWinning;
import tictactoe.strategy.WinningStrategy;

public class TicTacToeSystem {

    private static TicTacToeSystem instance;

    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;

    private GameStatus status;
    private List<WinningStrategy> winningStrategies;

    private TicTacToeSystem() {
        // singleton
    }

    public static TicTacToeSystem getInstance() {
        if (instance == null) {
            instance = new TicTacToeSystem();
        }
        return instance;
    }

    // Reset/start a new game
    public void createGame(Player p1, Player p2) {
        if (p1.getSymbol() == p2.getSymbol()) {
            throw new IllegalArgumentException("Both players cannot have the same symbol.");
        }

        // Ensure X is playerX
        if (p1.getSymbol().name().equals("X")) {
            this.playerX = p1;
            this.playerO = p2;
        } else {
            this.playerX = p2;
            this.playerO = p1;
        }

        this.board = new Board(3);
        this.status = GameStatus.IN_PROGRESS;
        this.currentPlayer = playerX;

        this.winningStrategies = Arrays.asList(
                new RowWinning(),
                new ColumnWinning(),
                new DiagonalWinning()
        );
    }

    public boolean makeMove(Player player, int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            System.out.println("Game already finished: " + status);
            return false;
        }

        if (player != currentPlayer) {
            System.out.println("Not your turn: " + player.getName() + ". Current: " + currentPlayer.getName());
            return false;
        }

        boolean placed = board.placeSymbol(row, col, player.getSymbol());
        if (!placed) {
            System.out.println("Invalid move at (" + row + "," + col + ")");
            return false;
        }

        if (checkWinner(player)) {
            status = (player == playerX) ? GameStatus.WINNER_X : GameStatus.WINNER_O;
            return true;
        }

        if (board.isFull()) {
            status = GameStatus.DRAW;
            return true;
        }

        switchPlayer();
        return true;
    }

    private boolean checkWinner(Player player) {
        for (WinningStrategy ws : winningStrategies) {
            if (ws.checkWinner(board, player)) return true;
        }
        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    public void printBoard() {
        board.printBoard();
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

// What .name() Does
// Every enum in Java has a built-in method:
// symbol.name()
// That converts the enum constant to a String.

// Example:

// Symbol.X.name()  →  "X"
// Symbol.O.name()  →  "O"
// So this:
// p1.getSymbol().name().equals("X")
// means:
// Convert enum to String → compare with "X"