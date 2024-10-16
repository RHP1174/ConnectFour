import java.util.*;

// This class implements the StrategyGame interface.
// The user is able to play ConnectFour with this class.
// They are able to add a token or remove their token from
// the bottom of the board on their turn.
public class ConnectFour implements StrategyGame {

    // Fields
    private String[][] board;
    private boolean isYTurn;

    // Constructor
    // Creates the board with the correct arrangement.
    // Sets the player turn to the player with the yellow token.
    // Conditions:
    //      post - constructs new board with pattern.
    //             and sets isYTurn to true.
    public ConnectFour() {
        board = new String[][]{{"⚪", "⚪", "⚪", "⚪", "⚪", "⚪", "⚪"},
                               {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪", "⚪"},
                               {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪", "⚪"},
                               {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪", "⚪"},
                               {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪", "⚪"},
                               {"⚪", "⚪", "⚪", "⚪", "⚪", "⚪", "⚪"}};
        isYTurn = true;
    }

    // If there is a player that has 4 tokens in a row, then the method will
    // return a number greater than 0 to mark that there is a winner for the game.
    // Conditions:
    //      - post - if getWinner() number is greater than 0 (return true).
    //      - post - if getWinner() number is less than 0 (return false).
    public boolean isGameOver() {
        return getWinner() >= 0;
    }

    // Checks if there is a winner by going through the board by
    // checking for a horizontal, vertical, diagonal, or reverse
    // diagonal winner.
    // Returns 1 if the winner is Yellow and 2 if the winner
    // is Red and -1 if there is no winner.
    // Conditions:
    //      pre - there must be a 4-in-a-row pattern horizontally, vertically,
    //            diagonally, or reverse diagonally (returns -1 if not).
    //      post - return -1 if there is no 4-in-a-row pattern.
    public int getWinner() {

        // Horizontal Check
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] != "⚪") {
                    if (board[i][j] == board[i][j + 1] &&
                        board[i][j + 1] == board[i][j + 2] &&
                        board[i][j + 2] == board[i][j + 3]) {

                        return board[i][j] == "\ud83d\udfe1" ? 1 : 2;
                    }
                }
            }
        }

        // Vertical Check
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != "⚪") {
                    if (board[i][j] == board[i + 1][j] &&
                        board[i + 1][j] == board[i + 2][j] &&
                        board[i + 2][j] == board[i + 3][j]) {

                        return board[i][j] == "\ud83d\udfe1" ? 1 : 2;
                    }
                }
            }
        }

        // Diagonal Check
        for (int i = 0; i < board.length - 3; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] != "⚪") {
                    if (board[i][j] == board[i + 1][j + 1] &&
                        board[i + 1][j + 1] == board[i + 2][j + 2] &&
                        board[i + 2][j + 2] == board[i + 3][j + 3]) {

                        return board[i][j] == "\ud83d\udfe1" ? 1 : 2;
                    }
                }
            }
        }

        // Reverse Diagonal Check
        for (int i = 3; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 3; j++) {
                if (board[i][j] != "⚪") {
                    if (board[i][j] == board[i - 1][j + 1] &&
                        board[i - 1][j + 1] == board[i - 2][j + 2] &&
                        board[i - 2][j + 2] == board[i - 3][j + 3]) {

                        return board[i][j] == "\ud83d\udfe1" ? 1 : 2;
                    }
                }
            }
        }

        return -1;
    }

    // Gets the number for the next player who's turn it is.
    // Returns 1 for the player one or 2 for player two.
    // Conditions:
    //      post - returns 1 if it is player one's turn.
    //      post - returns 2 if is is player two's turn.
    public int getNextPlayer() {
        return isYTurn ? 1 : 2;
    }

    // Asks user what they want to do and executed that move
    // for the player. The user is able to add a token to the
    // board or remove a token from the bottom of the board.
    // Parameter:
    //      input - for the user's input of what they would
    //              like to do in the game.
    // Conditions:
    //      pre - the user's choice must be "a" or "b" (throw IllegalArgumentException if not).
    //      post - calls the makeMove method if user's input is "a".
    //      post - calls the removeToken method if user's input is "r".
    public void makeMove(Scanner input) {
        String currPlayer = isYTurn ? "Y" : "R";

        String userChoice = "";

        System.out.print("Would you like to (a)dd a token or (r)emove a token? ");
        userChoice = input.nextLine();

        if (userChoice.equalsIgnoreCase("a") || userChoice.equalsIgnoreCase("r")) {
            System.out.print("Which Column? ");
            String column = input.nextLine();
            int col = Integer.parseInt(column) - 1;

            if (userChoice.equalsIgnoreCase("a")) {
                makeMove(col, currPlayer);
            } else {
                removeToken(col, currPlayer);
            }

            isYTurn = !isYTurn;

        } else {
            throw new IllegalArgumentException("Invalid Input.");
        }
    }

    // This method adds the token to the board depending on the
    // user's choice of column.
    // Parameters:
    //      col - number for the column that the user chose
    //            to drop their token in.
    //      player - String for the player who's turn it is.
    // Conditions:
    //      pre - col is < 0 and >= board[0].length (6). (throws IllegalArgumentException if not).
    //      pre - top of col does not contain a player's token
    //            (throws IllegalArgumentException if there is).
    //      post - Updates the board to the player's token color at the chosen column.
    private void makeMove(int col, String player) {
        if (col < 0 || col >= board[0].length) {
                throw new IllegalArgumentException("Invalid column position: " + (col + 1));
        }

        if (board[0][col] != "⚪") {
            throw new IllegalArgumentException("Column has no space: " + (col + 1));
        }

        int rowNum = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == "⚪") {
                rowNum = i;
            }
        }

        if (player.equalsIgnoreCase("Y")) {
            board[rowNum][col] = "\ud83d\udfe1";
        } else {
            board[rowNum][col] = "\ud83d\udd34";
        }
    }

    // This method removes the user's token from the bottom of the board
    // depending on the user's choice of column.
    // Parameters:
    //      col - number for the column that the user chose
    //            to drop their token in.
    //      player - String for the player who's turn it is.
    // Conditions:
    //      pre - col is < 0 and >= board[0].length (6). (throws IllegalArgumentException if not).
    //      pre - the token at the bottom of the board must belong to the current player
    //            (throws IllegalArgumentException if it is not).
    //      post - Removes the player's token from the bottom of the board and moves tokens above
    //             it down 1 spot.
    private void removeToken(int col, String currPlayer) {
        String player = "";
        if (currPlayer.equalsIgnoreCase("Y")) {
            player = "🟡";
        } else {
            player = "🔴";
        }

        if (col < 0 || col >= board[0].length) {
            throw new IllegalArgumentException("Invalid column position: " + (col + 1));
        }

        if (board[5][col] != player) {
            throw new IllegalArgumentException("Invalid column, not allowed to do that here: "
                + (col + 1));
        }

        for (int i = 5; i > 0; i--) {
            board[i][col] = board[i - 1][col];
        }

        board[0][col] = "⚪";
    }

    // Gets all strings from the game board and returns
    // the board arrangement in the correct form.
    // Conditions:
    //      post - returns the arrangement of the board.
    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        Scanner console = new Scanner(System.in);

        while (!game.isGameOver()) {
            System.out.println(game);
            game.makeMove(console);
        }
        System.out.println(game);
    }
}
