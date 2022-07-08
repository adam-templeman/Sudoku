package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * This class is from my CS124 group project from last year.
 * The class is a frame for showing a help file.
 *
 * @author Adam Templeman aet9, Andrei Stanica afs9, Asa Carrington agc9
 */
public class HelpFrame extends SimpleFrame{

   /**
    * This is the help frame. It will display the help.
    */
    public HelpFrame() {
        this.setTitle("Help");
	this.setSize(350, 300);
	BorderLayout bLayout = new BorderLayout();
	this.setLayout(bLayout);
	this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	this.setLocation(600, 200);

	JTabbedPane jtPanel = new JTabbedPane();
	this.add(jtPanel, BorderLayout.CENTER);

	JPanel mhPanel = new JPanel();
	JPanel ghPanel = new JPanel();

	jtPanel.addTab("Game Help", ghPanel);
	jtPanel.addTab("Menu Help", mhPanel);

	JTextArea menuHelp = new JTextArea();
	menuHelp.setText(" File - Help opens this window." +
                         "\n File - Open will open a new window allowing you to " +
                         "choose a Sudoku file to load into the solver." +
                         "\n File - Start will start the solver.");

        menuHelp.setEditable(false);
	menuHelp.setLineWrap(true);
	menuHelp.setWrapStyleWord(true);

	JScrollPane menuScrollPane = new JScrollPane(menuHelp);
	menuScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	menuScrollPane.setPreferredSize(new Dimension(310, 230));
	mhPanel.add(menuScrollPane);

	JTextArea gameHelp = new JTextArea();
	gameHelp.setText(" The solver loads sud files into the application and " +
                         " creates solutions to them. The user can view the actual" +
                         " process of the solver while the solver solves the sudoku");

        gameHelp.setEditable(false);
	gameHelp.setLineWrap(true);
	gameHelp.setWrapStyleWord(true);

	JScrollPane gameScrollPane = new JScrollPane(gameHelp);
	gameScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	gameScrollPane.setPreferredSize(new Dimension(310, 230));

        ghPanel.add(gameScrollPane);

    }
}
