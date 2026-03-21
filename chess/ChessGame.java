package chess;

import chess.entities.Board;
import chess.entities.Color;
import chess.entities.Move;
import chess.entities.Piece;
import chess.entities.Player;
import java.util.Scanner;

public class ChessGame {
    private final Board board;
    private Player whitePlayer, blackPlayer;
    private Player currentPlayer;

    public ChessGame() {
        board = new Board();
    }

    public void setPlayers(String playerWhiteName, String playerBlackName) {
        this.whitePlayer = new Player(playerWhiteName, Color.White);
        this.blackPlayer = new Player(playerBlackName, Color.Black);
        this.currentPlayer = whitePlayer;
    }

    private void switchTurn() {
        currentPlayer = currentPlayer == whitePlayer ? blackPlayer : whitePlayer;
    }

    private Move getPlayerMove(Player player) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter source row: ");
        int sourceRow = scanner.nextInt();
        System.out.print("Enter source column: ");
        int sourceCol = scanner.nextInt();
        System.out.print("Enter destination row: ");
        int destRow = scanner.nextInt();
        System.out.print("Enter destination column: ");
        int destCol = scanner.nextInt();

        Piece piece = board.getPiece(sourceRow, sourceCol);
        if (piece == null || piece.getColor() != player.getColor()) {
            throw new IllegalArgumentException("Invalid piece selection!");
        }

        return new Move(board.getCell(sourceRow, sourceCol), board.getCell(destRow, destCol));
    }

    public void start() {
        while (!isGameOver()) {
            Player player = currentPlayer;
            System.out.println(player.getName() + "'s turn.");

            try {
                Move move = getPlayerMove(player);
                boolean moved = board.movePiece(move);

                if (!moved) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                Color opponentColor = (player.getColor() == Color.White) ? Color.Black : Color.White;
                if (board.isKingInCheck(opponentColor)) {
                    System.out.println("Check!");
                }

                switchTurn();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        displayResult();
    }

    private boolean isGameOver() {
        return board.isCheckmate(whitePlayer.getColor()) ||
               board.isCheckmate(blackPlayer.getColor()) ||
               board.isStalemate(whitePlayer.getColor()) ||
               board.isStalemate(blackPlayer.getColor());
    }

    private void displayResult() {
        if (board.isCheckmate(Color.White)) {
            System.out.println("Black wins by checkmate!");
        } else if (board.isCheckmate(Color.Black)) {
            System.out.println("White wins by checkmate!");
        } else {
            System.out.println("The game ends in a stalemate!");
        }
    }
}