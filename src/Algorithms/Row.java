package Algorithms;

/**
 * Row class contains all the solving algorithms used on the rows.
 *
 * @author aet9
 */
public class Row implements Runnable{

    private BoardChecker bCheck;
    private Algorithm alg;
    private boolean isFinished = false;

    /**
     * Constructor for the row class asigns the perameters to variables for
     * use in the other methods.
     * 
     * @param bc board checker
     * @param b board
     * @param a algorithm 
     * @param solvingBoard two dimensional string array
     */
    public Row(BoardChecker bc, Algorithm a) {
        bCheck = bc;
        alg = a;
    }

    public void run() {
        isFinished = false;
        solveStepOne();
        instanceOf();
        solveStepTwo();
        solveStepThree();
        isFinished = true;
    }


    /**
     * Method checks each row to see if there is a number set on the board
     * if there is the number there then it removes the number from the row
     * on the solving board.
     */
    public void solveStepOne() {
        alg.numberThere();
        for (int row = 0; row<9; row++) {
            for (int num = 1; num< 10; num++) {
                int a = bCheck.checkRow(num, row);
                if (a!=-1) {
                    alg.removeInRow(num, row);
                }
            }
            alg.numberThere();
        }
    }

    /**
     * Method checks if there are cells in a row that are the same and appear
     * only the number of numbers in the cell, times. if there is then it 
     * removes the numbers from the rest of that row.
     */
    public void solveStepTwo() {
        alg.numberThere();
        for (int row = 0; row<9; row++) {
            for (int col = 0; col< 9; col++) {
                int numOf = 0;
                int num = alg.getSolvingBoardNum(row, col).length();
                if (num<2) continue;
                String column = new String();
                for (int h = 0; h<9; h++) {
                    if (alg.cellEquals(row, col, row, h)) {
                        numOf++;
                        column += h;
                    }
                }
                if (numOf==num&&num!=1) {
                    String cell = alg.getSolvingBoardNum(row, col);
                    for (int a = 0; a<num; a++) {
                        int number = Integer.parseInt(cell.charAt(a)+"");
                        alg.removeInRow(number, row);
                    }
                    for (int r = 0; r<column.length(); r++) {
                        int c = Integer.parseInt(column.charAt(r)+"");
                        alg.setSolvingBoardNum(cell, row, c);
                    }
                }
            }
        }
    }


   /**
    * Method finds any numbers in a row that in one box only apear in that
    * row, it then removes the numbers from the rest of the row.
    */
    public void solveStepThree() {
        alg.numberThere();
        for (int row = 0; row<9; row++) {
            for (int colStep = 0; colStep<3; colStep++) {
                for (int num = 1; num<10; num++) {
                    int numOf = 0;
                    String colContNum = "";
                    for (int col = 0; col<3; col++) {
                        if (alg.getSolvingBoardNum(row, (colStep*3)+col).contains(num+"")) {
                            numOf++;
                            colContNum += ""+((colStep*3)+col);
                        }
                    }
                    if (numOf == 0) {
                        continue;
                    }
                    else if (alg.numberOfbox(num, alg.nearestCorner(row), (colStep*3))==numOf) {
                        alg.removeInRow(num, row);
                        for (int h = 0; h<colContNum.length(); h++) {
                            int temp = Integer.parseInt(""+colContNum.charAt(h));
                            alg.setSolvingBoardNum(alg.getSolvingBoardNum(row, temp)+num, row, temp);
                        }
                    }
                }
            }
        }
    }


   /**
    * Method checks weather there is a cell in the row that only
    * contains one number or if a number only appears once in that row,
    * it then sets this number on the board and removes the number from
    * that row, column and box.
    */
    private void instanceOf() {
        alg.numberThere();
        for (int row = 0; row<9; row++) {
            int col = 0;
            int numOf = 0;
            for (int num = 1; num<10; num++) {
                numOf = 0;
                for (int j = 0; j<9; j++) {
                    char ch = (""+(num)).charAt(0);
                    String s = alg.getSolvingBoardNum(row, j);
                    int a = s.indexOf(ch);
                    if (s.length()==1) {
                        alg.numberThereCell(row, j);
                    }
                    if (a!=-1) {
                        numOf++;
                        col = j;
                    }
                }
                if (numOf==1) {
                    alg.removeFromAll(num, row, col);
                    alg.setSolvingBoardNum("", row, col);
                    alg.setNumber(num, row, col);
                }
            }
        }
        alg.numberThere();
    }


    /**
     * Method returns weather the thread has finished running or not.
     * 
     * @return boolean true if finished else false
     */
    public boolean isFinished() {
        return isFinished;
    }

}
