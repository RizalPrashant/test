
/*
 * Generates a play list of songs
 *
 * @author Prashant Rizal
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayList implements PlayListInterface {

	private String name;
	private int playCount;
	private ArrayList<Song> songList;

	public PlayList(String name) {
		this.name = name;
		this.playCount = 0;
		this.songList = new ArrayList<Song>();
	}

	/**
	 * Creates a play list from the song data in the given file.
	 * 
	 * @param file
	 *            The file containing the song data.
	 */
	public PlayList(File file) {
		songList= new ArrayList<Song>();
		try {
		Scanner scan = new Scanner(file);
			this.name = scan.nextLine().trim();
			while (scan.hasNextLine()) {
				String title = scan.nextLine().trim();
				String artist = scan.nextLine().trim();
				int playtime = Integer.parseInt(scan.nextLine().trim());
				String file1 = scan.nextLine().trim();
				Song song = new Song(title, artist, playtime, file1);
				this.addSong(song);
			
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.err.println("Failed to load playlist. " + e.getMessage());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Duplicate Method public ArrayList<Song> getSongList() { return songList;
	 * }
	 */

	public void setSongList(ArrayList<Song> songList) {
		this.songList = songList;
	}

	public int getPlayCount() {
		return playCount;
	}

	// *************************************************************

	public void addSong(Song s) {
		// TODO: add the song to the songs list
		songList.add(s);
	}

	public void removeSong(int id) {
		// TODO: remove the song at position 'id' from the songs list
		if (!(id > songList.size() - 1)) {
			songList.remove(id);
		}
	}

	public int getNumSongs() {
		// TODO: return the size of the songs list
		return songList.size();
	}

	public ArrayList<Song> getSongList() {
		// TODO: return the songs list
		return songList;
	}

	public void playAll() {
		// TODO: use a for-each loop to play all songs in the list.
		// TODO: increment the play count.
		for (int i = 0; i < songList.size(); i++) {
			songList.get(i).play();
			playCount++;
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("------------------\n");
		builder.append(name + " (" + songList.size() + " songs)\n");
		builder.append("------------------\n");
		for (int i = 0; i < songList.size(); i++) {
			builder.append("(" + i + ") ");
			builder.append(songList.get(i).toString() + "\n");
		}
		if (songList.size() > 1) {
			builder.append("------------------\n");
		}

		if (songList.size() < 1) {
			builder.append("There are no songs.\n");
			builder.append("------------------\n");
		}
		return builder.toString();

	}

	@Override
	public int getTotalPlayTime() {
		int count =0;
		for (int i=0; i<songList.size();i++){
			int totalTime= songList.get(i).getPlayTime();
			count+=totalTime;
		}
		return count;
	}

	@Override
	public Song[] getSongArray() {
		// TODO Auto-generated method stub
		Song[] copy = new Song[songList.size()];
		for (int i = 0; i < songList.size(); i++) {
			copy[i] = songList.get(i);
		}
		return copy;

	}

	@Override
	public int moveUp(int index) {
		Song x= songList.remove(index);
		if(index==0){
			index=songList.size();
			songList.add(index,x);}
		else{
			songList.add(index-1, x);
		
		}
		return index;
	}

	@Override
	public int moveDown(int index) {
		Song x= songList.remove(index);
		if(index==songList.size()){
			index=0;
			songList.add(index,x);}
		else{
			songList.add(index+1, x);
		
		}
		return index;
	}

	@Override
	public Song[][] getMusicSquare() {
		int numSongs = this.getNumSongs();
		int dimension_of_square = (int) Math.ceil(Math.sqrt(numSongs));
		Song[][] grid = new Song[dimension_of_square][dimension_of_square];
		
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = songList.get((row * dimension_of_square + col) % (numSongs));
			}

		}
		return grid;
	}

	}


