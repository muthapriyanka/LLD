package tictactoe;

import tictactoe.entities.Player;
import tictactoe.entities.Symbol;

public class TicTacToeDemo {
    public static void main(String[] args) {

        TicTacToeSystem system = TicTacToeSystem.getInstance();

        Player alice = new Player("Alice", Symbol.X);
        Player bob   = new Player("Bob", Symbol.O);

        // --- GAME 1: Alice wins ---
        System.out.println("--- GAME 1: Alice (X) vs. Bob (O) ---");
        system.createGame(alice, bob);
        system.printBoard();

        system.makeMove(alice, 0, 0);
        system.makeMove(bob,   1, 0);
        system.makeMove(alice, 0, 1);
        system.makeMove(bob,   1, 1);
        system.makeMove(alice, 0, 2); // Alice wins
        system.printBoard();
        System.out.println("Status: " + system.getStatus());
        System.out.println("----------------------------------------\n");

        // --- GAME 2: Bob wins ---
        System.out.println("--- GAME 2: Alice (X) vs. Bob (O) ---");
        system.createGame(alice, bob);
        system.printBoard();

        system.makeMove(alice, 0, 0);
        system.makeMove(bob,   1, 0);
        system.makeMove(alice, 0, 1);
        system.makeMove(bob,   1, 1);
        system.makeMove(alice, 2, 2);
        system.makeMove(bob,   1, 2); // Bob wins
        system.printBoard();
        System.out.println("Status: " + system.getStatus());
        System.out.println("----------------------------------------\n");
    }
}