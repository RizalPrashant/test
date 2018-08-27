import java.util.Scanner;
public class ConvertToHours
{
    public static void main(String[] args)
    {
        Scanner get_seconds = new Scanner(System.in);
        System.out.print("Enter seconds:");
        int seconds = get_seconds.nextInt();
        
        int hours= seconds/3600;
        int remaining_seconds= seconds % 3600;
        int minutes= remaining_seconds/60;
        int last_remaining_seconds = remaining_seconds % 60;
        int after_conversion_seconds = last_remaining_seconds;
        
        System.out.println("Hours = " + hours);
        System.out.println("Minutes = " + minutes);
        System.out.println("Seconds = " + after_conversion_seconds);
        
        Double fractional_hours = (double) seconds/3600;
        System.out.println("Fractional hours = " + fractional_hours);
        
    }
}
