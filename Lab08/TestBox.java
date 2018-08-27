
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class TestBox {
	public static void main(String[] args) {
		Box smallbox = new Box(4,5,2);
		System.out.println(smallbox.toString());
		System.out.println("smallbox's width = "+ smallbox.getWidth());
		System.out.println("smallbox's height = "+ smallbox.getHeight());
		System.out.println("smallbox's depth = "+ smallbox.getDepth());
		System.out.println("smallbox's volume = "+ smallbox.volume());
		System.out.println("smallbox's surface area = "+ smallbox.surfaceArea());
		
		System.out.println("smallbox reports its full status as : "+ smallbox.getFull());
		System.out.println(" ");
		System.out.println(" ");
		smallbox.setWidth(2);
		smallbox.setHeight(3);
		smallbox.setDepth(1);
		smallbox.setFull(true);
		System.out.println(smallbox.toString());
		System.out.println("smallbox's width = "+ smallbox.getWidth());
		System.out.println("smallbox's height = "+ smallbox.getHeight());
		System.out.println("smallbox's depth = "+ smallbox.getDepth());
		System.out.println("smallbox's volume = "+ smallbox.volume());
		System.out.println("smallbox's surface area = "+ smallbox.surfaceArea());
		System.out.println("smallbox reports its full status as : "+ smallbox.getFull());
	
		ArrayList<Box> Boxes = new ArrayList<Box>();
		Random rand= new Random();
		for (int i=0 ; i<5; i++){
			Box b= new Box(30*rand.nextDouble()+1,30*rand.nextDouble()+1,30*rand.nextDouble()+1);
			b.setFull(rand.nextBoolean());
			Boxes.add(b);
			System.out.println("Box"+i+": "+ b);
		}
		
		double max= Double.MIN_VALUE;
		Box checkbox = new Box(0,0,0);
		
		for (Box member: Boxes){
			
			if (member.volume()>max){
				max=Math.max(member.volume(),max);
				checkbox = member;
			}
		}
		
		
		String pattern="##.00";
		DecimalFormat decimal = new DecimalFormat(pattern);
		System.out.println("Largest box =  " + checkbox.toString()+ " with volume "+decimal.format(checkbox.volume())+" with surface area "+decimal.format(checkbox.surfaceArea()));
	}
	
}
		
