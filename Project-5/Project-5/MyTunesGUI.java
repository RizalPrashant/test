
/**
 * Demonstrates how to replace a panel with a fresh one. Helpful for implementing
 * new game buttons.
 * 
 * 
 * @author PRASHANT RIZAL
 */
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MyTunesGUI {
	/**
	 * @param args
	 *            unused
	 */
	public static void main(String args[]) {

		JFrame frame = new JFrame("JList Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MyTunesGUIPanel());
		frame.setPreferredSize(new Dimension(500, 400));
		frame.pack();
		frame.setVisible(true);
	}
}
