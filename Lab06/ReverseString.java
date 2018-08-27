import java.util.Scanner;
public class ReverseString {
public static void main(String[] args) {
	System.out.println("Enter a string :");
    Scanner input_data = new Scanner(System.in);
   String input_data_obtained= input_data.nextLine();
   
   System.out.println("Your string is "+input_data_obtained);
   
   System.out.println("your Reverse String is : ");
  
   for (int i = input_data_obtained.length()-1; i>-1;i--){
	  System.out.print( input_data_obtained.charAt(i));
  }
   
   input_data.close();
}
{
	
}
}
