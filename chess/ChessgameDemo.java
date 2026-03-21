package chess;

public class ChessgameDemo {
    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        chessGame.setPlayers("Alice", "Bob");
        chessGame.start();
    } 
}
