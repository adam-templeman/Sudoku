package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * SudokuAlgPanel class contains a two dimensional array of JTextFields used to
 * show the solving board.
 * 
 * @author aet9
 */
public class SudokuAlgPanel extends JPanel{

    private JTextField[][] board;
    private GridBagLayout gbl;

    /**
     * Constructor creates array of JTextFields to use as board, sets layout
     * and background
     */
    public SudokuAlgPanel() {
        gbl = new GridBagLayout();
        gbl.maximumLayoutSize(this);
        this.setLayout(gbl);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        board = new JTextField[9][9];
        makeBoard();
    }

    /**
     * Method sets the number in the cell indicated.
     *
     * @param num number to be placed in cell
     * @param row row of cell for number to be placed
     * @param col of cell for number to be placed
     */
    public void setNumber(String num, int row, int col) {
        board[row][col].setText(num);
    }

    /**
     * Method creates 81 JTextFields to show each number in and adds each
     * one to the panel.
     */
    public void makeBoard() {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        JTextField cell;
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                cell = new JTextField(9);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                cell.setEditable(false);
                cell.setBackground(Color.WHITE);
                board[i][j] = cell;
                c.weightx =1;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = j;
                c.gridy = i;
                this.add(board[i][j], c);
            }
        }
    }
}
