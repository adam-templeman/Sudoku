package View;

import java.awt.Dimension;
import javax.swing.*;

/**
 * Class is used from CS124 group project.
 * Class contains methods to chow and hide frames.
 * 
 * @author Adam Templeman aet9, Andrei Stanica afs9, Asa Carrington agc9
 */
public class SimpleFrame extends JFrame {

    /**
     * The constructor sets the size, location and weather resizable to default.
     */
    SimpleFrame() {
        this.setSize(new Dimension(260, 260));
        this.setLocation(300, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * this show it method just sets the frame as visible using
     * default parameters
     */
    public void showIt() {
        this.setVisible(true);
    }

    /**
     * this show it method just sets the frame as visible and
     * sets the title to the string given
     *
     * @param title Title of the frame
     */
    public void showIt(String title) {
        this.setTitle(title);
        this.setVisible(true);
    }

    /**
     * this show it method just sets the frame as visible and
     * sets the title to the string given and sets the placement and size
     * of the frame.
     *
     * @param title  Title of the frame
     * @param x  x value for placement of frame
     * @param y  y value for placement of frame
     * @param w  sets width value
     * @param h  sets hight value
     */
    public void showIt(String title, int x, int y, int w, int h) {
        this.setTitle(title);
        this.setLocation(x, y);
        this.setMinimumSize(new Dimension(w, h));
        this.setSize(new Dimension(w, h));
        this.setVisible(true);
    }

    /**
     * hides the frame by setting visible to false
     */
    public void hideIt() {
        this.setVisible(false);
    }
}
