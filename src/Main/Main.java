package Main;

import Algorithms.Algorithm;
import Controller.Classes;
import Controller.ReadController;
import Model.Board;
import View.SudokuAlgFrame;
import View.SudokuFrame;
import java.io.File;

/**
 * Main class contains Main method.
 * 
 * @author aet9
 */
public class Main {

    /**
     * Main creates instances of classes and shows the sudoku and alg frames.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        Classes.board = new Board();
        Classes.sAFrame = new SudokuAlgFrame();
        Classes.sFrame = new SudokuFrame();
        Classes.sFrame.showIt("SUDOKU");
        Classes.sAFrame.showIt("SUDOKU SOLVING VIEW", 0, 0, 800, 300);
        Classes.alg = new Algorithm();
        Classes.rc = new ReadController();
        Classes.rc.startNew(new File("games/book64.sud"));

    }

}
