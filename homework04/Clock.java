/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  Breelyn Betts
 *  Date written  :  2018-02-22
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Clock {

   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

   private static final double MAXIMUM_NUMBER_OF_SECONDS = 43200;
   private static double totalSeconds = 0;
   private static double timeSlice = 0;
   private double targetAngle = 0;
   private double hourHandAngle;
   private double minuteHandAngle;
   private double degrees;
   private double hourHand = 0;
   private double minuteHand = 0;
   private double seconds = 0;

  /**
   *  Constructor goes here
   */
   public Clock() {
   }

   public Clock(double t) {
     timeSlice = t;
   }

  /**
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      totalSeconds += timeSlice;
      return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
      double targetAngle = Double.parseDouble(argValue);
      if ( argValue.length() == 0 || targetAngle < 0 || targetAngle > 360 ) {
        throw new NumberFormatException("Invalid arguments entered");
      }
      return targetAngle;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
     double timeslice = Double.parseDouble(argValue);
       if (timeslice < 0 || timeslice > 1800) {
           throw new NumberFormatException("You entered invalid arguments");
       }
       return timeslice;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      double hourHandAngle = ( HOUR_HAND_DEGREES_PER_SECOND * seconds ) % 360;
      return hourHandAngle;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      double minuteHandAngle = ( MINUTE_HAND_DEGREES_PER_SECOND * seconds ) % 360;
      return minuteHandAngle;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      double angleFinal = Math.abs( getHourHandAngle() - getMinuteHandAngle() ) % 360;
      if ( angleFinal > 180 ) {
        return 360 - angleFinal;
      }
      return angleFinal;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
     hourHand = Math.floor( totalSeconds / 3600);
     minuteHand = Math.floor((totalSeconds % 3600) / 60);
     seconds = totalSeconds - ((hourHand * 3600) + (minuteHand * 60));
     DecimalFormat df = new DecimalFormat("#.00");
     return (int) hourHand + ":" + (int) minuteHand + ":" + df.format(seconds);
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock2 = new Clock();
      System.out.println( "    New clock created: " + clock2.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock2.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

      Clock c = new Clock();
      timeSlice = 10;
      System.out.println(c.getTotalSeconds());
      System.out.println(c.tick());
      System.out.println(c.tick());
      System.out.println(c.tick());
      System.out.println(c.tick());
      System.out.println(c.tick());
      System.out.println(c.tick());
      System.out.println(c.tick());
      System.out.println(c.getTotalSeconds());
      totalSeconds = 3601;
      System.out.println(c.getHourHandAngle());
      System.out.println(c.getMinuteHandAngle());
      System.out.println(c.getHandAngle());

      Clock c2 = new Clock();
      totalSeconds = 0;
      timeSlice = 30;
      System.out.println(c2.getTotalSeconds());
      System.out.println(c2.tick());
      System.out.println(c2.tick());
      System.out.println(c2.tick());
      System.out.println(c2.tick());
      System.out.println(c2.tick());
      System.out.println(c2.tick());
      System.out.println(c2.tick());
      System.out.println(c2.getTotalSeconds());
      totalSeconds = MAXIMUM_NUMBER_OF_SECONDS;
      System.out.println(c2.getHourHandAngle());
      System.out.println(c2.getMinuteHandAngle());
      System.out.println(c2.getHandAngle());

   }
}
