import java.util.*;

// This class is for the user to play the game.
// the user can call the ConnectFour class and
// play the game.
public class Client {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        StrategyGame game = new ConnectFour();

        while (!game.isGameOver()) {
            System.out.println(game);
            System.out.printf("Player %d's turn.\n", game.getNextPlayer());
            try {
                game.makeMove(console);
            } catch (IllegalArgumentException ex) {
                System.out.println("**Illegal move: " + ex.getMessage());
            }
        }
        System.out.println(game);
        System.out.printf("Player %d wins!\n", game.getWinner());
    }
}
