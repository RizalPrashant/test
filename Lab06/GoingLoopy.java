import java.util.ArrayList;

/**
 * CS 121 Lab: Conditionals and Loops 2
 * @author CS121 Instructors
 * @author YOUR NAME HERE
 */
public class GoingLoopy
{
	public static void main(String[] args)
	{
		/*
		 * TODO: Print multiples of 7 between 0 and 100 using a while loop
		 *
		 * 1. Use a while loop to evaluate every integer from 0 to 100
		 *    to see which are evenly divisible by 7.
		 * 2. Print each multiple of 7 and keep track of the number of
		 *    multiples of 7 as you go. (Use the numMultiples variable initialized
		 *    below to keep track of the number of multiples.)
		 */
		System.out.println("Multiples of 7 between 0 and 100 (using while loop):");

		int numMultiples = 0; // Keeps track of the number of integers evenly divisible by 7.

		/*------------------ Your code goes here ------------------*/
         int i=0;
         while (i<=100)
         {
        	 if (i%7==0){
        		 System.out.print( i+ " " );
        		 numMultiples++;
        	 }
        	 i=i+1;
         }


		/*---------------------------------------------------------*/

		// Prints the total number of multiples of 7.
		System.out.println(" \ntotal: " + numMultiples);
		System.out.println();

		/*
		 * TODO: Print multiples of 7 between 0 and 100 using a for loop
		 *
		 * 1. Use a for loop to accomplish exactly the same task as in the previous
		 *    while loop.
		 */
		System.out.println("Multiples of 7 between 0 and 100 (using for loop):");

		numMultiples = 0; // Keeps track of the number of integers evenly divisible by 7.

		/*------------------ Your code goes here ------------------*/

        for(int j =0;j<=100;j++){
        	if (j%7==0){
        		System.out.print( j+ " " );
        		numMultiples++;
        	}
        }

		/*---------------------------------------------------------*/

		// Prints the total number of multiples of 7.
		System.out.println(" \ntotal: " + numMultiples);
		System.out.println();

		/*
		 * TODO: Print multiples of 7 between 0 and 100 using a do-while loop
		 *
		 * 1. Use a do-while loop to accomplish exactly the same task as in the previous
		 *    while and for loops.
		 */
		System.out.println("Multiples of 7 between 0 and 100 (using do loop):");

		numMultiples = 0; // Keeps track of the number of integers evenly divisible by 7.

		/*------------------ Your code goes here ------------------*/

        int k=0;
        do{
     
        if(k%7==0){
        	System.out.print( k+ " ");
        	
        	numMultiples++;
        	
        	
        }
     
        k=k+1;}        
        while (k<=100);
        	
        

		/*---------------------------------------------------------*/

		// Prints the total number of multiples of 7.
		System.out.println(" \ntotal: " + numMultiples);
		System.out.println();

		/*
		 * TODO: Print a 2-dimensional table of values using a nested for loop
		 *
		 * 1. The outer loop will iterate from 1 to MAX_ROWS rows.
		 * 2. The inner loop will iterate from 1 to MAX_COLS columns.
		 * 3. Print each value in the table as the product of the row and column numbers.
		 *    (rowNumber x columnNumber).
		 */

		final int MAX_ROWS = 5;
		final int MAX_COLS = 10;
		

		System.out.println("Multiplication table:");
		/*------------------ Your code goes here ------------------*/
         for (i=1;i<=MAX_ROWS;i++){
        	
        	 for (int l=1; l<=MAX_COLS;l++){
        		 System.out.print(l*i+ "  ");
        	 }
        	 System.out.println("");
         }


		/*---------------------------------------------------------*/

		System.out.println();
	}
}
