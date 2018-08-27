import java.awt.Font;
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
 * Displays a JList of Photos and a preview of the photo.
 * This class manages layout of controls and also handles events.
 *
 * @author CS121 Instructors
 * @author Prashant Rizal
 * Reflection:
 * 1) On the action performed of the onclick listener event, I first initialized the index to 0 .Then, when next button is clicked I simply increased index by 1 by using if-else.
 * Similarly, I decreased index by 1 when pressing previous button. Also, when the index count gets -1 from 0, I set the index to 5, which is the last photo and 
 * I set the index to 0 when the index count reaches 6.
 * 2) This project has helped me to familiarized myself with the components of the swing layout. I now have good idea of using JButtons, panels etc.
 */
@SuppressWarnings("serial")
public class PhotoAlbumGUIPanel extends JPanel
{
	/** The data representing the list of photos in the album (the "model") */
	private PhotoAlbum album;

	/** The GUI representation of the list of photos in the album (the "view") */
	private JList<Photo> photoList;

	private JLabel imageLabel;  // Displays the image icon inside of the preview panel
	private JButton nextButton; // Selects the next image in the photoList
	private JButton prevButton; // Selects the previous image in the photoList */

	/**
	 * Instantiates the photo album and adds all of the components to the JPanel.
	 */
	public PhotoAlbumGUIPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		// Instantiate the album object and print it to the command line
		//      to make sure it worked.
		
		
		album = new PhotoAlbum("Boise", "photos/album.dat");
		System.out.println(album);
		
		
		
		// TODO: Instantiate the JList<Photo> photoList object (declared above) and
		//      set the list data to album.getPhotoArray().
		//      Set the selected index of the photoList to position 0 to select the
		//      first photo by default.
		
		photoList = new JList<Photo>();
		photoList.setListData(album.getPhotoArray());
		photoList.setSelectedIndex(0);
		// TODO: Add a ListSelectionListener to the photoList
		photoList.addListSelectionListener(new PhotoListListener());
		// TODO: Create a JScrollPane containing the photoList.
		JScrollPane scrollPane = new JScrollPane(photoList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// TODO: Add the scrollPane to this panel.
		add(scrollPane); 

		// TODO: Create a new preview sub-panel.
		JPanel preview = new JPanel();
		// TODO: Instantiate the imageLabel (declared above) as an empty JLabel().
		imageLabel = new JLabel();
		
		// TODO: Add the imageLabel to the previewPanel and add the previewPanel
		//      to this panel.
		preview.add(imageLabel);
		
		this.add(preview);
		
		// TODO: Use the displayPhoto() method (provided below) to display the
		//      photo currently selected in the photoList.
		this.displayPhoto(photoList.getSelectedValue());
		// TODO: Update the valueChanged method of the PhotoListListener (below) to
		//       display the selected photo whenever you click a new photo in the list.
		
		// TODO: Create a sub-panel for control buttons.
			JPanel control = new JPanel();
			
		// TODO: Initialize the prevButton and nextButton and add the same
		//      ControlListener to both.
			nextButton = new JButton(">");
			prevButton = new JButton("<");
			ControlListener buttoncontrol = new ControlListener();
			nextButton.addActionListener(buttoncontrol);
			prevButton.addActionListener(buttoncontrol);
			
			
			
		// TODO: Add both buttons to the controlPanel and add the controlPanel
		//       to this panel.
			
			control.add(prevButton);
			this.add(control);
			control.add(nextButton);
			this.add(control);
			
	}
	

	private class PhotoListListener implements ListSelectionListener
	{
		/* (non-Javadoc)
		 * @see java.awt.event.ListSelectionListener#valueChanged(java.awt.event.ListSelectionEvent)
		 */
		@Override
		public void valueChanged(ListSelectionEvent event)
		{
			//TODO: Use the displayPhoto() method (provided below) to display the
			// photo currently selected in the photoList.
			displayPhoto(photoList.getSelectedValue());
		}
	}

	private class ControlListener implements ActionListener
	{
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			// Find index of photo that is currently selected.
			int index = photoList.getSelectedIndex();
			
			// TODO: Update the index depending on which button was clicked.
			if (event.getSource()==nextButton){
				index++;
			}
			if (event.getSource()==prevButton){
				index--;
			}
			if (index==-1){
				index=5;
			}
			if (index==6){
				index=0;
			}
			photoList.setSelectedIndex(index);
		}
	}

	/**
	 * Updates the photo on the preview panel.
	 * @param photo The photo to display.
	 */
	private void displayPhoto(Photo photo)
	{
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(photo.getFile()));
			imageLabel.setIcon(icon);
		} catch (IOException ex) {
			imageLabel.setText("Image not found :(");
		}
	}

}
