package chess.entities;

public class Board {
    private final Cell[][] board;

    public Board() {
        board = new Cell[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Cell(row, col);
            }
        }

        setupPieces();
    }

    private void setupPieces() {
        for (int j = 0; j < 8; j++) {
            board[1][j].setPiece(new Pawn(Color.White));
            board[6][j].setPiece(new Pawn(Color.Black));
        }

        board[0][0].setPiece(new Rook(Color.White));
        board[0][1].setPiece(new Knight(Color.White));
        board[0][2].setPiece(new Bishop(Color.White));
        board[0][3].setPiece(new Queen(Color.White));
        board[0][4].setPiece(new King(Color.White));
        board[0][5].setPiece(new Bishop(Color.White));
        board[0][6].setPiece(new Knight(Color.White));
        board[0][7].setPiece(new Rook(Color.White));

        board[7][0].setPiece(new Rook(Color.Black));
        board[7][1].setPiece(new Knight(Color.Black));
        board[7][2].setPiece(new Bishop(Color.Black));
        board[7][3].setPiece(new Queen(Color.Black));
        board[7][4].setPiece(new King(Color.Black));
        board[7][5].setPiece(new Bishop(Color.Black));
        board[7][6].setPiece(new Knight(Color.Black));
        board[7][7].setPiece(new Rook(Color.Black));
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public Piece getPiece(int row, int col) {
        return board[row][col].getPiece();
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col].setPiece(piece);
    }

    public synchronized boolean movePiece(Move move) {
        Cell from = move.getStart();
        Cell to = move.getEnd();
        Piece piece = from.getPiece();

        if (piece == null || !piece.canMove(this, from, to)) {
            return false;
        }

        Piece captured = to.getPiece();

        // make move temporarily
        to.setPiece(piece);
        from.setPiece(null);

        // illegal if own king becomes/stays in check
        if (isKingInCheck(piece.getColor())) {
            from.setPiece(piece);
            to.setPiece(captured);
            return false;
        }

        return true;
    }

    public boolean isCheckmate(Color color) {
        return isKingInCheck(color) && !hasAnyLegalMove(color);
    }

    public boolean isStalemate(Color color) {
        return !isKingInCheck(color) && !hasAnyLegalMove(color);
    }

    public boolean isKingInCheck(Color color) {
        Cell kingCell = findKing(color);
        if (kingCell == null) return false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col].getPiece();

                if (piece != null && piece.getColor() != color) {
                    if (piece.canMove(this, board[row][col], kingCell)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean hasAnyLegalMove(Color color) {
        for (int fromRow = 0; fromRow < 8; fromRow++) {
            for (int fromCol = 0; fromCol < 8; fromCol++) {
                Cell from = board[fromRow][fromCol];
                Piece piece = from.getPiece();

                if (piece == null || piece.getColor() != color) {
                    continue;
                }

                for (int toRow = 0; toRow < 8; toRow++) {
                    for (int toCol = 0; toCol < 8; toCol++) {
                        Cell to = board[toRow][toCol];

                        if (isLegalMove(from, to, color)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isLegalMove(Cell from, Cell to, Color color) {
        Piece piece = from.getPiece();

        if (piece == null || piece.getColor() != color) {
            return false;
        }

        if (!piece.canMove(this, from, to)) {
            return false;
        }

        Piece captured = to.getPiece();

        // make move temporarily
        to.setPiece(piece);
        from.setPiece(null);

        boolean safe = !isKingInCheck(color);

        // undo move
        from.setPiece(piece);
        to.setPiece(captured);

        return safe;
    }

    private Cell findKing(Color color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col].getPiece();
                if (piece instanceof King && piece.getColor() == color) {
                    return board[row][col];
                }
            }
        }
        return null;
    }
}