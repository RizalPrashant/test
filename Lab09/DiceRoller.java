import java.util.Scanner;

public class DiceRoller {
	public static void main(String[] args) {
		PairOfDice pair;
		int sides;
		long seed;
		int yourRoll;
		int compRoll;
		int lose = 0;
		int win = 0;
		int tie = 0;
		String again;
		
		System.out.println("Enter the no. of sides of the dice : ");
		Scanner scan = new Scanner(System.in);
		sides = scan.nextInt();
		pair = new PairOfDice(8, 232234, 565787);
		System.out.println("Enter the seed");
		seed = scan.nextLong();
		do {
			
			
				yourRoll = pair.roll();
				System.out.println("Your Roll is :" + pair.toString());
				compRoll = pair.roll();
				System.out.println("Computer's Roll is : " + pair.toString());

				if (compRoll > yourRoll) {
					System.out.println("You lose");
					lose++;
					
				} else if (yourRoll > compRoll) {
					System.out.println("You win");
					win++;
					
				} else {
					System.out.println("Its a tie");
					tie++;
					
				}
				System.out.println("Your wins: " +win);
				System.out.println("Your losses: "+lose);
				System.out.println("Tied games :"+ tie);
			System.out.println("Continue Y/N");
			again = scan.next();
		} while (again.equalsIgnoreCase("Y"));
		System.out.println("Thanks for playing!!");

	}
}
