import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class HashTest<T>
{
	
	public static void main(String[] args) throws IOException
	{
		Double loadFactor = Double.parseDouble(args[1]);
		
		HashTable hashTable = new HashTable();
		HashTable hashTable2 = new HashTable();
		int n = (int) (loadFactor * hashTable.getSize());

		if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 3)
		{
			throw new IOException("Enter 1 or 2 or 3");
		}

		if (Integer.parseInt(args[0]) == 1) 
		{
			Random random = new Random(1234);
			
			for (int i = 0; i < n; i++) 
			{
				HashObject intObject = new HashObject(random.nextInt());
				hashTable.insertLinear(intObject);
				hashTable2.insertDouble(intObject);
			}

		} 
		
		else if (Integer.parseInt(args[0]) == 2)
		{
			
			for (int i = 0; i < n; i++) 
			{
				long time_in_ms = System.currentTimeMillis();
				HashObject longObject = new HashObject(time_in_ms);
				if(hashTable.insertLinear(longObject) == false) {
					i = i - 1;
				}
				hashTable2.insertDouble(longObject);
			}
		} 
		
		else if (Integer.parseInt(args[0]) == 3) 
		{
			File data = new File("word-list");

			try
			{
				Scanner scanner = new Scanner(data);
				int i = 0;
				while (i < n)
				{
					String word = scanner.next();
					HashObject wordObject = new HashObject(word);
					if(hashTable.insertLinear(wordObject) == false) {
						i = i - 1;
					}
					hashTable2.insertDouble(wordObject);
					i++;
				}

			} 
			
			catch (FileNotFoundException e)
			{
				System.out.println("No file of such name found");
			}
		}
	
		if (Integer.parseInt(args[2]) == 0) 
		{
			System.out.println("A good table size is found:" + " " +  hashTable.getSize());
		
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
			System.out.println("");
			System.out.println("Using Linear Hashing....");
			System.out.println("Inserted " + n + " " + "elements, of which  " + hashTable.dupL + " duplicates ");
			System.out.println("load factor = " + loadFactor + ", Avg.no. of probes " + String.format("%.3f",(hashTable.numProbesL()/(n))));
		
			System.out.println("Using Double Hashing....");
			System.out.println("Inserted " + n + " " + "elements, of which  " + hashTable2.dupD  + " duplicates ");
			System.out.println("load factor = " + loadFactor + ", Avg.no. of probes " + String.format
					("%.3f",((hashTable2.numProbesD()/(n)))-(hashTable.numProbesL()/(n)))); // for some reason it added the numProbesL as well.
		}
		
		else if(Integer.parseInt(args[2]) == 1) 
		{
			System.out.println("A good table size is found:" + " " +  hashTable.getSize());
			
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
			System.out.println("");
			System.out.println("Using Linear Hashing....");
			System.out.println("Inserted " + n + " " + "elements, of which  " + hashTable.dupL + " duplicates ");
			System.out.println("load factor = " + loadFactor + ", Avg.no. of probes " + String.format("%.3f",(hashTable.numProbesL()/(n+1))));
			
			System.out.println("Using Double Hashing....");
			System.out.println("Inserted " + n + " " + "elements, of which  " + hashTable2.dupD  + " duplicates ");
			System.out.println("load factor = " + loadFactor + ", Avg.no. of probes " + String.format("%.3f",(hashTable2.numProbesD()/(n+1))));
			
			PrintStream output = new PrintStream(new FileOutputStream("linearDump.txt"));
			int hashSize = hashTable.getSize();
			System.setOut(output);
			//HashObject obtained_object = new HashObject(hashTable[i]);
			for(int i = 0; i < hashSize; i++) {
				if(hashTable.getHashObject(i) != null) {
					output.print(hashTable.getHashObject(i).toString());
				}
			}
			
			PrintStream output2 = new PrintStream(new FileOutputStream("doubleDump.txt"));
			int hashSize2 = hashTable2.getSize();
			System.setOut(output2);
			for(int i = 0; i < hashSize2; i++) {
				if(hashTable.getHashObject(i) != null) {
					output2.print(hashTable.getHashObject(i).toString());
				}
			}
		}
	}
}