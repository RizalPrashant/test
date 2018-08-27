import java.util.Random;

/**
 * Use an ArrayList to store a collection of objects and use a for-each loop to process the objects.
 * 
 * @author amit
 *
 */
public class ArrayListExercise {

    public static void main(String[] args)
    {
        Random rand = new Random();
        final int RADIUS_MAX = 100;
        //TODO: declare a constant for the number of spheres NUM_SPHERES and 
        //      initialize it appropriately

        //TODO: Declare the ArrayList to hold the Sphere objects
        
        //TODO: Create the spheres using a normal for loop and add them to an ArrayList<Sphere>
        Sphere s1 = new Sphere(rand.nextInt(RADIUS_MAX));
        Sphere s2 = new Sphere(rand.nextInt(RADIUS_MAX));
        Sphere s3 = new Sphere(rand.nextInt(RADIUS_MAX));
        Sphere s4 = new Sphere(rand.nextInt(RADIUS_MAX));
        
        //TODO: Convert to a for-each loop to print out the spheres
        System.out.println("Sphere 1: \t" + s1 + "\n");
        System.out.println("Sphere 2: \t" + s2 + "\n");
        System.out.println("Sphere 3: \t" + s3 + "\n");
        System.out.println("Sphere 4: \t" + s4 + "\n");
        
        //TODO: Convert to a for-each loop to find the volume of the smallest sphere
        double min1 = Math.min(s1.getVolume(), s2.getVolume());
        double min2 = Math.min(s3.getVolume(), s4.getVolume());
        double min = Math.min(min1, min2);

        System.out.printf("Volume of the smallest sphere: %.2f\n", min);
    }
}
