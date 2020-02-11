/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcs2336;

import java.util.*;

// this Bot class extends the Player abstract class
public class Bot extends Player {

    // Constructor
    public Bot(UltimateTTTBoard board, Mark mark) {
        super(board, mark); // call super constructor
    }

    // this method overrides abstract move method and takes into argument previous move position in int array
    @Override
    public int[] move(int[] previousMovePosition) {
        int[] newPositions = new int[4]; // new positions
        if (previousMovePosition[2] == -1 || previousMovePosition[3] == -1) { // when opponent can play in any board
            do {
                for (int i = 0; i < 4; i++) {
                    newPositions[i] = (int) (Math.random() * 3); // the AI bot will make random moves using this code
                }
            } while (!isValid(gameBoard, newPositions[0], newPositions[1], newPositions[2], newPositions[3])); // loop until valid move is made
        } else {    // when opponent must play in specific board
            do {
                // set new board position to previous box position
                newPositions[0] = previousMovePosition[2]; 
                newPositions[1] = previousMovePosition[3];

                for (int i = 2; i < 4; i++) {
                    newPositions[i] = (int) (Math.random() * 3); // the AI bot will make random moves using this code
                }
            } while (!isValid(gameBoard, newPositions[0], newPositions[1], newPositions[2], newPositions[3])); // loop until valid move is made
        }
        gameBoard.setMarkAtPosition(newPositions[0], newPositions[1], newPositions[2], newPositions[3], playerMark); // place the bot's mark on the given position
        return newPositions; // returns the new positions
    }

}
