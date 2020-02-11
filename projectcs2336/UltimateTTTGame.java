/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcs2336;

import java.util.*;

//The UltimateTTTGame is a class which utilizes the other classes to make the ultimate tic tac toe game
// and has the methods that run the actual game, allows the players to make valid moves and 
// respond to it appropriately, and constantly checks if a winner is found yet or a tie has been declared. 
// This class also prints out the current board on to the console after every move has been made.
public class UltimateTTTGame {

    private Player player1;// Define player 1
    private Player player2;// Define player 2
    private UltimateTTTBoard ultimateBoard; // ultimate ttt game board object
    int[] startingPositions = {-1, -1, -1, -1}; // default starting position set to -1 for each position to allow user to start from anywhere in board

    // Constructor
    public UltimateTTTGame(UltimateTTTBoard boardIn, Player player1In, Player player2In) {
        player1 = player1In;    // initialize to passed in argument for player 1
        player2 = player2In; // initialize to passed in argument for  player 2
        ultimateBoard = boardIn; // initialize to passed in argument for board
    }

    // this method checks if ultimate board has been tied and returns true or false
    private boolean isTied() {
        Iterator boardIter = ultimateBoard.iterator();      // iterate through each board
        while (boardIter.hasNext()) {
            if (((TTTBoard) boardIter.next()).getBoardWinner().equals("_")) {
                return false; // returns false if not tied
            }
        } // keep looping until a next board is found
        return true; // true indicating it is tied
    }

    // this method checks if ultimate board has become full and returns true or false
    protected boolean boardFull(int row, int column) {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (ultimateBoard.getTTTBoard(row, column).getMarkAtPosition(i, j).getMark().equals("_")) {
                    return false;       // returns false if there is an empty small board
                }
            } // end of for loop
        }  // end of for loop
        return true; // returns true when board is indeed full
    }

    // this method takes in the row and column and determines the board number that the next player must play in
    private String determineBoard(int row, int column) {
        if (row < 0 || column < 0) {
            return "any board. ";
        } // when any board can be played in for next move
        String boardNum = "Board "; // board number in string format
        if (row == 0) {
            boardNum += Integer.toString(column);  // board number among 0,1, and 2
        } else if (row == 1) {
            boardNum += Integer.toString(3 + column); // board number among 3,4, and 5
        } else if (row == 2) {
            boardNum += Integer.toString(6 + column); // board number among 6, 7 , 9
        }
        return boardNum; // return the board number in a message in string format
    }

    // this method is used by the play method to allow the player to make a move
    // this is done by taking in the positions in int array form and a value indicating which player's turn it is
    private int[] makeMove(boolean player1Turn, int[] positions) {
        String playerNumber = "2";      // player 2 assumed
        if (player1Turn) {
            playerNumber = "1";     // if player 1 turn value is true then player number changed to 1
        }
        if (positions[2] != -1 && positions[3] != -1 && boardFull(positions[2], positions[3])) {
            positions = startingPositions; // starting position of -1s is set when invalid previous position or full small board
        }

        // print message saying to player which board can he/she make a move in
        System.out.println("Player " + playerNumber + ", you can make a move in "
                + determineBoard(positions[2], positions[3]));
        System.out.println(listAvlMoves(positions[2], positions[3])); // print all the list of available legal moves that can be made in the board

        if (player1Turn) {
            return player1.move(positions); // if player 1's turn then return player 1's move method
        }
        return player2.move(positions); // else return player2's move methodd
    }

    // this method plays the game of Ultimate tic tac toe and has return void type
    public void play() {
        int[] positions = printCurrentBoard(startingPositions); // positions set to starting values when printing for first time
        while (!hasWonBigBoard(player1) && !hasWonBigBoard(player2)) {
            if (isTied()) {
                System.out.println("Game Tied!"); // print message saying game tied
                return;
            }           //  end of case when game has tied
            positions = makeMove(true, positions);   // allow player 1 to make move as player1 turn is initalized to true
            positions = printCurrentBoard(positions); // print board with new positions 
            if (!hasWonBigBoard(player1) && !hasWonBigBoard(player2)) {
                if (isTied()) {
                    System.out.println("Game Tied!"); // print message saying game tied
                    return; // return back to caller
                }            //  end of case when game has tied
                positions = makeMove(false, positions);  // allow player 2 for making move as player1 turn is initalized to false
                positions = printCurrentBoard(positions);   // print board with new positions 
            }
        } // end of while loop when no one haas won the ultimate tic tac toeboard 

        // once an ultimate tic tac toe board winner is determined
        if (hasWonBigBoard(player1)) {
            System.out.println("Game Winner: Player 1"); // print message saying player 1 won
        } else if (hasWonBigBoard(player2)) {
            System.out.println("Game Winner: Player 2");  // print message saying player 2 won
        }
    }

    /* METHOD TO IMPLEMENT THE EXTRA CREDIT FEATURE  */
    // this method takes in the row and column of the small board to return to the player the list of available legal moves for the given board
    private String listAvlMoves(int r, int c) {
        if (r < 0 || c < 0) {
            return "";
        }                // for invalid row and column or when any board can be played in for next move
        String listMoves = "Available boxes: ";     // string of list of moves
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ultimateBoard.getTTTBoard(r, c).getMarkAtPosition(i, j).getMark().equals("_")) { // when the box has the default mark dash
                    listMoves += findNumBox(i, j) + ", ";       // if an empty box is found in the board then keep adding it to the list of moves
                }
            } // end of for loop
        } // end of for loop
        return listMoves; // return the list of legal moves
    }

    // this method takes in the player and row and column of the board to see if a diagonal match pattern is found
    private boolean diagonalMatch(Player player, int rowSmall, int colSmall) {
        return (ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 0, 0).getMark().equals(player.playerMark.getMark())// check once from top left to bottom right
                && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 1, 1).getMark().equals(player.playerMark.getMark())
                && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 2, 2).getMark().equals(player.playerMark.getMark()))
                || (ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 2, 0).getMark().equals(player.playerMark.getMark()) // // check once from top right to bottom left
                && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 1, 1).getMark().equals(player.playerMark.getMark())
                && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 0, 2).getMark().equals(player.playerMark.getMark()));
    } //  returns false when the pattern is not matched and true when diagonally matched

    // this method takes in the player and row and column of the board to see if a column match pattern is found
    private boolean columnMatch(Player player, int rowSmall, int colSmall) {
        for (int col = 0; col <= 2; col++) {
            if (ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 0, col).getMark().equals(player.playerMark.getMark())
                    && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 1, col).getMark().equals(player.playerMark.getMark())
                    && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, 2, col).getMark().equals(player.playerMark.getMark())) {
                return true;
            }// returns true when the pattern is matched
        }// end of for loop
        return false;// returns false when the pattern is not matched
    }

    // this method takes in the player and row and column of the board to see if a row match pattern is found
    private boolean rowMatch(Player player, int rowSmall, int colSmall) {
        for (int row = 0; row <= 2; row++) {
            if (ultimateBoard.getMarkAtPosition(rowSmall, colSmall, row, 0).getMark().equals(player.playerMark.getMark())
                    && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, row, 1).getMark().equals(player.playerMark.getMark())
                    && ultimateBoard.getMarkAtPosition(rowSmall, colSmall, row, 2).getMark().equals(player.playerMark.getMark())) {
                return true;
            } // returns true when the pattern is matched
        } // end of for loop
        return false;  // returns false when the pattern is not matched
    }

    // this method takes in the player to see if the player has made any winning pattern and has won the board or not
    private boolean hasWonBigBoard(Player player) {

        // check column pattern
        for (int bigColumn = 0; bigColumn <= 2; bigColumn++) {
            if ((hasWonSmallBoard(player, 0, bigColumn)
                    && hasWonSmallBoard(player, 1, bigColumn)
                    && hasWonSmallBoard(player, 2, bigColumn))) {
                return true;// returns true when the pattern is matched
            }
        }// end of for loop
        // check row pattern
        for (int bigRow = 0; bigRow <= 2; bigRow++) {
            if ((hasWonSmallBoard(player, bigRow, 0)
                    && hasWonSmallBoard(player, bigRow, 1)
                    && hasWonSmallBoard(player, bigRow, 2))) {
                return true;// returns true when the pattern is matched
            }
        } // end of for loop

        // check diagonal pattern
        return (hasWonSmallBoard(player, 0, 0) && hasWonSmallBoard(player, 1, 1)
                && hasWonSmallBoard(player, 2, 2)) || (hasWonSmallBoard(player, 2, 0)
                && hasWonSmallBoard(player, 1, 1) && hasWonSmallBoard(player, 0, 2)); // returns true when the pattern is diagonally matched
    }

    // check if the player has won any small board by taking in the row and column of the small board and player making final move
    private boolean hasWonSmallBoard(Player player, int rowSmall, int colSmall) {
        return diagonalMatch(player, rowSmall, colSmall) && columnMatch(player, rowSmall, colSmall) && rowMatch(player, rowSmall, colSmall);
    }

    // this method prints the current board state by taking in the positions of the last move
    private int[] printCurrentBoard(int[] lastPosition) {
        System.out.println(ultimateBoard.toString());   // first print the ultimate board to string
        if (lastPosition[0] == -1) {
            return lastPosition;
        }   // when invalid last position, dont print the board
        // When player 1 has won a small board with his/her last move positions, print this board state
        if (hasWonSmallBoard(player1, lastPosition[0], lastPosition[1])) {
            System.out.println(determineBoard(lastPosition[0], lastPosition[1]) + " won by Player 1." + "\n\n");
            ultimateBoard.getTTTBoard(lastPosition[0], lastPosition[1]).setBoardWinner("X");        // set board winner to player 1 mark
        } // When player 2 has won a small board with his/her last move positions, print this board state
        else if (hasWonSmallBoard(player2, lastPosition[0], lastPosition[1])) {
            System.out.println(determineBoard(lastPosition[0], lastPosition[1]) + " won by Player 2." + "\n\n");
            ultimateBoard.getTTTBoard(lastPosition[0], lastPosition[1]).setBoardWinner("O");        // set board winner to player 2 mark
            // When small board becomes full after last move positions, print this board state
        } else if (boardFull(lastPosition[0], lastPosition[1])) {
            System.out.println(determineBoard(lastPosition[0], lastPosition[1]) + " is full.\n\n");
            ultimateBoard.getTTTBoard(lastPosition[0], lastPosition[1]).setBoardWinner("F");        // set board winner to F mark indicating full board
            return startingPositions; // return the starting positions again to allow player to move in any board next move
        }
        return lastPosition; // return the array containing positions for the last move
    }

    // this method takes in the row and column of a position to let the player know the box number
    // this is done to make the program more user friendly
    private String findNumBox(int row, int column) {
        if (row == 1) {
            row += 2;
        } else if (row == 2) {
            row += 4;
        }
        return Integer.toString(row + column); // return in string format using toString method
    }

}
