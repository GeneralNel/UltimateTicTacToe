/*
Name: Mushfiq Arefin Rashid
Course: CS 2336.006
Date: 12/02/2019

Analysis:
Create an ultimate tic tac toe game using object-oriented programming.
This game will have different rules than a regular tic tac toe game, and the 
program must adhere to all those rules and make sure the game is played that way.
The game must be played against an AI (bot). It must also provide player with a
list of all the possible legal moves for each round.

Design:
The main idea is that a simple tic tac toe board and game is first created
and then an ultimate tic tac toe board is implemented which is just an array of the
simple tic tac toe board objects. An abstract player class is created that the human
and bot classes extends from, and overrides methods like the move method which is 
common for both type of players. A mark class is created which is used as an object
type variable by the other classes. A simple tic tac toe board class is built which
simply puts marks on the multi dimensional board, gets those marks, and checks for 
winners or tie. The ultimate tic tac toe board class is then built which utilizes 
the TTTBoard class to create a multi dimensional array of board objects, so that
each board is treated as a box in the bigger picture. The UltimateTTTBoard class
also implements the iterable interface which iterates through each small board in the 
big ultimate board. Finally, the UltimateTTTGame class is created which utilizes most of 
the classes mentioned above and has the methods that run the actual game, allows the
players to make valid moves and respond to it appropriately, and constantly checks
if a winner is found yet or a tie has been declared. This class also prints out the 
current board on to the console after every move has been made. Do-while classes
and other loops have been implemented to ensure effective error handling. The 
list of available legal moves feature has also been implemented in this program.

Test:
    The following tests were executed along with the following results:
    1.  In a situation when human player wins, check if the program runs smoothly.
        Result: Program executes flawlessly with showing the winning board, declaring
        statement acknowledging human player (player 1 or 2) winning, and then exiting
        the program without any error.
    2.  In a situation when bot(AI) wins, check if the program runs smoothly.
        Result: Program executes flawlessly with showing the winning board, declaring
        statement acknowledging bot(player 1 or 2) winning, and then exiting
        the program without any error.
    3.  In a situation when no one wins, i.e. game tied, check if the program runs smoothly.
        Result: Program executes without any error showing the tied board that is completely
        full, announcing that neither player 1 nor 2 has won and that the game is tied, and
        then exiting the program without any error.
    4.  Ensure the program does not terminate awkwardly when the user makes a wrong selection
        when choosing player 1.
        Result: By testing with various outputs like numbers outside 1 and 2 (e.g. -1, 200),
        it is witnessed that program reiterates to request valid input, thanks to the do-while
        loop implemented which checks for valid input.
    5.  Ensure the program does not terminate awkwardly when the player chooses the wrong box 
        or board, i.e. when board is already filled or a specific board must be played in then.
        Result: By testing with inputs for boards and boxes it is confirmed that program does
        not crash but rather shows an error message when user types invalid input, and then lets
        user retype move.
    6.  Ensure the program does not terminate awkwardly when the player makes a move that is out
        of bounds.
        Result: By testing with inputs for boards and boxes outside the range of 0-9, the program 
        is executed and witnessed that the program loops again for new input after showing the user
        an error message as well as specifying that the user went out of bounds.
    7.  Check to confirm that all of the winning patterns (by row/column/diagonal) work properly.
        Result: Each type of pattern is tested by making human player play well enough to check all
        the boxes that match the pattern. The result is that each pattern works perfectly!
    8.  Ensure that the player can make a move in any board if the previous move board has become full.
        Result: It is tested and then seen that the user can play in any board during the first move and
        whenever the opponent challenges the player to a board for next move that has become full already.
    9.  Confirm that the list of available legal moves are correctly being outputted.
        Result: After different moves for different boards, it is indeed verified that this feature 
        is working well.
    10. When one player wins a board, ensure that the next player must still play in the board which aligns
        with the box number played in the previous move.
        Result: such a scenario is recreated and tested to confirm that the restriction applies in all 
        appropriate situations.
 */
package projectcs2336;

import java.util.Scanner;

public class DriverMain {

    public static void main(String[] args) {
        Player player1 = null; // Define player 1
        Player player2 = null;// Define player 2
        Scanner input = new Scanner(System.in); // input
        Mark markPlayer1 = new Mark("X"); // mark for player 1
        Mark markPlayer2 = new Mark("O"); // mark for player 2

        UltimateTTTBoard gameBoard = new UltimateTTTBoard(); // ultimate ttt game board
        int selection; // user selection 
        boolean invalidInput = false;  // invalid input considered false first
        printWelcomeMessage(); // print welcome message
        // keep iterating until a valid input is entered by user
        do {
            System.out.println("Select player 1 (X).\n  1 = Human\n  2 = Bot");
            System.out.print("Selection: ");
            selection = input.nextInt(); // take input 
            // automatically make the selected player into player 1 and other player into player 2
            if (selection == 1) {
                invalidInput = false;
                player1 = new Human(gameBoard, markPlayer1);  // player 1 is human
                player2 = new Bot(gameBoard, markPlayer2);// player 2 is bot
                System.out.println("\nHuman will be player 1 (X) and bot will be player 2 (O)\n\n");
            } else if (selection == 2) {
                invalidInput = false;
                player1 = new Bot(gameBoard, markPlayer1); // player 1 is bot
                player2 = new Human(gameBoard, markPlayer2); // player 2 is human
                System.out.println("\nBot will be player 1 (X) and human will be player 2 (O)\n\n");
            } else {
                System.out.println("Invalid input!\n"); // invalid input
                invalidInput = true;
            }
        } while (invalidInput);
        UltimateTTTGame ultimateGame = new UltimateTTTGame(gameBoard, player1, player2); // create an object of ultimateTTTGame
        ultimateGame.play(); // start the game
        System.out.println("\nThank you for playing!\n"); // end the program with a nice message
    }

    // this function prints the welcome message at start of game
    private static void printWelcomeMessage() {
        String lineDivider = "===========================================================================";
        System.out.println("Welcome! This is a game of tic-tac-toe on steroids.");
        System.out.println("-------------- Ultimate Tic Tac Toe ---------------");
        System.out.println("Each dash( _ ) represents an empty box in a specific board.");
        System.out.println("To select a valid box, choose the numbers 0 - 8");
        System.out.println("(0 for the first element on top left, 8 for the last element on top right)");
        System.out.println("\nMay the best man (or bot) win!");
        System.out.println("\n" + lineDivider + "\n");
    }

}
