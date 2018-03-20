/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File Name     :
 *  Purpose       :
 *  @author       : Breelyn Betts
 *  Date written  :
 *
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Timer {

  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
  private static double totalSeconds = 0;
  private static double timeSlice = 0;

  public Timer() {

  }
  public Timer(double t) {
    timeSlice = t;
  }

  public double tick() {
    totalSeconds += timeSlice;
    return totalSeconds;
  }

  /**
   * Method to validate the optional time slice arg from the command line
   * @param argValue String from the main programs args[7] input
   * @return double-precision value of the argument or -1.0 if invalid
   * @throws NumberFormatException
   */
  public double validateTimeSliceArg(String argValue) throws NumberFormatException {
    double timeSlice = Double.parseDouble(argValue);
      if ( timeSlice < 0 || timeSlice > 1800 ) {
        throw new NumberFormatException("You entered invalid arguments");
      }
    return timeSlice;
  }



  public double getTotalSeconds() {
    return totalSeconds;
  }

  public String toString() {
    return "" + totalSeconds;
  }

}
