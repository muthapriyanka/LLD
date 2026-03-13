package tictactoe.strategy;

import tictactoe.entities.Board;
import tictactoe.entities.Player;

public class DiagonalWinning implements WinningStrategy {
    @Override
    public boolean checkWinner(Board board, Player player) {
        // Main diagonal
        boolean diagWin = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getCell(i, i).getSymbol() != player.getSymbol()) {
                diagWin = false;
                break;
            }
        }
        if (diagWin) return true;

        // Anti-diagonal
        boolean antiDiagWin = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getCell(i, board.getSize() - 1 - i).getSymbol() != player.getSymbol()) {
                antiDiagWin = false;
                break;
            }
        }
        return antiDiagWin;
    }
}
