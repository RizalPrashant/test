import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Animated program with a ball bouncing off the program boundaries
 * @author mvail
 * @author yourname
 */
public class BouncyBall extends JPanel
{
	private final int INIT_WIDTH = 600;
	private final int INIT_HEIGHT = 400;
	private final int DELAY = 70;//milliseconds between Timer events
	private Random rand; //random number generator
	private int x, y; //anchor point coordinates
	private int xDelta, yDelta; //change in x and y from one step to the next
	private final int DELTA_RANGE = 20; //range for xDelta and yDelta
	private  int RADIUS = 10; //circle radius
	
	private int MAX_RADIUS= 50;
	private int MIN_RADIUS = 10;
	private int redDelta=2;
	private int greenDelta=2 ;
	private int blueDelta=2;
	private int rDELTA=1;
	private int red, blue, green;
	

	/**
	 * Draws a filled oval with random color and dimensions.
	 *
	 * @param g Graphics context
	 * @return none
	 */
	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		//clear canvas
		//g.setColor(getBackground());
		//g.fillRect(0, 0, width, height);

		//CALCULATE NEW X
		x += xDelta;
		
		
		
		
		
		if (x+RADIUS>=width)
		{
		   xDelta *= -1;
		   x= width-RADIUS;
		}
		else if(x-RADIUS<=0)
		{
			xDelta *= -1;
			x=RADIUS;
		}
		//TODO: needs more to stay in-bounds

		//CALCULATE NEW Y
		y += yDelta;
		
		if (y+RADIUS>=height)
		{
			yDelta *= -1;
			y = height-RADIUS;
		}
		else if(y-RADIUS<=0)
		{
			yDelta *= -1;
			y=RADIUS;
		}
		//TODO: needs more to stay in-bounds
		RADIUS = RADIUS + rDELTA;
		if (RADIUS>=MAX_RADIUS || RADIUS<=MIN_RADIUS){
			rDELTA=-rDELTA;
		}

		//NOW PAINT THE OVAL
		red=rand.nextInt(256);
		blue=rand.nextInt(256);
		green=rand.nextInt(256);
      
		
		
		
		Color random_ball_color = new Color(red,green,blue);
		g.setColor(random_ball_color);
		g.fillOval(x-RADIUS, y-RADIUS, 2*RADIUS, 2*RADIUS);

		//Makes the animation smoother
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 * This method also sets up a Timer that will call
	 * paint() with frequency specified by the DELAY
	 * constant.
	 */
	public BouncyBall()
	{
		setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
		this.setDoubleBuffered(true);
		setBackground(Color.black);
		

		rand = new Random();	//instance variable for reuse in paint()
		
		
		
        int start_point_x =rand.nextInt(INIT_WIDTH-RADIUS);
        int start_point_y = rand.nextInt(INIT_HEIGHT-RADIUS);
		//initial ball location within panel bounds
		
		x = start_point_x;
		y = start_point_y;
		//TODO: replace centered starting point with a random
		// position anywhere in-bounds - the ball should never
		// extend out of bounds, so you'll need to take RADIUS
		// into account

		//deltas for x and y
		int delta_random = rand.nextInt(DELTA_RANGE)-(DELTA_RANGE/2);
		xDelta = delta_random;
		yDelta = delta_random;
		//TODO: replace with random deltas from -DELTA_RANGE/2
		// to +DELTA_RANGE/2
		
		
		
		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically
	 * DO NOT MODIFY
	 */
	private void startAnimation() {
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		};
		new Timer(DELAY, taskPerformer).start();
	}

	/**
	 * Starting point for the BouncyBall program
	 * DO NOT MODIFY
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Bouncy Ball");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BouncyBall());
		frame.pack();
		frame.setVisible(true);
	}

}
