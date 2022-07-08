package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * Sudoku panel class contains a two dimensional array to show the sudoku board.
 * 
 * @author aet9
 */
public class SudokuPanel extends JPanel{

    private JTextField[][] board;
    private GridBagLayout gbl;

    /**
     * Constructor creates array of JTextFields to use as board, sets layout
     * and background
     */
    public SudokuPanel() {
        gbl = new GridBagLayout();
        gbl.maximumLayoutSize(this);
        this.setLayout(gbl);
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        board = new JTextField[9][9];
        makeBoard();

    }

    /**
     * Sets the number in the cell indicated.
     *
     * @param num number to be placed in cell
     * @param row row of cell for number to be placed
     * @param col of cell for number to be placed
     */
    public void setNumber(int num, int row, int col) {
        if (num!=0) {
            board[row][col].setText(num+"");
        }
        else {
            board[row][col].setText("");
        }
        repaint();
    }

    /**
     * MakeBoard creates 81 JTextFields to show each number in and adds each
     * one to the panel.
     */
    public void makeBoard() {
        GridBagConstraints c = new GridBagConstraints();
        //c.insets = new Insets(1,1,1,1);
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                JTextField square = new JTextField(2);
                square.setEditable(false);
                square.setHorizontalAlignment(JTextField.CENTER);
                square.setBackground(Color.WHITE);
                square.setBorder(border(i,j));
                board[i][j] = square;
                c.gridx = j;
                c.gridy = i;
                this.add(board[i][j], c);
            }
        }
    }

    /**
     * This method will make sure each cell has the right border so that
     * the sudoku board is shown. The method returns a border for the cell in
     * that position on the board.
     *
     * @param row the row in which the cell will be placed
     * @param col the column in which the cell will be placed
     * @return border for that cell
     */
    public Border border(int row, int col) {
        int n=1, e=1, s=1, w=1;
        if (row==2 || row==5 || row==8) {
            s=2;
        }
        if (col==2 || col==5 || col==8) {
            e=2;
        }
        if (row==0 || row==3 || row==6) {
            n=2;
        }
        if (col==0 || col==3 || col==6) {
            w=2;
        }
        return BorderFactory.createMatteBorder(n, w, s, e, Color.BLACK);
    }
}
