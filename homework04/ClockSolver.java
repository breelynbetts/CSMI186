/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @author       :  Breelyn Betts
 *  Date written  :  2018-02-22
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ClockSolver {

   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final static double EPSILON_VALUE       = 0.1;      // small value for double-precision comparisons
   private static final double MAXIMUM_NUMBER_OF_SECONDS = 43200;

   private static double targetAngle = 0;
   private static double timeSlice = 0;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      Clock clock = new Clock();
      if ( 1 == args.length ) {
        clock.validateAngleArg(args[0]);
      } else if ( 2 == args.length ) {
        clock.validateAngleArg(args[0]);
        clock.validateTimeSliceArg(args[1]);
      }

   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      cse.handleInitialArguments( args );
      cse.targetAngle = Double.parseDouble(args[0]);
      if ( args.length == 2 ) {
        cse.timeSlice = Double.parseDouble(args[1]);
      } else {
        cse.timeSlice = 60.0;
      }
      Clock clock     = new Clock(cse.timeSlice);
      while( clock.getTotalSeconds() <= MAXIMUM_NUMBER_OF_SECONDS ) {
        clock.tick();
        if ( Math.abs((clock.getHandAngle() - targetAngle )) <= EPSILON_VALUE )  {
          System.out.println( clock.toString());
        }
        System.exit( 0 );
      }

   }
}
