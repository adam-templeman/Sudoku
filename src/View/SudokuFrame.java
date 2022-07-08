package View;

import Controller.Classes;
import Listener.MenuListener;
import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * SudokuFrame class is the frame that contains the Sudoku panel for showing
 * the sudoku board.
 *
 * @author aet9
 */
public class SudokuFrame extends SimpleFrame {


    private JMenu fileMenu = new JMenu("File");
    private JMenuItem help = new JMenuItem("Help");
    private JMenuItem open = new JMenuItem("Open");
    private JMenuItem start = new JMenuItem("Start");
    private JMenuBar mb = new JMenuBar();
    private MenuListener ml;
    private SudokuPanel sPanel;


    /**
     * Constructor creates the menu and adds the sudoku panel to the frame.
     */
    public SudokuFrame() {

        ml = new MenuListener(this);

        //file menu
        fileMenu.add(help);
        help.addActionListener(ml);
        fileMenu.add(open);
        open.addActionListener(ml);
        fileMenu.add(start);
        start.addActionListener(ml);
        mb.add(fileMenu);
        
        this.setJMenuBar(mb);
        sPanel = new SudokuPanel();
        this.add(sPanel, BorderLayout.CENTER);
        
    }


    /**
     * Method passes the number and the row and column of the cell
     * to be changed to the SudokuPanel
     *
     * @param num number to be placed in cell
     * @param row row of cell
     * @param col column of cell
     */
    public void setNumberSudoku(int num, int row, int col) {
        sPanel.setNumber(num, row, col);
    }

    /**
     * Method calls startNew in read controller to start a new game
     *
     * @param fileName file to be used.
     */
    public void loadNew(File fileName) {
        Classes.rc.startNew(fileName);
    }

    public void start() {
        Classes.rc.start();
    }

    /**
     * Method shows a new window when the sudoku has finished
     */
    public void finished(){
        JOptionPane.showMessageDialog(rootPane, "Solver has finished.",
            "Message",JOptionPane.INFORMATION_MESSAGE);
        this.repaint();
    }

    /**
     * Method shows a message when loading a new game.
     */
    public void startingNew() {
        JOptionPane.showMessageDialog(rootPane, "Solver is starting a new Sudoku.",
            "Message",JOptionPane.INFORMATION_MESSAGE);
    }
}
