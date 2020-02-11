/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcs2336;

// the abstract class Player is extended by Human player and Bot AI player since they are both essentially players
public abstract class Player {

    protected UltimateTTTBoard gameBoard; // gameBoard is the UltimateTTTBoard object
    protected Mark playerMark;// the playerMark is the Mark object for each player

    // Constructor
    public Player(UltimateTTTBoard board, Mark mark) {
        gameBoard = board;
        playerMark = mark;
    }

    // abstract class for making move by taking in the previous positions
    public abstract int[] move(int[] positions);

    // isValid move checks whether the move is valid in the current board state or not
    public boolean isValid(UltimateTTTBoard currBoard, int boardRow, int boardColumn, int boxRow, int boxColumn) {
        if (currBoard.getMarkAtPosition(boardRow, boardColumn, boxRow, boxColumn).getMark().equals(playerMark.getDefaultMark())) {
            return true; // returns true when the box is occupied by the default mark i.e. dash
        } else {
            return false; // returns false when the box is occupied by one of the player's marks
        }
    }
}
