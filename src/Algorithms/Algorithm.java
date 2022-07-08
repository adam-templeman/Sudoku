package Algorithms;

import Controller.*;
import Model.Board;
import java.awt.Point;

/**
 * Algorithm Class controlls the solving of the sudoku by running the row, 
 * column and box classes in turn. The class also contains the two dimensional
 * array used for solving the sudoku.
 *
 * @author aet9
 */
public class Algorithm implements Runnable{

    private String[][] solvingBoard;
    private String numbers;
    private BoardChecker bCheck;
    private Board board;
    private Thread runner;
    private Thread rowRun;
    private Thread colRun;
    private Thread boxRun;
    private Row rowAlg;
    private Column colAlg;
    private Box boxAlg;
    private boolean startNew;
    private boolean running;
    private static long time = 1;


    /**
     * Constructor created solving board and instances of row, column and box
     * and their corresponding threads.
     */
    public Algorithm() {
        bCheck = new BoardChecker();
        board = Classes.board;
        startNew = false;
        solvingBoard = new String[9][9];
        numbers = "123456789";
        updateSolvingBoard();
        updateAlgPanel();
        rowAlg = new Row(bCheck, this);
        colAlg = new Column(bCheck, this);
        boxAlg = new Box(bCheck, this);
        rowRun = new Thread(rowAlg);
        colRun = new Thread(colAlg);
        boxRun = new Thread(boxAlg);
        runner = new Thread(this);
        running = false;
    }

    /**
     * Method starts the algorithm by creating a new thread and calling
     * thread.start();
     */
    public void start() {
        startNew = false;
        runner = new Thread(this);
        updateSolvingBoard();
        updateAlgPanel();
        if (running == true) {
            stop();
        }
        else {
            runner.start();
        }
    }


    /**
     * Method sets the variable startNew to true and waits for the thread to
     * stop.
     */
    @SuppressWarnings("static-access")
    public void stop() {
        startNew = true;
        if (running) {
            /*
             * Tried to do runner.interupt() here but made things blow up
             * therefore though slower i decided to use this method.
             */
            do {
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException ex) {
                    System.out.print("this is where it failed.");
                }
            }while(running);
        }
    }

    /**
     * Method sets up the solving board by removing any numbers that have been
     * given.
     */
    public void setUp() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getNumber(i, j)!=0) {
                    removeFromAll(board.getNumber(i, j), i, j);
                    solvingBoard[i][j] = "";
                }
            }
        }
    }

    /**
     * Run method for the algorithm class which is called when runner.start()
     * is called. the method calls the three algorithm classes box row and column
     * which then run their algorithms.
     */
    @Override
    @SuppressWarnings("static-access")
    public void run() {
        running = true;
        setUp();
        for (int i = 0; i< 10; i++) {
            numberThere();
            updateAlgPanel();
            rowRun.run();
            colRun.run();
            boxRun.run();
            if (bCheck.isFinished()||startNew) {
                break;
            }
            do {
                try {
                    Thread.currentThread().sleep(time);
                    if (bCheck.isFinished()||startNew) break;
                } catch (InterruptedException ex) {
                    System.out.print("this is where it failed.");
                }
            }while (!rowAlg.isFinished()&&!colAlg.isFinished()&&!boxAlg.isFinished());
            if (bCheck.isFinished()||startNew) break;
        } 
        running = false;
        if (startNew) {
            Classes.sFrame.startingNew();
        }
        else {
            Classes.sFrame.finished();
        }
    }

    
    /**
     * Method removes all instances of num in the specified row.
     *
     * @param num number to remove
     * @param row row to remove from
     */
    public void removeInRow(int num, int row) {
        char c = (""+num).charAt(0);
        for (int i = 0; i<9; i++) {
            solvingBoard[row][i] = removeChar(solvingBoard[row][i], c);
        }
        updateAlgPanel();
    }

    /**
     * Method removes num from the specified column
     *
     * @param num number to remove
     * @param col column to remove from
     */
    public void removeInColumn(int num, int col) {
        for (int i = 0; i<9; i++) {
            char c = (""+num).charAt(0);
            solvingBoard[i][col] = removeChar(solvingBoard[i][col], c);
        }
        updateAlgPanel();
    }

    /**
     * Method removes num from the specified box.
     *
     * @param num number to remove
     * @param row row of top left cell of box
     * @param col column of top left cell of box
     */
    public void removeInBox(int num, int row, int col) {
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                char c = (""+num).charAt(0);
                solvingBoard[row+i][col+j] = removeChar(solvingBoard[row+i][col+j], c);
            }
        }
        updateAlgPanel();
    }


    /**
     * Method checks if there are any cells on the board that contain only one
     * number.
     */
    public void numberThere() {
        for (int row = 0; row<9; row++) {
            for (int col = 0; col<9; col++) {
                if (solvingBoard[row][col].length()==1) {
                    numberThereCell(row, col);
                }
            }
        }
    }

    /**
     * Method checks weather a cell contains only one number then if the
     * number can be placed in the cell then removes all instances of that
     * number from row column and box.
     *
     * @param row row of number to be checked
     * @param col column of cell to be checked
     */
    public void numberThereCell(int row, int col) {
        int num = Integer.parseInt(solvingBoard[row][col]);
        if (checkCell(num, row, col)) {
            removeFromAll(num, row, col);
            solvingBoard[row][col] = "";
            setNumber(num, row, col);
        }
    }

    /**
     * Method sets the number specified to the board and the sudokuFrame after
     * waiting 0.1s.
     *
     * @param num number to set
     * @param row row of number to set
     * @param col column of number to set
     */
    @SuppressWarnings("static-access")
    public void setNumber(int num, int row, int col) {
        try {
            Thread.currentThread().sleep(time);
        }
        catch (InterruptedException e) {
            System.out.println("run inrterrupted!");
        }
        board.setNumber(num, row, col);
        Classes.sFrame.setNumberSudoku(num, row, col);
        updateAlgPanel();
    }

    /**
     * Method removes specified number from that row, column and box.
     *
     * @param num number to remove
     * @param row row of number to remove
     * @param col column of number to remove
     */
    public void removeFromAll(int num, int row, int col) {
        removeInColumn(num, col);
        removeInRow(num, row);
        removeInBox(num, nearestCorner(row), nearestCorner(col));
        updateAlgPanel();
    }


   /**
    * Method for removing a char from a string from
    * http://www.rgagnon.com/javadetails/java-0030.html
    * slightly modified.
    *
    * @param s string to remove char from
    * @param c char to remove
    * @return string with chars removed
    */
    public static String removeChar(String s, char c) {
        StringBuffer r = new StringBuffer(s.length());
        r.setLength(s.length());
        int current = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur != c) {
                r.setCharAt( current++, cur );
            }
        }
        String ret = r.toString();
        ret = ret.substring(0, current);
        return ret;
    }

    /**
     * Method checks that a specified number can be placed in the row, column
     * and box without breaking any rules.
     *
     * @param num number to check
     * @param row row of number to check
     * @param col column of number to check
     * @return boolean true if number can be placed else false
     */
    public boolean checkCell(int num, int row, int col) {
        if (bCheck.checkRow(num, row)==-1 && bCheck.checkColumn(num, col)==-1 &&
                bCheck.checkBox(num, nearestCorner(row),
                nearestCorner(col)).equals(new Point(-1, -1))) {
            return true;
        }
        else return false;
    }


    /**
     * Method used to determine the top left corner of the box of that row or
     * column.
     *
     * @param num row or column to find cell for
     * @return top left cell of box.
     */
    public int nearestCorner(int num) {
        int ret;
        if (num==0||num==3||num==6) {
            ret=num;
        }
        else if (num==1||num==4||num==7) {
           ret=num-1;
        }
        else {
            ret=num-2;
        }
        return ret;
    }

    /**
     * Method updates the sudokuAlgFrame from the solving board making the
     * current thread sleep before setting.
     */
    @SuppressWarnings("static-access")
    public void updateAlgPanel() {
        for (int row = 0; row<9; row++) {
            for (int col = 0; col<9; col++) {
                try {
                    Thread.currentThread().sleep(time);
                }
                catch (InterruptedException e) {
                    System.out.println("run inrterrupted!");
                }
                Classes.sAFrame.setNumberAlg(solvingBoard[row][col], row, col);
            }
        }
    }

    /**
     * Method fills the solving board and the sudoku frame cells with the
     * numbers one to nine.
     */
    public void updateSolvingBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solvingBoard[i][j] = numbers;
                Classes.sAFrame.setNumberAlg(solvingBoard[i][j], i, j);
            }
        }
    }

    /**
     * Method compares two cells to see if they are equal.
     *
     * @param row1 row of first compare
     * @param col1 column of first compare
     * @param row2 row of second compare
     * @param col2 column of second compare
     * @return boolean true if equal else false
     */
    public boolean cellEquals(int row1, int col1, int row2, int col2) {
        return solvingBoard[row1][col1].equals(solvingBoard[row2][col2]);
    }

    /**
     * Method checks the number of times num appears in a specified box.
     *
     * @param num number to check
     * @param row row of top left cell of box
     * @param col column of top left cell of box
     * @return number of instances of num found
     */
    public int numberOfbox(int num, int row, int col) {
        return boxAlg.numberOf(num, row, col);
    }

    public String findNumInBox(int num, int row, int col) {
        String ret ="";
        for (int r = 0; r<3; r++) {
            for (int c = 0; c<3; c++) {
                if (solvingBoard[row+r][col+c].contains(""+num)) {
                    ret+=(row+r)+(col+c);
                }
            }
        }
        return ret;
    }

    public void setSolvingBoardNum(String num, int row, int col) {
        solvingBoard[row][col] = num;
        updateAlgPanel();
    }


    public String getSolvingBoardNum(int row, int col) {
        return solvingBoard[row][col];
    }

    /**
     * Method returns and array containing column specified.
     * @param col column to return
     * @return array containing col
     */
    public String[] getColumn(int col) {
        String[] temp = new String[9];
        for (int i = 0; i<9; i++) {
            temp[i] = solvingBoard[i][col];
        }
        return temp;
    }

    /**
     * Method returns an array containing the row specified.
     * @param row row to return
     * @return array containin row
     */
    public String[] getRow(int row) {
        String[] temp = new String[9];
        for (int i = 0; i<9; i++) {
            temp[i] = solvingBoard[row][i];
        }
        return temp;
    }

    /**
     * Method returns a two dimensional array of the box that contains the cell
     * (cellRow, cellCol);
     *
     * @param boxRow row of cell of which to return box of
     * @param boxCol column of cell of which to return box of
     * @return two dimensional array of box
     */
    public String[][] getBox(int cellRow, int cellCol) {
        String[][] temp = new String[3][3];
        int leftRow = nearestCorner(cellRow)*3;
        int leftCol = nearestCorner(cellCol)*3;
        for (int row = 0; row<3; row++) {
            for (int col = 0; col<3; col++) {
                temp[row][col] = solvingBoard[leftRow+row][leftCol+col];
            }
        }
        return temp;
    }

    public String[][] setGetBox(int box) {
        int row;
        int col;
        if (box==1||box==2||box==3) {
            row = 0;
            col = (box-1)*3;
        }
        if (box==4||box==5||box==6) {
            row = 3;
            col = (box-3)*3;
        }
        else {
            row = 6;
            col = (box-6)*3;
        }
        return getBox(row, col);
    }
}

