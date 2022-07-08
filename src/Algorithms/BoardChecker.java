package Algorithms;

import Controller.*;
import Model.Board;
import java.awt.Point;

/**
 * The board checker class contains methods for cheching weather numbers appear
 * in rows, columns or boxes and also contains a method to check if the
 * sudoku has been finished.
 *
 * @author aet9
 */
public class BoardChecker {

    private Board board;

    /**
     * Sets the as a global variable to be used in the methods.
     *
     */
    public BoardChecker() {
        board = Classes.board;
    }

    /**
     * Checks the intigers in a row to determine weather it contains the
     * specified number or 0 if checking weather finished. returns true if no
     * instances of the number are found otherwise false.
     *
     * @param num the number to check.
     * @param row row to be checked
     * @return int containing the position of the number in the row or -1 if
     *         it is not in the row.
     */
    public int checkRow(int num, int row) {
        int ret = -1;
        int[] r = board.getRow(row);
        for (int i =0; i<9; i++) {
            if (r[i]==num){
                ret = i;
                break;
            }
        }
        return ret;
    }

    /**
     * Checks the intigers in a column to determine weather it contains the
     * specified number or 0 if checking weather finished. returns true if no
     * instances of the number are found otherwise false.
     *
     * @param num the number to check.
     * @param col column to be checked
     * @return int containing the position of the number in the column or -1 if
     *         it is not in the column.
     */
    public int checkColumn(int num, int col) {
        int ret = -1;
        int[] r = board.getColumn(col);
        for (int i =0; i<9; i++) {
            if (r[i]==num){
                ret= i;
            }
        }
        return ret;
    }

    /**
     * Checks the intigers in a 3 by 3 box to determine weather it contains the
     * specified number or 0 if checking weather finished. returns true if no
     * instances of the number are found otherwise false.
     *
     * @param num the number to check.
     * @param row row of cell in top left of the box
     * @param col column of cell in top left of the box
     * @return Point containing the position of the number in the
     *         3 x 3 box or -1,-1 if it is not in the box.
     */
    public Point checkBox(int num, int row, int col) {
        Point ret = new Point(-1,-1);
        int[][] r = board.getSquare(row, col);
        for (int i =0; i<3; i++) {
            for (int j =0; j<3; j++) {
                if (r[i][j]==num){
                    ret = new Point(i,j);
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * Method checks if the board has been finished
     *
     * @return boolean true if finished else false
     */
    public boolean isFinished() {
        for (int i = 0; i<9; i++) {
            for (int j =0; j<9; j++) {
                if (board.getNumber(i, j)==0){
                    return false;
                }
            }
        }
        return true;
    }
}
