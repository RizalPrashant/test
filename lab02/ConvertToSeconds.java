import java.util.Scanner;
public class ConvertToSeconds
{
    public static void main(String[] args)
    {
       Scanner get_hours_minutes_seconds = new Scanner(System.in);
       
       System.out.print("Enter hours:");
       int hours = get_hours_minutes_seconds.nextInt();
       System.out.print("Enter minutes:");
       int minutes = get_hours_minutes_seconds.nextInt();
       System.out.print("Enter Seconds:");
       int seconds = get_hours_minutes_seconds.nextInt();
      
       int total_seconds = hours*3600+minutes*60+seconds;
        System.out.print("Total seconds is:"+total_seconds);
    }
}