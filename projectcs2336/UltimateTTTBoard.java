/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcs2336;

import java.util.*;

// the UltimateTTTBoard uses the simple TTTBoard class to create an ultimate board
// it implements the Iterable interface in order to let the program iterate through each small board in the ultimate board
public class UltimateTTTBoard implements Iterable {

    private TTTBoard[][] ultimateBoard; // create ultimateBoard object as a multidimensional array of TTTBoard

    // Constructor
    public UltimateTTTBoard() {
        ultimateBoard = new TTTBoard[3][3];
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                ultimateBoard[r][c] = new TTTBoard();
            }// end of for loop
        } // end of for loop
    }

    // getter for the TTTBoard
    public TTTBoard getTTTBoard(int row, int column) {
        return ultimateBoard[row][column];
    }

    // this method returns the contents in the row passed in as argument into a string format
    private String getContentsInRows(int indexRow) {
        int R = indexRow / 3;
        String lineInString = "";
        for (int i = 0; i < 3; i++) {
            lineInString += getTTTBoard(R, i).getContents(indexRow - R * 3);
            if (i != 2) {
                lineInString += " || ";
            } else {
                lineInString += "\n";
            }
        } // end of for loop
        return lineInString;
    }

    // getter for mark at desired position
    public Mark getMarkAtPosition(int bigBoardRow, int bigBoardCol, int smallBoardRow, int smallBoardCol) {
        return ultimateBoard[bigBoardRow][bigBoardCol].getMarkAtPosition(smallBoardRow, smallBoardCol);
    }

    // setter for mark at desired position
    public void setMarkAtPosition(int bigBoardRow, int bigBoardCol, int smallBoardRow, int smallBoardCol, Mark mark) {
        ultimateBoard[bigBoardRow][bigBoardCol].placeMark(smallBoardRow, smallBoardCol, mark);
    }

    // this method returns the header for each current board state taking in the starting board number
    private String getHeader(int index) {
        return "   Board " + ++index + "  Board " + ++index + "  Board " + ++index + "\n";
    }

    // this method overrides the default toString method and returns all the contents line by line in string format
    @Override
    public String toString() {
        String newLine = "   -------------------------\n";// dashes for comprehensible reading
        String whiteSpace = "    ";         // white space for comprehensible reading
        String contents = newLine + getHeader(-1) + newLine; // get the first heading for boards 0,1, and 2
        for (int index = 0; index <= 8; index++) {
            contents += whiteSpace + getContentsInRows(index); // add the contents with whitespace to the contents string
            if (index % 3 == 2 && index != 8) {
                contents += newLine + getHeader(index) + newLine;  // get the headings for boards 3,4,5 and then boards 6,7, and 8
            }                       // when it is time to move to newline to print next row of headings
        } // end of for loop
        contents += "\n"; // new linefor comprehensible reading
        return contents; // return the contents of the board
    }

    // this  method overrides the default iterator method in order to iterate through each small board in the big board
    @Override
    public Iterator<TTTBoard> iterator() {
        Iterator<TTTBoard> iterator = new Iterator<TTTBoard>() { //iterator of type TTTBoard objects
            int curr = 0;

            // overrides to define the hasNext method as the iterator of the board to see if there are any boards left to iterate through
            @Override
            public boolean hasNext() {
                return curr < 9;
            }

            // overrides to define the next method to return the next small board in the ultimate board
            @Override
            public TTTBoard next() {
                return ultimateBoard[curr / 3][curr++ % 3];
            }

        }; // end of iterator method
        return iterator; // return the iterator
    }

}
