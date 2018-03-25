/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File Name     : SoccerSim.java
 *  Purpose       : Main program for the SoccerSim class
 *  @author       : Breelyn Betts
 *  Date written  : 2018 - 03 - 20
 *  Description   : This class provides numerous methods useful for simulating
                    a program that tests whether two balls collide. (Homework 5)
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

public class SoccerSim {

  Ball[] balls = null;

  public void handleInitialArguments( String args []) {

    private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

    System.out.println( "\n   Hello world, from the SoccerSim program!!\n\n" ) ;
    if( 0 == args.length ) {
       System.out.println( "   Sorry you must enter at least one argument\n" +
                           "   Usage: java SoccerSim <x position> <y position> <x velocity> <y velocity> [timeSlice]\n" +
                           "   Please try again..........." );
       System.exit( 1 );
    }

    public double validateLocationArgs( String argValue) throws NumberFormatException {
      double
      try  {
        args.length % 4 == 1;
      }
      catch (NumberFormatException nfe ){
        System.out.println(nfe.toString());
      }
      return 0;
    }



    public double validateVelocityArgs(argValue) {
      double ballQuantity = new Double;
      if ( args.length % 4 == 1 ) {
        ballQuantity = ( args.length - 1 ) / 4;
      }
      if ( args.length % 4 == 0 ) {
        ballQuantity = args.length / 4;
      }
    }



    balls = new Ball[ ];
    int j = 0;
    if ( (args.length % 4) == 1 ) {
      for ( int i = 0; i < args.length - 1; i+=4;  ) {
        balls[j] = new Ball(
          Double.parseDouble(args[i+0]),
          Double.parseDouble(args[i+1]);
          Double.parseDouble(args[i+2]);
          Double.parseDouble(args[i+3]);
        );
        xPosition = Double.parseDouble(args[i]);
        yPosition = Double.parseDouble(args[i+1]);
        xVelocity = Double.parseDouble(args[i+2]);
        yVelocity = Double.parseDouble(args[i+3]);

      }
      timeSlice = Double.parseDouble( args[args.length - 1] );

    }

    if ( (args.length % 4) == 0 ) {
      for ( int i = 0; i < args.length; i+=4;  ) {
        balls[j] = new Ball(
          Double.parseDouble(args[i+0]),
          Double.parseDouble(args[i+1]);
          Double.parseDouble(args[i+2]);
          Double.parseDouble(args[i+3]);
        );
        xPosition = Double.parseDouble(args[i]);
        yPosition = Double.parseDouble(args[i+1]);
        xVelocity = Double.parseDouble(args[i+2]);
        yVelocity = Double.parseDouble(args[i+3]);

      }
      timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
    }

  }

  public static void main( String args[] ) {



    while () {
      ball.tick();
      if (location1 - location2) {
        System.exit(0);
      }
    }


  }



}
