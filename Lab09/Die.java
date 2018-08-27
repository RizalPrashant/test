import java.util.Random;

/**
 * Die.java
 *
 * Represents one die (singular of dice) with faces showing values
 * between 1 and 6.
 *
 * @author Java Foundations
 * @author CS121 Instructors (modified a few things from book)
 */
public class Die
{
   private final int numOfSides;  // maximum face value
   private int faceValue;  // current value showing on the die
   private Random rand;
   private long seed;

   /**
    * Constructor: Sets the initial face value of this die.
    */
   public Die(int numOfSides)
   {
      this.numOfSides= numOfSides;
	   rand = new Random();
      faceValue = 1;
   }
   public Die(int numOfSides, long seed){
	   rand = new Random(seed);
	   faceValue=1;
	   this.numOfSides=numOfSides;
   }

   /**
    * Computes a new face value for this die and returns the result.
    * @return The new face value.
    */
   public int roll()
   {
      //faceValue = (int)(Math.random() * MAX) + 1;
      faceValue = rand.nextInt(numOfSides) + 1;
      return faceValue;
   }

   /**
    * Face value mutator. The face value is not modified if the
    * specified value is not valid.
    *
    * @param value The new face value. Must be between 1 and max face
    * value.
    */
   public void setFaceValue (int value)
   {
      if (value > 0 && value <= numOfSides) {
         faceValue = value;
      }
   }

   /**
    * Face value accessor.
    * @return The current face value.
    */
   public int getFaceValue()
   {
       return faceValue;
   }

   /**
    * Returns a string representation of this die.
    */
   public String toString()
   {
      String result = "Die [faceValue = " + faceValue + "]";
      return result;
   }
}