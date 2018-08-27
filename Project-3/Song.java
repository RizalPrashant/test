/*
 * Song Object
 *
 * @author Prashant Rizal
 */
import java.text.DecimalFormat;

public class Song{
	private String title="";
	private String artist="";
	private String fileName="";
	private String genre="";
	private int playTime;
	
	public Song(String title, String artist, int playTime, String fileName){
		this.title=title;
		this.artist=artist;
		this.fileName=fileName;
		this.playTime=playTime;		
	}
	
	public Song(String title, String artist){
		this.title=title;
		this.artist=artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	
	//copied
	public void play()
	{
	    TimedAudioClip clip = new TimedAudioClip(title, fileName, playTime);
	    clip.playAndWait();
	}
	
	public String toString(){
		int playMinute = playTime / 60;
		int playSeconds = playTime % 60;
		String pattern="00";
		DecimalFormat songTimeFormat = new DecimalFormat(pattern);		
		
		String myTime = songTimeFormat.format(playMinute)+":"+songTimeFormat.format(playSeconds);
		String line = String.format("%-20s %-25s %-25s %-20s", title, artist, myTime, fileName);
		
		return line;
	}
}