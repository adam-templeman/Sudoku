package Controller;

import Algorithms.Algorithm;
import Model.Reader;
import java.io.File;

/**
 * ReadController class controlls the reading of the sudoku into the board via
 * the reader class.
 *
 * @author aet9
 */
public class ReadController {

    private Reader reader;
    private Algorithm alg;
    private File file;
    private static int rows = 9;
    private static int columns = 9;
    
    /**
     * Constructor sets alg variable.
     */
    public ReadController() {
        alg = Classes.alg;
    }


    /**
     * Method starts a new game using the file specified
     *
     * @param fileName file to be used
     */
    public void startNew(File fileName) {
        file = fileName;
        alg.stop();
        loadGame(file);
    }
    
    /**
     * Method starts the algorithm running.
     */
    public void start() {
        alg.start();
    }

    /**
     * Method creates a new reader and fills the board in with the numbers from
     * the new file.
     *
     * @param fileName file to be loaded
     */
    public void loadGame(File fileName) {
        reader = new Reader(fileName);
        int num;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                num = reader.read();
                if (num!=0) {
                    Classes.board.setNumber(num, i, j);
                    Classes.sFrame.setNumberSudoku(num, i, j);
                }
                else {
                    Classes.board.setNumber(0, i, j);
                    Classes.sFrame.setNumberSudoku(0, i, j);
                }
            }
        }
    }
}
