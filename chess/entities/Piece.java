package chess.entities;
import chess.entities.Board;
import chess.entities.Cell;
import chess.entities.Color;


public abstract class Piece {
    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean canMove(Board board, Cell from, Cell to);

    public Color getColor() {
        return color;
    }
}
