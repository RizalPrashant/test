import java.util.Scanner;

public class Add {
public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter a number : ");
	int firstNumber = scan.nextInt();
	System.out.println("Enter another number : ");
	int secondNumber = scan.nextInt();
	System.out.println("Sum is " + (firstNumber+secondNumber));
	scan.close();
}

}
