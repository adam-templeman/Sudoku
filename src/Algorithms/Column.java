package Algorithms;

/**
 * The column class controlls all of the solving algorithms used on the columns.
 *
 * @author aet9
 */
public class Column implements Runnable{

    private BoardChecker bCheck;
    private Algorithm alg;
    private boolean isFinished = false;

    /**
     * Constructor for the column class asigns the perameters to variables for
     * use in the other methods.
     *
     * @param bc board checker
     * @param b board
     * @param a algorithm
     * @param solvingBoard two dimensional string array
     */
    public Column(BoardChecker bc, Algorithm a) {
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
     * Method checks each column to see if there is a number set on the board
     * if there is the number there then it removes the number from the row on
     * the solving board.
     */
    public void solveStepOne() {
        alg.numberThere();
        for (int col = 0; col<9; col++) {
            for (int num = 1; num< 10; num++) {
                int a = bCheck.checkColumn(num, col);
                if (a!=-1) {
                    alg.removeInColumn(num, col);
                }
            }
            alg.numberThere();
        }
    }

    /**
     * Method checks if there are cells in a column that are the same and appear
     * only the number of numbers in the cell, times. if there is then it
     * removes the numbers from the rest of that column.
     */
    public void solveStepTwo() {
        alg.numberThere();
        for (int i = 0; i<9; i++) {
            for (int j = 0; j< 9; j++) {
                int numOf = 0;
                int num = alg.getSolvingBoardNum(j, i).length();
                if (num==0) continue;
                String row = new String();
                for (int h = 0; h<9; h++) {
                    if (alg.cellEquals(j, i, h, i)) {
                        numOf++;
                        row += h;
                    }
                }
                if (numOf==num&&num!=1) {
                    String cell = alg.getSolvingBoardNum(j, i);
                    for (int a = 0; a<num; a++) {
                        int number = Integer.parseInt(cell.charAt(a)+"");
                        alg.removeInColumn(number, i);
                    }
                    for (int g = 0; g<row.length(); g++) {
                        int r = Integer.parseInt(row.charAt(g)+"");
                        alg.setSolvingBoardNum(cell, r, i);
                    }
                }
            }
        }
        alg.numberThere();
    }

    /**
     * Method finds any numbers in a column that in one box only apear in that
     * column, it then removes the numbers from the rest of the column.
     */
    public void solveStepThree() {
        alg.numberThere();
        for (int col = 0; col<9; col++) {
            for (int rowStep = 0; rowStep<3; rowStep++) {
                for (int num = 1; num<10; num++) {
                    int numOf = 0;
                    String rowContNum = "";
                    for (int row = 0; row<3; row++) {
                        if (alg.getSolvingBoardNum((rowStep*3)+row, col).contains(num+"")) {
                            numOf++;
                            rowContNum += ""+((rowStep*3)+row);
                        }
                    }
                    if (numOf == 0) {
                        continue;
                    }
                    else if (alg.numberOfbox(num, (rowStep*3), alg.nearestCorner(col))==numOf) {
                        alg.removeInColumn(num, col);
                        for (int h = 0; h<rowContNum.length(); h++) {
                            int temp = Integer.parseInt(""+rowContNum.charAt(h));
                            alg.setSolvingBoardNum(alg.getSolvingBoardNum(temp, col)+num, temp, col);
                        }
                    }
                }
            }
        }
        alg.numberThere();
    }

   /**
    * Method checks weather there is a cell in the column that only
    * contains one number or if a number only appears once in that column,
    * it then sets this number on the board and removes the number from
    * that row, column and box.
    */
    private void instanceOf() {
        alg.numberThere();
        for (int col = 0; col<9; col++) {
            int row = 0;
            int numOf = 0;
            for (int num = 1; num<10; num++) {
                numOf = 0;
                for (int j = 0; j<9; j++) {
                    char ch = (""+(num)).charAt(0);
                    String s = alg.getSolvingBoardNum(j, col);
                    int a = s.indexOf(ch);
                    if (s.length()==1) {
                        alg.numberThereCell(j, col);
                    }
                    if (a!=-1) {
                        numOf++;
                        row = j;
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
