package Algorithms;

import java.awt.Point;

/**
 * The box class controlls all the solving algorithms used on three by three
 * squares.
 *
 * @author aet9
 */
public class Box implements Runnable{

    private BoardChecker bCheck;
    private Algorithm alg;
    private boolean isFinished = false;

    /**
     * Constructor for the box class asigns the perameters to variables for
     * use in the other methods.
     *
     * @param bc board checker
     * @param b board
     * @param a algorithm
     * @param solvingBoard two dimensional string array
     */
    public Box(BoardChecker bc, Algorithm a) {
        bCheck = bc;
        alg = a;
    }


    public void run() {
        isFinished = false;
        solveStepOne();
        instanceOf();
        solveStepTwo();
        //multyNumber();
        isFinished = true;
    }

    /**
     * Method checks each box to see if there is a number set on the board
     * if there is the number there then it removes the number from the box
     * on the solving board.
     */
    public void solveStepOne() {
        alg.numberThere();
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                for (int h = 1; h<10; h++) {
                    Point a = bCheck.checkBox(h, i*3, j*3);
                    if (a.equals(new Point(-1,-1))) {
                        break;
                    }
                    else {
                        alg.removeInBox(h, i*3, j*3);
                    }
                }
                alg.numberThere();
            }
        }
    }


    /**
     * Method checks if there are cells in a column that are the same and appear
     * only the number of numbers in the cell, times. if there is then it
     * removes the numbers from the rest of that column.
     */
    public void solveStepTwo() {
        alg.numberThere();
        for (int rowStep = 0; rowStep<3; rowStep++) {
            for (int colStep = 0; colStep<3; colStep++) {
                for (int row = 0; row<3; row++) {
                    for (int col = 0; col<3; col++) {
                        int curRow = (rowStep*3)+row;
                        int curCol = (colStep*3)+col;
                        int numOf = 0;
                        int num = alg.getSolvingBoardNum(curRow, curCol).length();
                        if (num==0) continue;
                        String cell = new String();
                        for (int j = 0; j<3; j++) {
                            for (int h = 0; h<3; h++) {
                                if (alg.cellEquals(curRow, curCol, (rowStep*3)+j, (colStep*3)+h)) {
                                    numOf++;
                                    cell += ((rowStep*3)+j)+""+((colStep*3)+h);
                                }
                            }
                        }
                        if (numOf==num&&num!=1) {
                            String cell2 = alg.getSolvingBoardNum(curRow, curCol);
                            for (int a = 0; a<num; a++) {
                                int r = Integer.parseInt(cell2.charAt(a)+"");
                                alg.removeInBox(r, rowStep*3, colStep*3);
                            }
                            for (int g = 0; g<num; g++) {
                                int cellRow = Integer.parseInt(cell.charAt(g*2)+"");
                                int cellCol = Integer.parseInt(cell.charAt((g*2)+1)+"");
                                alg.setSolvingBoardNum(cell2, cellRow, cellCol);
                            }
                        }
                        else if (num==1) {
                            String cell2 = alg.getSolvingBoardNum(curRow, curCol);
                            int number = Integer.parseInt(cell2);
                            alg.removeFromAll(number, curRow, curCol);
                            alg.setNumber(number, curRow, curCol);
                        }
                    }
                }
            }
        }
    }

   /**
    * Method checks weather there is a cell in the box that only
    * contains one number or if a number only appears once in that box,
    * it then sets this number on the board and removes the number from
    * that row, column and box.
    */
    private void instanceOf() {
        alg.numberThere();
        int numOf = 0;
        int rowNum = 0;
        int colNum = 0;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                for (int num = 1; num<10; num++) {
                    numOf = 0;
                    for (int row = 0; row<3; row++) {
                        for (int col = 0; col<3; col++) {
                            char ch = (""+(num)).charAt(0);
                            String s = alg.getSolvingBoardNum((i*3)+row, (j*3)+col);
                            if (s.length()==1) {
                                alg.numberThereCell((i*3)+row, (j*3)+col);
                                continue;
                            }
                            int a = s.indexOf(ch);
                            if (a!=-1) {
                                numOf++;
                                rowNum = (i*3)+row;
                                colNum = (j*3)+col;
                            }
                        }
                    }
                    if (numOf==1) {
                        if (alg.checkCell(num, rowNum, colNum)) {
                            alg.removeFromAll(num, rowNum, colNum);
                            alg.setSolvingBoardNum("", rowNum, colNum);
                            //sBoard[rowNum][colNum] = "";
                            alg.setNumber(num, rowNum, colNum);
                        }
                    }
                }
            }
        }
        alg.numberThere();
    }

    // not sure how to do this
    public void multyNumber() {
        for (int rowStep = 0; rowStep<3; rowStep++) {
            for (int colStep = 0; colStep<3; colStep++) {
                String numbers = "";
                for (int num = 1; num<10; num++) {
                    if (numberOf(num, rowStep, colStep)==2) {
                        numbers += num;
                    }
                }
                String[] cells = new String[numbers.length()];
                for (int array = 0; array<numbers.length(); array++) {
                    cells[array] = alg.findNumInBox(numbers.charAt(array), rowStep, colStep);
                }
                for (int num1 = 0; num1<numbers.length(); num1++) {
                    for (int num2 = 0; num2<numbers.length(); num2++) {
                        if (num1==num2) {
                            break;
                        }
                        if (cells[num1].equals(cells[num2])) {
                            int r1 = cells[num1].charAt(1);
                            int c1 = cells[num1].charAt(2);
                            int r2 = cells[num1].charAt(3);
                            int c2 = cells[num1].charAt(4);
                            int number1 = numbers.charAt(num1);
                            int number2 = numbers.charAt(num2);
                            alg.removeInBox(number1, rowStep, colStep);
                            alg.removeInBox(number2, rowStep, colStep);
                            //sBoard[r1][c1] = ""+number1+number2;
                            //sBoard[r2][c2] = ""+number1+number2;
                        }
                    }
                }
            }
        }
    }

    /**
     * Method counts the number of the number num there are in the
     * solving board for that box.
     *
     * @param num number to count the instances of
     * @param row row of top left cell of box
     * @param col column of top left cell of box
     * @return number of num in box
     */
    public int numberOf(int num, int row, int col) {
        int numOf = 0;
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                if (alg.getSolvingBoardNum(row+i, col+j).contains(num+"")) {
                    numOf++;
                }
            }
        }
        return numOf;
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
