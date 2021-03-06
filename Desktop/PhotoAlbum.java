import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Represents a photo album.
 *
 * @author CS121 Instructors
 * @author You - complete getPhotoArray() method
 */
public class PhotoAlbum {
	private String name;
	private ArrayList<Photo> photos;

	/**
	 * Creates a new photo album with the given name from the data file located
	 * at the given file path. The album data file should have the format
	 * 
	 * <pre>
	 * Photo Name 0
	 * filepath 0
	 * Photo Name 1
	 * filepath 1
	 * ...
	 * </pre>
	 * 
	 * @param name
	 *            The name of the album.
	 * @param filename
	 *            The relative path the the file containing the photo album
	 *            data.
	 */
	public PhotoAlbum(String name, String filename) {
		this.name = name;
		this.photos = new ArrayList<Photo>();
		loadFromFile(filename);
	}

	/**
	 * Returns the number of photos in this album.
	 * 
	 * @return the number of photos in this album.
	 */
	public int getNumPhotos() {
		return photos.size();
	}

	/**
	 * Returns a copy of the photos list as an array of Photos.
	 * 
	 * @return an array of Photos in the album.
	 */
	public Photo[] getPhotoArray() {
		Photo[] copy = new Photo[photos.size()];
		for (int i = 0; i < photos.size(); i++) {
			copy[i] = photos.get(i);
		}

		// TODO: Use a for-loop to copy the contents from the photos ArrayList
		// to
		// a new Photo[] and return the copied array.

		return copy;
	}

	/**
	 * Loads photos from the data in the given file into this photo album.
	 * 
	 * @param the
	 *            path of the file to read from.
	 */
	private void loadFromFile(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			try {
				Scanner scan = new Scanner(file);
				while (scan.hasNextLine()) {
					String photoName = scan.nextLine().trim();
					File photoFile = new File(scan.nextLine().trim());
					Photo photo = new Photo(photoName, photoFile);
					photos.add(photo);
				}
				scan.close();
			} catch (FileNotFoundException e) {
				System.err.println("Could not read album file: " + e.getMessage());
			}
		} else {
			System.err.println("Album not found:: " + file);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("------ " + name + " ------\n");
		for (Photo photo : photos) {
			builder.append(photo + "\n");
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		PhotoAlbum album = new PhotoAlbum("Test Album", "photos/album.dat");

		Photo[] copy = album.getPhotoArray();
		for (Photo p : copy) {
			System.out.println(p);
		}
		Photo [][] two_dimension = album.getPhotoSquare();
		
		for (int row = 0; row < two_dimension.length; row++) {
			for (int col = 0; col < two_dimension[row].length; col++) {
				System.out.println(two_dimension[row][col]);
			}
		}
	}

	public Photo[][] getPhotoSquare() {
		
		int numPhotos = this.getNumPhotos();
		int dimension_of_square = (int) Math.ceil(Math.sqrt(numPhotos));
		Photo[][] grid = new Photo[dimension_of_square][dimension_of_square];
		
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = photos.get((row * dimension_of_square + col) % (numPhotos));
			}

		}
		return grid;
	}

}
