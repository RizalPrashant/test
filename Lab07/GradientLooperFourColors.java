import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Draws gradients across the width of the panel
 * @author ?
 */
@SuppressWarnings("serial")
public class GradientLooperFourColors extends JPanel {
	/* This method draws on the Graphics context.
	 * This is where your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics canvas) 
	{
		//ready to paint
		super.paintComponent(canvas);
		
		//account for changes to window size
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height
		
		final int GRADIENT_DIVISIONS = 256;
		final int NUM_GRADIENT_BARS = 4;

		//TODO: Your code goes here
		int scale = width/GRADIENT_DIVISIONS;
		
		for (int i=0; i<GRADIENT_DIVISIONS; i++){
			for (int j=0; j<NUM_GRADIENT_BARS; j++){
				switch (j) {
				case 0 :
					canvas.setColor(new Color(i,i,i));
					canvas.fillRect(i*scale, 0, width, height/4);
					break;
					
				case 1:
						canvas.setColor(new Color(0,0,i));
						canvas.fillRect(i*scale, height/4, width, height/4);
						break;
				case 2 :
						canvas.setColor(new Color(i,0,0));
						canvas.fillRect(i*scale, height/2, width, height/4);
						break;
				case 3:
						canvas.setColor(new Color(0,i,0));
						canvas.fillRect(i*scale, 3*height/4, width, height/4);
				default:
						break;
			}
		}
		
		}
	}

	/**
	 * DO NOT MODIFY
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 */
	public GradientLooperFourColors() 
	{
		setBackground(Color.black);
		int initWidth = 768;
		int initHeight = 512;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);
	}

	/**
	 * DO NOT MODIFY
	 * Starting point for the program
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("GradientLooperGrayscale");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GradientLooperFourColors());
		frame.pack();
		frame.setVisible(true);
	}
}
