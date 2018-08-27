import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;

/**
 * A simple application to test use of String, Math, DecimalFormat and NumberFormat classes.
 *
 * @author CS121 instructors (starter code)
 * @author Your name
 */
public class MyNameIs
{
	/**
	 * @param args (unused)
	 */
	public static void main(String[] args)
	{
		DecimalFormat all_to_two_dp = new DecimalFormat("#.00");
		Scanner keyboard = new Scanner(System.in);

		System.out.print("First name: ");
		String firstName = keyboard.nextLine();

		System.out.print("Last name: ");
		String lastName = keyboard.nextLine();
		
		System.out.print("Enter a number: ");
		double n1 = keyboard.nextDouble();

		System.out.print("Enter another number (between 0 and 1): ");
		double n2 = keyboard.nextDouble();
		
		all_to_two_dp.format(n1);
		all_to_two_dp.format(n2);

		System.out.println("\nHi, my name is " + firstName + " " + lastName + ".");
		
		System.out.println("You'll find me under"+"\""+firstName+","+lastName+".\"");
		char first_initial= firstName.charAt(0);
		char second_initial= lastName.charAt(0);
	    System.out.println("My name badge:"+ "\""+ first_initial + second_initial +".\"");
	   
	    NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(1);
        double calculated_percentage = n1*n2; 
        all_to_two_dp.format(calculated_percentage);
		System.out.println(defaultFormat.format(n2)+" of "+ all_to_two_dp.format(n1)+" is "+all_to_two_dp.format(+calculated_percentage));
		System.out.println(all_to_two_dp.format(n1)+" Raised to the power of "+all_to_two_dp.format(+n2) + " is " +all_to_two_dp.format(Math.pow(n1, n2)));
		
		
        
		
		
		

		// TODO: Finish the program according to the lab specifications.

	}
}
