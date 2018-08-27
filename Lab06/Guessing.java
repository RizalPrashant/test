import java.util.*;

/**
 * Demonstrates the use of a block statement in an if-else.
 *
 * @author Java Foundations
 */
public class Guessing
{
   /**
    * Plays a simple guessing game with the user.
    * @param args
    */
    public static void main(String[]args)
    {
        final int MAX = 10;
        int answer, guess;

        Scanner scan = new Scanner(System.in);
        Random generator = new Random();

        answer = generator.nextInt(MAX) + 1;

		System.out.print("I'm thinking of a number between 1 and "
                            +MAX + ". Guess what it is: ");
       do{ 
		guess = scan.nextInt();
         if (guess<=10 && guess>=1){
		if (guess == answer)
        {
            System.out.println("You got it! Good guessing!");
        }
        
		 else if(guess<answer){
			System.out.println("Higher : ");
			
			
		}
		else if(guess>answer){
			System.out.println("Lower :");
		}}
		else   {
			System.out.println("Please enter a valid number");
		}
		
		
       
        
       }while (guess!= answer);
       
        	
        	
            
            
       
        
       
       scan.close();
       }}