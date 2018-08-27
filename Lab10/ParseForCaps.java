
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Prompt for a file to echo. Read the file and print its contents out to the console.
 * 
 * @author mvail
 */
public class ParseForCaps {

	public static final int ERROR_CODE = 1;

	/**
	 * @param args unused
	 */
	public static void main(String[] args) {
		
		Scanner kbd = new Scanner(System.in);
		
		System.out.print("Enter a filename: ");
		String filename = kbd.nextLine().trim();
		kbd.close(); 
		
		File file = new File(filename);
		
		
		try {
			Scanner fileScan = new Scanner(file);
			
			
			System.out.println("\nContents of \"" + filename + "\":\n");
			
			

			while (fileScan.hasNextLine()) {
				
				String line = fileScan.nextLine();
				Scanner word= new Scanner(line);
				while(word.hasNext()){
					String wordlist = word.next();
					if(Character.isUpperCase(wordlist.charAt(0))){
						System.out.print(wordlist.charAt(0));
					}
				}
				
				
			}
			
			
			fileScan.close();
		} catch (FileNotFoundException e) {

			

			System.out.println("File \"" + filename + "\" could not be opened.");
			System.out.println(e.getMessage());
			System.exit(ERROR_CODE);
		} 
	}
}