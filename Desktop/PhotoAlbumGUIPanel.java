import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Displays a JList of Photos and a preview of the photo. This class manages
 * layout of controls and also handles events.
 *
 * @author CS121 Instructors
 * @author Prashant Rizal Reflection: 1) I calculated photosquare.length then compared it with the size of the buttons to make sure that the 2D photo and the button align together. Then depending on what button is clicked, I made sure that photo is selected because the dimension of that button was fixed in the place of the image.. 2) 
 * 2)  When adding the action listener, I made sure that the action listener of the buttons of Photosquare and the list made sure to point at the same image. That way they were in sync with each other.
 * 3) This project has helped me to familiarized myself with
 *         the components of the swing layout. I now have good idea of using
 *         JButtons, panels etc.
 */
@SuppressWarnings("serial")
public class PhotoAlbumGUIPanel extends JPanel {
	/** The data representing the list of photos in the album (the "model") */
	private PhotoAlbum album;
	private Photo[][] photoSquare;
	private JButton[][] photoSquareButtons;
	/**
	 * The GUI representation of the list of photos in the album (the "view")
	 */
	private JList<Photo> photoList;

	private JLabel imageLabel; // Displays the image icon inside of the preview
								// panel
	private JButton nextButton; // Selects the next image in the photoList
	private JButton prevButton; // Selects the previous image in the photoList
								// */

	/**
	 * Instantiates the photo album and adds all of the components to the
	 * JPanel.
	 */
	public PhotoAlbumGUIPanel() {
		


		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new BorderLayout());
		
		// Instantiate the album object and print it to the command line
		// to make sure it worked.

		album = new PhotoAlbum("Boise", "photos/album.dat");
		System.out.println(album);

		// TODO: Instantiate the JList<Photo> photoList object (declared above)
		// and
		// set the list data to album.getPhotoArray().
		// Set the selected index of the photoList to position 0 to select the
		// first photo by default.

		JPanel leftPanel = new JPanel();
		
		leftPanel.setLayout(new BoxLayout(leftPanel,BoxLayout.Y_AXIS));
		
		photoList = new JList<Photo>();
		photoList.setListData(album.getPhotoArray());
		photoList.setSelectedIndex(0);
		// TODO: Add a ListSelectionListener to the photoList
		photoList.addListSelectionListener(new PhotoListListener());
		// TODO: Create a JScrollPane containing the photoList.
		JScrollPane scrollPane = new JScrollPane(photoList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// TODO: Add the scrollPane to this panel.
		leftPanel.add(scrollPane);

		// TODO: Create a new preview sub-panel.
		JPanel preview = new JPanel();
		// TODO: Instantiate the imageLabel (declared above) as an empty
		// JLabel().
		imageLabel = new JLabel();

		// TODO: Add the imageLabel to the previewPanel and add the previewPanel
		// to this panel.
		preview.add(imageLabel);
	
		// TODO: Use the displayPhoto() method (provided below) to display the
		// photo currently selected in the photoList.
		this.displayPhoto(photoList.getSelectedValue());
		// TODO: Update the valueChanged method of the PhotoListListener (below)
		// to
		// display the selected photo whenever you click a new photo in the
		// list.

		// TODO: Create a sub-panel for control buttons.
		JPanel control = new JPanel();

		// TODO: Initialize the prevButton and nextButton and add the same
		// ControlListener to both.
		nextButton = new JButton(">");
		prevButton = new JButton("<");
		ControlListener buttoncontrol = new ControlListener();
		nextButton.addActionListener(buttoncontrol);
		prevButton.addActionListener(buttoncontrol);

		// TODO: Add both buttons to the controlPanel and add the controlPanel
		// to this panel.

		leftPanel.add(preview);
		control.add(prevButton);
		leftPanel.add(control);
		control.add(nextButton);
		leftPanel.add(control);
		
		photoSquare = album.getPhotoSquare();
		int numPhotos = photoSquare.length;
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(numPhotos, numPhotos));
		
		photoSquareButtons = new JButton[numPhotos][numPhotos];
		
		for (int row = 0; row < photoSquareButtons.length; row++) {
			for (int col = 0; col < photoSquareButtons[row].length; col++) {
				photoSquareButtons[row][col] = new JButton();
				
					try {
						ImageIcon icon = new ImageIcon(ImageIO.read(photoSquare[row][col].getFile()));
						photoSquareButtons[row][col].setIcon(icon);
						//TODO you have to create your action listner 
						photoSquareButtons[row][col].addActionListener(new PhotoSquareListener());
						grid.add(photoSquareButtons[row][col]);
						
					} catch (IOException ex) {
						imageLabel.setText("Image not found :(");
					}
				
			
		}
		}
		this.add(leftPanel,BorderLayout.WEST);
		this.add(grid,BorderLayout.CENTER);
		
		}
	

	private class PhotoListListener implements ListSelectionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ListSelectionListener#valueChanged(java.awt.event.
		 * ListSelectionEvent)
		 */
		@Override
		public void valueChanged(ListSelectionEvent event) {
			// TODO: Use the displayPhoto() method (provided below) to display
			// the
			// photo currently selected in the photoList.
			displayPhoto(photoList.getSelectedValue());
		}
	}

	private class ControlListener implements ActionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event) {
			// Find index of photo that is currently selected.
			int index = photoList.getSelectedIndex();

			// TODO: Update the index depending on which button was clicked.
			if (event.getSource() == nextButton) {
				index++;
			}
			if (event.getSource() == prevButton) {
				index--;
			}
			if (index == -1) {
				index = 5;
			}
			if (index == 6) {
				index = 0;
			}
			photoList.setSelectedIndex(index);
		}
	}

	/**
	 * Updates the photo on the preview panel.
	 * 
	 * @param photo
	 *            The photo to display.
	 */
	private void displayPhoto(Photo photo) {
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(photo.getFile()));
			imageLabel.setIcon(icon);
		} catch (IOException ex) {
			imageLabel.setText("Image not found :(");
		}
	}
	

	
	private class PhotoSquareListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for (int row = 0; row < photoSquareButtons.length; row++) {
				for (int col = 0; col < photoSquareButtons[row].length; col++) {
				
					if(e.getSource() == photoSquareButtons[row][col]){
						displayPhoto(photoSquare[row][col]);
						photoList.setSelectedValue(photoSquare[row][col], true);
					}
					
				}
			
		}
		
	}

}
	}
