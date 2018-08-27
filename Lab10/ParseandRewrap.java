import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseandRewrap {
	public static final int ERROR_CODE = 1;

	/**
	 * @param args
	 *            unused
	 */
	public static void main(String[] args) {

		Scanner kbd = new Scanner(System.in);

		System.out.println("Enter a filename: ");
		String filename = kbd.nextLine().trim();
		System.out.println("Enter the maximum no. of characters in a sentence");
		int max_word = kbd.nextInt();
		kbd.close();

		File file = new File(filename);
		String output = "";
		try {
			Scanner fileScan = new Scanner(file);

			System.out.println("\nContents of \"" + filename + "\":\n");

			while (fileScan.hasNextLine()) {

				String line = fileScan.nextLine();

				Scanner lineScan = new Scanner(line);

				while (lineScan.hasNext()) {
					String word = lineScan.next();

					if (output.length() + word.length() + 1 <= max_word) {
						output += word + " ";

					} else if (output.length() + word.length() == max_word) {
						output += word;

					} else {
						System.out.println(output);
						output = "";
						output += word + " ";
					}

				}
			}
			System.out.println(output);
			System.out.println("Longest Line : " + max_word);
			System.out.println("Shortest Line :" + output.length());
			// We are done reading the file, so close the scanner.
			fileScan.close();
		} catch (FileNotFoundException e) {

			System.out.println("File \"" + filename + "\" could not be opened.");
			System.out.println(e.getMessage());
			System.exit(ERROR_CODE);
		}
	}
}
