import java.util.*;

// Integrace for Connect Four game.
public interface StrategyGame {

    // Gets the game board and returns
    // the board in the correct form.
    public String toString();

    // Calls the getWinner method to check if there
    // is a winner for the game.
    // Returns true if there is and false if not.
    public boolean isGameOver();

    // Checks if there is a winner by going through the board.
    // Returns positive number if there is and -1 if not.
    public int getWinner();

    // Gets the next player who's turn it is.
    // Returns 1 for the first player or 2
    // for the second player.
    public int getNextPlayer();

    // Asks user what they want to do and executed that move
    // for the player (add a token or remove a token).
    // Parameter:
    //      input - for the user's input of what they would
    //              like to do in the game.
    public void makeMove(Scanner input);
}
