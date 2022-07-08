package Model;

/**
 * Board class contains the two dimensional array of intigers used to hold the
 * sudoku board.
 *
 * @author aet9
 */
public class Board {

    private int[][] board;

    /**
     * Constructor creates a two dimensional array of intigers to
     * hold the board.
     */
    public Board() {
        board = new int[9][9];
    }

    /**
     * Method to set numbers in the array.
     *
     * @param num number to be set
     * @param row row of cell to be placed
     * @param col column of cell to be placed
     */
    public void setNumber(int num, int row, int col) {
        board[row][col] = num;
    }
    
    /**
     * Method to get the number from a specified cell
     * 
     * @param row row of cell to get number from
     * @param col column of cell to get number from
     * @return integer from the cell
     */
    public int getNumber(int row, int col) {
        return board[row][col];
    }

    /**
     * Returns an aray of intigers containing the intigers from the specified
     * row
     *
     * @param row row to be returned
     * @return array of intigers from the row
     */
    public int[] getRow(int row) {
        int[] ret = new int[9];
        for (int i =0; i< 9; i++) {
            ret[i]= board[row][i];
        }
        return ret;
    }

    /**
     * Returns an aray of intigers containing the intigers from the specified
     * column
     *
     * @param col column to be returned
     * @return array of intigers from the column
     */
    public int[] getColumn(int col) {
        int[] ret = new int[9];
        for (int i =0; i< 9; i++) {
            ret[i]= board[i][col];
        }
        return ret;
    }

    /**
     * Returns an aray of intigers containing the intigers from the specified
     * 3 x 3 box.
     *
     * @param row row of cell in top left corner of box.
     * @param col column of cell in top left of box
     * @return array of ints from specified box
     */
    public int[][] getSquare(int row, int col) {
        int[][] ret = new int[3][3];
        for (int i =0; i< 3; i++) {
            ret[0][i]= board[row][col+i];
            ret[1][i]= board[row+1][col+i];
            ret[2][i]= board[row+2][col+i];
        }
        return ret;
    }


}
