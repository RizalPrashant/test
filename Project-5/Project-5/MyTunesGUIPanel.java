
/**
 * Demonstrates how to replace a panel with a fresh one. Helpful for implementing
 * new game buttons.
 * 
 * @author PRASHANT RIZAL
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyTunesGUIPanel extends JPanel {
	/** The data representing the list of photos in the album (the "model") */
	private MyTunesPlayList playList;

	private JButton[][] songSquareButtons;
	/**
	 * The GUI representation of the list of photos in the album (the "view")
	 */
	private JList<Song> songList;

	private JLabel songLabel;
	private JButton removeSong;
	private JButton addSong;
	private JButton nextButton;
	private JButton prevButton;
	private JButton playButton;
	private JButton moveUpButton;
	private JButton moveDownButton;
	private JButton stopButton;
	private JLabel topLabel;
	private JLabel botLabel;
	// private Song[][] songSquare;

	private JPanel grid;

	private Timer timer = new Timer("songTimer");

	/**
	 * Panel creation
	 */
	public MyTunesGUIPanel() {
		String doubleDecimalFormat = "#.00";
		DecimalFormat format = new DecimalFormat(doubleDecimalFormat);

		setLayout(new BorderLayout());

		playList = new MyTunesPlayList(new File("sounds/playlist.txt"));
		Song[][] songSquare = playList.getMusicSquare();
		// System.out.println(playList);

		JPanel leftPanel = new JPanel();

		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		songList = new JList<Song>();
		songList.setListData(playList.getSongArray());
		songList.setSelectedIndex(0);

		JScrollPane scrollPane = new JScrollPane(songList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(500, 300));
		leftPanel.add(scrollPane);

		JPanel preview = new JPanel();

		songLabel = new JLabel();

		preview.add(songLabel);

		JPanel control = new JPanel();

		removeSong = new JButton("Remove Song");
		addSong = new JButton("Add Song");
		nextButton = new JButton(">");
		prevButton = new JButton("<");
		playButton = new JButton("Play");
		moveUpButton = new JButton("^");
		stopButton = new JButton("STOP");

		botLabel = new JLabel("Nothing");

		moveDownButton = new JButton("v");

		ControlListener buttoncontrol = new ControlListener();

		prevButton.addActionListener(buttoncontrol);
		moveUpButton.addActionListener(buttoncontrol);
		moveDownButton.addActionListener(buttoncontrol);
		addSong.addActionListener(buttoncontrol);
		removeSong.addActionListener(buttoncontrol);
		nextButton.addActionListener(buttoncontrol);
		playButton.addActionListener(buttoncontrol);
		stopButton.addActionListener(buttoncontrol);
		leftPanel.add(preview);
		control.add(addSong);
		leftPanel.add(control);
		control.add(removeSong);

		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
		box.add(prevButton);
		box.add(playButton);
		box.add(nextButton);
		box.add(stopButton);
		box.add(botLabel);
		this.add(box, BorderLayout.SOUTH);
		topLabel = new JLabel(playList.getName() + "Playlist Loaded : " + playList.getNumSongs() + " songs "
				+ format.format((double) playList.getTotalPlayTime() / 60) + " minutes ");
		JPanel movePanel = new JPanel();
		movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.X_AXIS));
		movePanel.add(moveUpButton);
		movePanel.add(moveDownButton);
		movePanel.add(topLabel);
		this.add(movePanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		// draws the initial grid.
		drawGrid();

	}

	/**
	 * Given the number of times a song has been played, this method will return
	 * a corresponding heat map color.
	 *
	 * Sample Usage: Color color = getHeatMapColor(song.getTimesPlayed());
	 *
	 * This algorithm was borrowed from:
	 * http://www.andrewnoske.com/wiki/Code_-_heatmaps_and_color_gradients
	 *
	 * @param plays
	 *            The number of times the song that you want the color for has
	 *            been played.
	 * @return The color to be used for your heat map.
	 */
	private Color getHeatMapColor(int plays) {
		double minPlays = 0, maxPlays = PlayableSong.MAX_PLAYS; // upper/lower
																// bounds
		double value = (plays - minPlays) / (maxPlays - minPlays); // normalize
																	// play
																	// count

		// The range of colors our heat map will pass through. This can be
		// modified if you
		// want a different color scheme.
		Color[] colors = { Color.CYAN, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
		int index1, index2; // Our color will lie between these two colors.
		float dist = 0; // Distance between "index1" and "index2" where our
						// value is.

		if (value <= 0) {
			index1 = index2 = 0;
		} else if (value >= 1) {
			index1 = index2 = colors.length - 1;
		} else {
			value = value * (colors.length - 1);
			index1 = (int) Math.floor(value); // Our desired color will be after
												// this index.
			index2 = index1 + 1; // ... and before this index (inclusive).
			dist = (float) value - index1; // Distance between the two indexes
											// (0-1).
		}

		int r = (int) ((colors[index2].getRed() - colors[index1].getRed()) * dist) + colors[index1].getRed();
		int g = (int) ((colors[index2].getGreen() - colors[index1].getGreen()) * dist) + colors[index1].getGreen();
		int b = (int) ((colors[index2].getBlue() - colors[index1].getBlue()) * dist) + colors[index1].getBlue();

		return new Color(r, g, b);
	}

	private void drawGrid() {

		if (grid != null) {
			this.remove(grid);
		}

		grid = new JPanel();
		Song[][] songSquares = playList.getMusicSquare();
		grid.setLayout(new GridLayout(songSquares.length, songSquares.length));
		songSquareButtons = new JButton[songSquares.length][songSquares.length];
		for (int row = 0; row < songSquares.length; row++) {
			for (int col = 0; col < songSquares.length; col++) {
				songSquareButtons[row][col] = new JButton();
				songSquareButtons[row][col].setText(songSquares[row][col].getTitle());
				ControlListener songListener = new ControlListener();
				songSquareButtons[row][col].addActionListener(songListener);
				grid.add(songSquareButtons[row][col]);
				Color color = getHeatMapColor(songSquares[row][col].getTimesPlayed());
				songSquareButtons[row][col].setBackground(color);
				songSquareButtons[row][col].setOpaque(true);

			}

		}

		this.add(grid, BorderLayout.CENTER);
		this.revalidate();

	}

	private class ControlListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			int index = songList.getSelectedIndex();

			// TODO: Update the index depending on which button was clicked.
			if (event.getSource() == moveDownButton) {
				playList.moveDown(index);
				songList.setListData(playList.getSongArray());
				if (index != playList.getNumSongs() - 1) {
					songList.setSelectedIndex(index + 1);
				} else {
					songList.setSelectedIndex(0);
				}
				drawGrid();
			} else if (event.getSource() == moveUpButton) {
				playList.moveUp(index);
				songList.setListData(playList.getSongArray());
				if (index != 0) {
					songList.setSelectedIndex(index - 1);
				} else {
					songList.setSelectedIndex(playList.getNumSongs() - 1);
				}
				drawGrid();
			}

			else if (event.getSource() == nextButton) {

				index++;
				if (index == playList.getNumSongs()) {
					index = 0;
				}
				songList.setSelectedIndex(index);
				timer.cancel();
				playWithTimer(index);
			}

			else if (event.getSource() == prevButton) {

				index--;
				if (index == -1) {
					index = playList.getNumSongs() - 1;
				}
				songList.setSelectedIndex(index);
				timer.cancel();
				playWithTimer(index);
			}

			else if (event.getSource() == playButton) {
				playWithTimer(index);

			} else if (event.getSource() == stopButton) {
				timer.cancel();
				playList.stop();
			}

			else if (event.getSource() == moveDownButton) {

			} else if (event.getSource() == addSong) {
				String thisWay = "#.00";
				DecimalFormat format = new DecimalFormat(thisWay);
				JPanel formInputPanel = new JPanel();
				formInputPanel.setLayout(new BoxLayout(formInputPanel, BoxLayout.Y_AXIS));

				JTextField titleField = new JTextField(20);
				JTextField artistField = new JTextField(20);
				JTextField playTimeField = new JTextField(20);
				JTextField fileField = new JTextField(20);

				formInputPanel.add(new JLabel("Song Title: "));
				formInputPanel.add(titleField);
				formInputPanel.add(new JLabel("Artist : "));
				formInputPanel.add(artistField);
				formInputPanel.add(new JLabel("PlayTime: "));
				formInputPanel.add(playTimeField);
				formInputPanel.add(new JLabel("File: "));
				formInputPanel.add(fileField);
				int result = JOptionPane.showConfirmDialog(null, formInputPanel, "Add Song",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {

					String title = titleField.getText();
					String artist = artistField.getText();
					int playTime = Integer.parseInt(playTimeField.getText());
					String file = fileField.getText();
					Song newSong = new Song(title, artist, playTime, file);
					playList.addSong(newSong);
					songList.setListData(playList.getSongArray());
					songList.setSelectedIndex(0);
					topLabel.setText(playList.getName() + "Playlist Loaded : " + playList.getNumSongs() + " songs "
							+ format.format((double) playList.getTotalPlayTime() / 60) + " minutes ");

					drawGrid();
				}

			} else if (event.getSource() == removeSong) {
				String thisWay = "#.00";
				DecimalFormat format = new DecimalFormat(thisWay);
				int selectedOption = JOptionPane.showConfirmDialog(null, "Do you want to remove the song?", "Choose",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
					playList.removeSong(index);
					topLabel.setText(playList.getName() + "Playlist Loaded : " + playList.getNumSongs() + " songs "
							+ format.format((double) playList.getTotalPlayTime() / 60) + " minutes ");
					drawGrid();
				}
				songList.setListData(playList.getSongArray());

			} else {
				Song[][] songSquare = playList.getMusicSquare();
				for (int i = 0; i < songSquare.length; i++) {
					for (int j = 0; j < songSquare.length; j++) {

						if (event.getSource() == songSquareButtons[i][j]) {
							songList.setSelectedValue(songSquare[i][j], true);
							timer.cancel();
							playWithTimer(songList.getSelectedIndex());
							drawGrid();
						}
					}
				}
			}
			botLabel.setText(songList.getSelectedValue().getTitle() + " BY " + songList.getSelectedValue().getArtist());

		}
	}
	
	/**
	 * given an index, this method will play the song in that index and start a timer for the next song.
	 * @param index of the song to play.
	 */
	private void playWithTimer(int index){
		songList.setSelectedIndex(index);
		this.revalidate();
		playList.play(index);
		int timeToPlayMilisecond = (playList.getSongList().get(index).getPlayTime()) * 1000;
		timer = new Timer("songPlayer");
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				playWithTimer(index + 1);
			}
		};
		timer.schedule(task, timeToPlayMilisecond);
	}
	
	/**
	 * list selection listener method
	 */
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
			// System.out.println(playList.);
		}
	}

}