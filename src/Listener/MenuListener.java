package Listener;

import View.HelpFrame;
import View.SudokuFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author aet9
 */
public class MenuListener implements ActionListener{

    private SudokuFrame sFrame;
    final JFileChooser fc = new JFileChooser();


    /**
     * Constriuctor sests the sudoku frame as a variable for use in action
     * performed method.
     *
     * @param sF sudoku frame to be sert.
     */
    public MenuListener(SudokuFrame sF) {
        sFrame = sF;
        fc.setCurrentDirectory(new File("games/"));
    }

    /**
     * Method called when a menu iten is clicked. It determines which item was
     * clicked and calls the appropriate methods.
     *
     * @param e menu item clicked event
     */
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
	if (actionCommand.equalsIgnoreCase("help")) {
            HelpFrame hFrame = new HelpFrame();
            hFrame.showIt();
    	}
        if (actionCommand.equalsIgnoreCase("open")) {
            int returnVal = fc.showOpenDialog(sFrame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                sFrame.loadNew(file);
            }
        }
        if (actionCommand.equalsIgnoreCase("start")) {
            sFrame.start();
        }
    }

}
