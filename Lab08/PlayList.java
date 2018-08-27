/*
 * Generates a play list of songs
 *
 * @author Prashant Rizal
 */
import java.util.ArrayList;

public class PlayList {
	
	private String name;
	private int playCount;
	private ArrayList<Song> songList;
	
	public PlayList(String name){
		this.name = name;
		this.playCount = 0;
		this.songList = new ArrayList<Song>();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	/* Duplicate Method
	public ArrayList<Song> getSongList() {
		return songList;
	}
	*/

	public void setSongList(ArrayList<Song> songList) {
		this.songList = songList;
	}

	public int getPlayCount() {
		return playCount;
	}
	
	//*************************************************************
	
	public void addSong(Song s)
	{
	    // TODO: add the song to the songs list
		songList.add(s);
	}
	public void removeSong(int id)
	{
	    // TODO: remove the song at position 'id' from the songs list
		if (!(id > songList.size()-1)){
			songList.remove(id);
		}
	}
	public int getNumSongs()
	{
	    // TODO: return the size of the songs list
		return songList.size();
	}
	public ArrayList<Song> getSongList()
	{
	    // TODO: return the songs list
		return songList;
	}
	public void playAll()
	{
	    // TODO: use a for-each loop to play all songs in the list.
	    // TODO: increment the play count.
		for (int i = 0; i < songList.size(); i++){
			songList.get(i).play();
			playCount++;
		}
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("------------------\n");
		builder.append(name + " (" + songList.size() + " songs)\n");
		builder.append("------------------\n");
		for(int i = 0; i < songList.size(); i++) {
			builder.append("(" + i + ") ");
			builder.append(songList.get(i).toString() + "\n");
		}
		if (songList.size()>1){
			builder.append("------------------\n");
		}

		if (songList.size() < 1){
			builder.append("There are no songs.\n");
			builder.append("------------------\n");
		}
		return builder.toString();
		
	}
	
}
