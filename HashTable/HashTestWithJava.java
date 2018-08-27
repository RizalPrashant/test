import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import java.util.Hashtable;


public class HashTestWithJava
{
	
	public static void main(String[] args) throws IOException
	{	
		
		
		Double loadFactor = Double.parseDouble(args[1]);
		int dupCount = 0;
		int num = 0;
		
		int n = (int) (loadFactor * Integer.parseInt(args[2]));
		
		if (Integer.parseInt(args[0]) == 1) 
		{
			Hashtable<Integer,Integer> hashTable = new Hashtable<>(Integer.parseInt(args[2]), Float.parseFloat(args[1]));
			
			Random random = new Random(1234);

			for (int i = 0; i < n; i++) 
			{
				int randNumber = random.nextInt();
				if(hashTable.containsKey(randNumber)){
					dupCount++;
					Integer intCount = hashTable.get(randNumber);
					hashTable.put(randNumber, intCount + 1);
				}else {
					hashTable.put(randNumber, 0);
					num++;
				}

			} 
			if (Integer.parseInt(args[3]) == 0) 
			{
				System.out.println("Table size: " + args[2]);

				if(Integer.parseInt(args[0]) == 1) 
				{
					System.out.println("Data source type: " + "random number generator");
				}

				else if(Integer.parseInt(args[0]) == 2) 
				{
					System.out.println("Data source type: " + "CurrentTimeMillis");
				}

				else if(Integer.parseInt(args[0]) == 3)
				{
					System.out.println("Data source type: " + "word-list");
				}

				System.out.println("\nUsing java Hashtable....");
				System.out.println("Inserted " + (n) + " " + "elements, of which  " + dupCount + " duplicates ");
				System.out.println("load factor = " + loadFactor);

			}

			else if(Integer.parseInt(args[3]) == 1) 
			{
				System.out.println("A good table size is found:" + " " +  args[2]);

				if(Integer.parseInt(args[0]) == 1)
				{
					System.out.println("Data source type: " + "random number generator");
				}

				else if(Integer.parseInt(args[0]) == 2)
				{
					System.out.println("Data source type: " + "CurrentTimeMillis");
				}

				else if(Integer.parseInt(args[0]) == 3)
				{
					System.out.println("Data source type: " + "word-list");
				}

				System.out.println("\nUsing java Hashtable....");
				System.out.println("Inserted " + (n) + " " + "elements, of which  " + (dupCount) + " duplicates ");
				System.out.println("load factor = " + loadFactor);

				PrintStream output = new PrintStream(new FileOutputStream("hashtable-dump.txt"));
				System.setOut(output);
				output.println(hashTable.toString() + "/n");
				output.close();

			}

		}
		
		else if (Integer.parseInt(args[0]) == 2)
		{
			Hashtable<Long,Integer> hashTable = new Hashtable<>(Integer.parseInt(args[2]), Float.parseFloat(args[1]));
			
			for (int i = 0; i < n; i++)  
			{
				long timeMillis = System.currentTimeMillis();
				if(hashTable.containsKey(timeMillis)) {
					dupCount++;
					Integer intCount = hashTable.get(timeMillis);
					hashTable.put(timeMillis, intCount + 1);
					i--;
				}else {
					hashTable.put(timeMillis, 0);
					num++;
				}
			}
			
			
			if (Integer.parseInt(args[3]) == 0) 
			{
				System.out.println("Table size: " + args[2]);

				if(Integer.parseInt(args[0]) == 1) 
				{
					System.out.println("Data source type: " + "random number generator");
				}

				else if(Integer.parseInt(args[0]) == 2) 
				{
					System.out.println("Data source type: " + "CurrentTimeMillis");
				}

				else if(Integer.parseInt(args[0]) == 3)
				{
					System.out.println("Data source type: " + "word-list");
				}

				System.out.println("\nUsing java Hashtable....");
				System.out.println("Inserted " + (n) + " " + "elements, of which  " + dupCount + " duplicates ");
				System.out.println("load factor = " + loadFactor);

			}

			else if(Integer.parseInt(args[3]) == 1) 
			{
				System.out.println("A good table size is found:" + " " +  args[2]);

				if(Integer.parseInt(args[0]) == 1)
				{
					System.out.println("Data source type: " + "random number generator");
				}

				else if(Integer.parseInt(args[0]) == 2)
				{
					System.out.println("Data source type: " + "CurrentTimeMillis");
				}

				else if(Integer.parseInt(args[0]) == 3)
				{
					System.out.println("Data source type: " + "word-list");
				}

				System.out.println("\nUsing java Hashtable....");
				System.out.println("Inserted " + (n) + " " + "elements, of which  " + (dupCount) + " duplicates ");
				System.out.println("load factor = " + loadFactor);

				PrintStream output = new PrintStream(new FileOutputStream("hashtable-dump.txt"));
				System.setOut(output);
				output.println(hashTable.toString() + "/n");
				output.close();
			}
		}
		
		else if (Integer.parseInt(args[0]) == 3) 
		{
			Hashtable<String,Integer> hashTable = new Hashtable<String, Integer>(Integer.parseInt(args[2]), Float.parseFloat(args[1]));
	
			try
			{
					File file = new File("word-list");
				Scanner scanFile = new Scanner(file);
				for (int i = 0; i < n; i++)  {

					String word = scanFile.nextLine();
					if(hashTable.get(word) != null) {
						Integer intCount = hashTable.get(word);
						intCount++;
						dupCount++;
						hashTable.put(word, intCount);
						i--;
					}else {
						hashTable.put(word, 0);
						num++;
					}
				}
				if (Integer.parseInt(args[3]) == 0) 
				{
					System.out.println("Table size: " + args[2]);

					if(Integer.parseInt(args[0]) == 1) 
					{
						System.out.println("Data source type: " + "random number generator");
					}

					else if(Integer.parseInt(args[0]) == 2) 
					{
						System.out.println("Data source type: " + "CurrentTimeMillis");
					}

					else if(Integer.parseInt(args[0]) == 3)
					{
						System.out.println("Data source type: " + "word-list");
					}

					System.out.println("\nUsing java Hashtable....");
					System.out.println("Inserted " + (n) + " " + "elements, of which  " + dupCount + " duplicates ");
					System.out.println("load factor = " + loadFactor);

				}

				else if(Integer.parseInt(args[3]) == 1) 
				{
					System.out.println("A good table size is found:" + " " +  args[2]);

					if(Integer.parseInt(args[0]) == 1)
					{
						System.out.println("Data source type: " + "random number generator");
					}

					else if(Integer.parseInt(args[0]) == 2)
					{
						System.out.println("Data source type: " + "CurrentTimeMillis");
					}

					else if(Integer.parseInt(args[0]) == 3)
					{
						System.out.println("Data source type: " + "word-list");
					}

					System.out.println("\nUsing java Hashtable....");
					System.out.println("Inserted " + (n) + " " + "elements, of which  " + (dupCount) + " duplicates ");
					System.out.println("load factor = " + loadFactor);

					PrintStream output = new PrintStream(new FileOutputStream("hashtable-dump.txt"));
					System.setOut(output);
					output.println(hashTable.toString() + "/n");
					output.close();
				}
			} 
			catch (FileNotFoundException e)
			{
				System.out.println("File could not be located");
			}
		}

	}
}
