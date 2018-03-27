/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File Name     : SoccerSim.java
 *  Purpose       : Main program for the SoccerSim class
 *  @author       : Breelyn Betts
 *  Date written  : 2018 - 03 - 20
 *  Description   : This class provides numerous methods useful for simulating
                    a program that tests whether two balls collide. (Homework 5)
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.text.DecimalFormat;

 public class SoccerSim {

     public static double xLocation;
     public static double yLocation;
     public static double xVel;
     public static double yVel;
     public static double timeSlice = 1.0;
     private static int ballQuantity;
     private int FOUR = 4;
     public static double fieldX = 500;
     public static double fieldY = 500;
     public static double poleX = 20;
     public static double poleY = 20;
     private final static double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;

     private static Timer timer;
     private static Ball[] balls;

     public SoccerSim() {
         super();
     }

     public double validateInitialArguments( String[] argValues) throws IllegalArgumentException, NumberFormatException {
       try {
         xLocation = Double.parseDouble(argValues[0]);
         yLocation = Double.parseDouble(argValues[1]);
         xVel = Double.parseDouble(argValues[2]);
         yVel = Double.parseDouble(argValues[3]);
       }
       catch( NumberFormatException nfe) {
         System.out.println(nfe.toString());
       }
       if (timeSlice > 1800) {
         throw new IllegalArgumentException("Not a valid timeSlice");
       }
       if (xLocation > fieldX || yLocation > fieldY ) {
         throw new IllegalArgumentException("Location out of range");
       }

       if ((argValues.length % 4) == 1) {
         ballQuantity = (argValues.length - 1) / 4;
       }
       if ((argValues.length % 4) == 0) {
         ballQuantity = argValues.length / 4;
       } else if ((argValues.length % 4) == 2) {
         throw new IllegalArgumentException("Invalid number of arguments");
       } else if ((argValues.length % 4) == 3) {
         throw new IllegalArgumentException("Invalid number of arguments");
       }
       return ballQuantity;

     }
     public boolean isMovement() {
       for (int i = 0 ; i < ballQuantity; i++) {
         if (balls[i].xVel != 0.0 || balls[i].yVel != 0.0 ) {
           return true;
         }
       }
       return false;
     }

     public boolean isOnField() {
       for (int i =0 ; i < ballQuantity; i++) {
         if (balls[i].xLocation > (fieldX / 2) || balls[i].yLocation > (fieldY / 2 )) {
           return true;
         }
       }
       return false;
     }

     public void checkForCollision() {

     }

     public void handleInitialArguments( String args[] ) {
         System.out.println("\n   Hello world, from the SoccerSim program!!\n\n");
         if( 0 == args.length ) {
           System.out.println( "   Sorry you must enter at least one argument\n" +
                               "   Usage: java SoccerSim <x Location> <y Location> <x velocity> <y velocity> [timeSlice]\n" +
                               "   Please try again..........." );
             System.exit(1);
         }
         if ((args.length % 4) == 1) {
           timeSlice = Double.parseDouble(args[args.length - 1]);
         }
         else {
           timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
         }
         timer = new Timer(timeSlice);

         try {
           int j = 0;

           balls = new Ball[ballQuantity];
           for (int i = 0; i < args.length/FOUR; i++) {
             balls[j] = new Ball(this.timeSlice);
             balls[j].xLocation = Double.parseDouble(args[i]);
             balls[j].yLocation = Double.parseDouble(args[4 * i + 1]);
             balls[j].xVel = Double.parseDouble(args[4 * i + 2]);
             balls[j].yVel = Double.parseDouble(args[4 * i +3 ]);
             j++;
            }
         }
         catch( NumberFormatException nfe ) {
           System.out.println(nfe.toString());
         }

     }



     public static void main( String[] args ) {

       SoccerSim ss = new SoccerSim();
       DecimalFormat df = new DecimalFormat("#0.000");
       ss.checkForCollision();
       try {
         ss.validateInitialArguments( args );
       }
       catch( IllegalArgumentException iae) {
         System.out.println(iae.toString());
         System.exit(-1);
       }
       System.out.println("Test");
       System.out.println(ballQuantity);
       ss.handleInitialArguments( args );
       System.out.println("INITIAL REPORT at " + timer.toString());
          for ( int i = 0; i < ballQuantity; i++ ) {
            System.out.println(i + ": position <" + df.format(balls[i].xLocation) + "," + df.format(balls[i].yLocation)+ ">" );
            System.out.println(i + ": velocity <" + df.format(balls[i].xVel) + "," + df.format(balls[i].yVel) + ">");
          }
          System.out.println("Pole is located at (" + poleX + "," + poleY + ")\n");


       while ( ss.isMovement()  ) {
         System.out.println(timer.tick());
         System.out.println("PROGRESS REPORT at" + timer.toString());

         for ( int i = 0; i < ballQuantity; i++ ) {
           balls[i].move();
           System.out.println( i + "position <" + df.format(balls[i].getLocation()[0]) + "," + df.format(balls[i].move()[1]) + ">");
            if(balls[i].isMoving()) {
                System.out.println( i + "velocity <" + df.format(balls[i].xVel) + "," + df.format(balls[i].yVel) + ">");
            }
            else {
                System.out.println("       AT REST");
            }
          }
         }
         ss.checkForCollision();

       System.out.println("FINAL REPORT at" +  timer.toString() );
       System.out.println("no collision possible!");
       System.exit(-1);
     }

 }
