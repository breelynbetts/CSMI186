//
// File Name: CountTheDays2.java
// Purpose : Create a program that allows for a user to enter in any two dates
// and the program will return the days between them, accounting for leap years
// @author : Breelyn Betts
// Date : 2018 - 01 - 24

public class CountTheDays2 {
  public static void main ( String args[] ) {

    long[] params = new long[6];

    for (int i = 0; i < 6; i++) {
      params[i] = Long.parseLong(args[i]);
    }
  System.out.println( "The number of days between the two given dates is: " + CalendarStuff.daysBetween(params[0], params[1], params[2], params[3], params[4], params[5]));

  }
}
