/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectcs2336;

// the Mark class is used for creating mark objects in the board
public class Mark {

    private String mark;    // user mark of string type
    private final String defaultMark = "_"; // default mark set to a dash

    // Constructor
    public Mark(String userMark) {
        mark = userMark;
    }

    // getter for default mark
    public String getDefaultMark() {
        return defaultMark;
    }

    // setter for mark
    public void setMark(String userMark) {
        mark = userMark;
    }

    // getter for mark
    public String getMark() {
        return mark;
    }

}
