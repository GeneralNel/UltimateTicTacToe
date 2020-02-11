/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcs2336;

// TTTBoard is the simple Tic Tac Toe board class
public class TTTBoard {

    private final Mark[][] smallBoard; // creates a smallBoard as an object of multidimensional Mark class
    private String boardWinner = "_"; // the default board winner is set to the default mark

    // Constructor
    public TTTBoard() {
        smallBoard = new Mark[3][3];    // initialize small board object
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                smallBoard[i][j] = new Mark("_"); // place dashes in each box
            } // end of for loop
        } // end of for loop
    }

    // getter for mark in the desired position
    public Mark getMarkAtPosition(int row, int col) {
        return smallBoard[row][col];
    }

    // setter for mark in the desired position
    public void placeMark(int row, int col, Mark mark) {
        smallBoard[row][col] = mark;
    }

    // getter for winner in the current board
    public String getBoardWinner() {
        return boardWinner;
    }

    // setter for winner in the current board
    public void setBoardWinner(String newWinner) {
        boardWinner = newWinner;
    }

    // this method returns all the contents line by line in the selected row of the board passed in as argument
    public String getContents(int selectedRow) {
        String contents = ""; // define contents string
        for (int i = 0; i <= 2; i++) {
            contents += smallBoard[selectedRow][i].getMark(); // get the mark at each position
            if (i != 2) {
                contents += " "; // add a space between the elements
            }
        } // end of for loop
        return contents; // return the string containing all the contents line by line
    }

}
