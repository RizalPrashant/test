import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Test<T> {
	static Cache<String> cache1;
	static Cache<String> cache2;
	static Scanner scan;
	static String fileName;
	static int count;

	public static void usage() {
		System.out.println("Incorrectly passed arguments");
		System.out.println("java Test [1] [cachecapacity] [Filename] ");
		System.out.println("java Test [2] [1stcachecapacity] [2ndcachecapacity] [Filename]");
		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length < 3 || args.length > 4) {
			usage();
		}
		if (args[0].equals("1")) {
			fileName = args[2];
		} else if (args[0].equals("2")) {
			if(Integer.parseInt(args[1]) > Integer.parseInt(args[2])) {
				usage();
			}
			fileName = args[3];
			cache2 = new Cache<String>(Integer.parseInt(args[2]));
		}
		cache1 = new Cache<String>(Integer.parseInt(args[1]));
		
		File file = new File(fileName);
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file name");
			e.printStackTrace();
		}
		
		StringTokenizer token;
		String currentToken = null;
		String temp = null;
		while(scan.hasNextLine()) {
			temp = scan.nextLine();
			token = new StringTokenizer(temp," ");
			while(token.hasMoreTokens()) {
				count++;
				currentToken = token.nextToken();
			if (args[0].equals("1")) {
				//System.out.println(currentToken);
				//cache1.add(currentToken);
				if (cache1.get(currentToken) == null ) {
					cache1.add(currentToken);
				}
			}
			
			else if(args[0].equals("2")) {
				String found;
				if((found = cache1.get(currentToken)) == null) {
					cache1.add(currentToken);
				}
				if (currentToken.equals(found)) {
					cache2.write(currentToken);
				}
				else if(cache2.get(currentToken) == null) {
					cache2.add(currentToken);
				}
				//System.out.println(currentToken);
				//cache1.add(currentToken);
				//System.out.println(cache1.getNumAccess());
				//cache2.add(currentToken);
				//System.out.println(cache2.getNumAccess());
			}
			}
			}
		DecimalFormat intFormat = new DecimalFormat("####");
		DecimalFormat doubFormat = new DecimalFormat("#0.00");
		
		
		
		System.out.println("L1 cache with" + cache1.toString() + "entries created");
		if (args.length == 4) {
			System.out.println("L2 cache with" + cache2.toString() + "entries created");
		}
		System.out.println("...");
		System.out.println("Number of L1 cache hits: " + intFormat.format(cache1.getNumHits()));
		System.out.println("L1 cache hit rate: " + doubFormat.format((cache1.getNumHits()/count)*100)+ "%" + "\n");
		if (args.length == 4) {
			System.out.println("Number of L2 cache hits: " + intFormat.format(cache2.getNumHits()));
			System.out.println("L2 cache hit rate: "+ doubFormat.format(((cache2.getNumHits()/(count - cache1.getNumHits()))*100)) + "%" + "\n");
			System.out.println("Total number of accesses: "+ intFormat.format((count)));
			System.out.println("Total number of cache hits: "+ intFormat.format((cache2.getNumHits() + cache1.getNumHits())));
			System.out.println("Overall hit rate: " + doubFormat.format(((cache1.getNumHits()+cache2.getNumHits())/count)*100) + "%");
			}
		if(args.length == 3) {
			//System.out.println("Total number of accesses: "+ intFormat.format((cache1.getNumHits()+cache1.getNumMiss())));
			System.out.println("Total number of accesses: "+ intFormat.format(count));
			System.out.println("Total number of cache hits: "+ intFormat.format(cache1.getNumHits()));
			System.out.println("Overall hit rate: " + doubFormat.format((cache1.getNumHits()/count)*100) + "%");
			
		}
		
		

		// Cache L1 = new Cache();
		// L1.add("Hell");
		// L1.add("Yeah");
		// L1.add("Mate");
		// L1.add("Hell");
		// //L1.add("Ram");
		// L1.add("Ram");
		// L1.add("Shyam");
		// L1.write("Shyam");
		// //L1.get("Ram");
		// //L1.isEmpty();
		// L1.getHitRate();
		//
		//
		// //L1.add("Mate");
		// //L1.add("Whatevs");
		//
	}
}
