package View;

import java.awt.BorderLayout;

/**
 * SudokuAlgFrame class Contains the frame used to show the solving panel.
 *
 * @author aet9
 */
public class SudokuAlgFrame extends SimpleFrame {


    private SudokuAlgPanel sAPanel;

    /**
     * Constructor creates a new Sudoku alg panel and adds it to the frame.
     */
    public SudokuAlgFrame() {
        sAPanel = new SudokuAlgPanel();
        this.add(sAPanel, BorderLayout.CENTER);
        pack();
    }

    /**
     * Method passes the number and the row and column of the cell
     * to be changed to the SudokuPanel
     *
     * @param num number to be placed in cell
     * @param row row of cell
     * @param col column of cell
     */
    public void setNumberAlg(String num, int row, int col) {
        sAPanel.setNumber(num, row, col);
    }
}
