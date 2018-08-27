/**
  * This is a javadoc comment. Replace with a description of what this class does.
  *
  * Using the @author tag at the end of the comment will let others know that you
  * wrote the program.
  *
  * @author Prashant Rizal
  */
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Random;

public class FindParking{
public static void main(String[] args) {

System.out.println("Enter Random Seed :");
Scanner in = new Scanner(System.in);
long seed= in.nextLong();

System.out.println("Enter time :");
int time_spent = in.nextInt();
Random rand = new Random(seed);

int carX = rand.nextInt(100);
int carY= rand.nextInt(100);

System.out.println("Position of the car is"+"  "+carX+" at x"+ " and "+ carY + " at Y");

NumberFormat fmt1 = NumberFormat.getCurrencyInstance();
ParkingSpot ParkingSpot1 = new ParkingSpot("1st Street",rand.nextInt(100),rand.nextInt(100));
System.out.println(ParkingSpot1);
int distance1 = Math.abs(carX-ParkingSpot1.getLocationX())+Math.abs(carY-ParkingSpot1.getLocationY());
double cost1 = Math.ceil((double)time_spent/10)*ParkingSpot1.getCharge();
System.out.println("Distance = "+distance1+ " and Cost = "+fmt1.format(cost1));

ParkingSpot ParkingSpot2 = new ParkingSpot("2nd Street",rand.nextInt(100),rand.nextInt(100));
System.out.println(ParkingSpot2);
int distance2 = Math.abs(carX-ParkingSpot2.getLocationX())+Math.abs(carY-ParkingSpot2.getLocationY());
double cost2 = Math.ceil((double)time_spent/10)*ParkingSpot2.getCharge();
System.out.println("Distance = "+distance2+ " and Cost = "+fmt1.format(cost2));

ParkingSpot ParkingSpot3 = new ParkingSpot("3rd Street",rand.nextInt(100),rand.nextInt(100));
ParkingSpot3.setCharge(0.30);
System.out.println(ParkingSpot3);
int distance3 = Math.abs(carX-ParkingSpot3.getLocationX())+Math.abs(carY-ParkingSpot3.getLocationY());
double cost3 = Math.ceil((double)time_spent/10)*ParkingSpot3.getCharge();
System.out.println("Distance = "+distance3+ " and Cost = "+fmt1.format(cost3));

ParkingSpot ParkingSpot4 = new ParkingSpot("4th Street",rand.nextInt(100),rand.nextInt(100));
ParkingSpot4.setCharge(0.30);
System.out.println(ParkingSpot4);
int distance4 = Math.abs(carX-ParkingSpot4.getLocationX())+Math.abs(carY-ParkingSpot4.getLocationY());
double cost4 = Math.ceil((double)time_spent/10)*ParkingSpot4.getCharge();
System.out.println("Distance = "+distance4+ " and Cost = "+fmt1.format(cost4));

if (distance1<distance2 & distance1<distance3 & distance1< distance4){
	System.out.println("Distance to Closest Spot : " + distance1);
	System.out.println("Closest Spot : " + ParkingSpot1);}
	else if (distance2<distance1 & distance2 <distance3 & distance2<distance4){
		System.out.println("Distance to Closest Spot : "+ distance2 );
		System.out.println("Closest Spot : " + ParkingSpot2);}
	else if (distance3<distance1 & distance3 <distance2 & distance3<distance4){
		System.out.println("Distance to Closest Spot : "+ distance3 );
		System.out.println("Closest Spot : " +ParkingSpot3);}
	else {
		System.out.println("Distance to Closest Spot : "+ distance4);
		System.out.println("Closest Spot : " + ParkingSpot4);
	
	}


}
}
