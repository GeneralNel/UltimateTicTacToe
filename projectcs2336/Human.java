/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcs2336;

import java.util.*;

// this Human class extends the Player abstract class
public class Human extends Player {

    Scanner in = new Scanner(System.in); // for user input

    // Constructor
    public Human(UltimateTTTBoard board, Mark mark) {
        super(board, mark); // Calls super Constructor
    }

    // this method takes in the board or box number and determines the position in array form
    private static int[] determineMove(int num) {
        int[] result = new int[2];      // create result array
        switch (num) {
            case 0:     // when the num argument is 0
                result[0] = 0;
                result[1] = 0;
                break;
            case 1:     // when the num argument is 1
                result[0] = 0;
                result[1] = 1;
                break;
            case 2:     // when the num argument is 2
                result[0] = 0;
                result[1] = 2;
                break;
            case 3:     // when the num argument is 3
                result[0] = 1;
                result[1] = 0;
                break;
            case 4:     // when the num argument is 4
                result[0] = 1;
                result[1] = 1;
                break;
            case 5:     // when the num argument is  5
                result[0] = 1;
                result[1] = 2;
                break;
            case 6:     // when the num argument is 6
                result[0] = 2;
                result[1] = 0;
                break;
            case 7:     // when the num argument is 7
                result[0] = 2;
                result[1] = 1;
                break;
            case 8:     // when the num argument is 8
                result[0] = 2;
                result[1] = 2;
                break;
            default:     // when the num argument is invalid
                result[0] = -1;
                result[1] = -1;
                break;
        }
        return result;  // return the result array
    }

    // this method overrides abstract move method and takes into argument previous move position in int array
    public int[] move(int[] positions) {
        boolean invalidInput = true;    // assume invalid input is true
        do {
            System.out.println("Select the board number you want to make a move in: ");
            int boardNum[] = determineMove(in.nextInt()); // take input of integer for board and pass it to the determineMove method to find it in array format
            System.out.println("Select the box number you want to make a move in: ");
            int boxNum[] = determineMove(in.nextInt()); // take input of integer for box and pass it to the determineMove method to find it in array format

            int[] newPositions = {boardNum[0], boardNum[1], boxNum[0], boxNum[1]}; // create new positions array

            // Define when the user input can be considered invalid
            invalidInput = (isOutOfBounds(newPositions) // when positions is out of bounds
                    || !isValid(gameBoard, boardNum[0], boardNum[1], boxNum[0], boxNum[1]) // when positions in the wrong box
                    || (positions[3] != -1 && boardNum[1] != positions[3]) // when the position input is invalid
                    || (positions[2] != -1 && boardNum[0] != positions[2]));                // when the position input is invalid

            if (invalidInput) {
                System.out.println("Please enter a valid move. ");  // get valid move position again
            } else {
                gameBoard.setMarkAtPosition(boardNum[0], boardNum[1], boxNum[0], boxNum[1], playerMark); // set the mark at given position
                return newPositions;    // return the new positions
            }
        } while (invalidInput); // keep looping for error handling
        return null; // this is like a void return type to complete the method
    }

    // this method checks if move input is out of bounds and returns true or false
    private boolean isOutOfBounds(int[] move) {
        for (int i = 0; i <= 3; i++) {
            if (move[i] > 2 || move[i] < 0) { // when the input is not between 0 and 2
                System.out.println("Out of bounds!");
                return true; // when out of bounds
            }
        }
        return false; // when not out of bounds
    }
}
