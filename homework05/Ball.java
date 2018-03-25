/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File Name     : Ball.java
 *  Purpose       : Provides a class defining methods for SoccerSim class
 *  @author       : Breelyn Betts
 *  Date written  : 2018 - 03- 20
 *
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.text.DecimalFormat;

public class Ball {

  public static final double MAXIMUM_VALUE_OF_X = 300;
  public static final double MINIMUM_VALUE_OF_X = -300;
  public static final double MAXIMUM_VALUE_OF_Y = 300;
  public static final double MINIMUM_VALUE_OF_Y = -300;
  private double RADIUS_IN_INCHES = 4.45;
  private double WEIGHT_IN_POUNDS = 1;

  private double xPosition = 0;
  private double yPosition = 0;
  private double xVelocity = 0;
  private double yVelocity = 0;
  private double timeSlice = 0;



  public Ball() {

  }

  public Ball( double x, double y, double xv, double yv, double ts) {
    xPosition = x;
    yPosition = y;
    xVelocity = xv;
    yVelocity = yv;
    timeSlice = ts;
  }

  public double[] getLocation() {
    double [] location = new double[2];
    location[0] = xPosition;
    location[1] = yPosition;
    return location;
  }

  public double[] getVelocity() {
    double [] velocity = new double[2];
    velocity[0] = xVelocity;
    velocity[1] = yVelocity;
    return velocity;
  }

  public void friction() {
    this.xVelocity = xVelocity - ( (xVelocity * 0.01) * timeSlice );
    this.yVelocity = yVelocity - ( (yVelocity * 0.01) * timeSlice );
  }

  public double[] move() {
    double [] move = new double [2];
    move[0] = xPosition + xVelocity;
    move[1] = yPosition + yVelocity;
    friction();
    return move;

  }


  public boolean isMoving() {
    return ( xVelocity > 1 )|| ( yVelocity > 1 );
  }

  public boolean isOnField( ) {
    if (Math.abs(xPosition) <= (MAXIMUM_VALUE_OF_X) && Math.abs(yPosition) <= (MAXIMUM_VALUE_OF_Y)) {
      return true;
    }
    return false;
  }

  public String toString() {
    DecimalFormat df = new DecimalFormat("#0.000");
    String ball = "<" + df.format(xPosition) + "," + df.format(yPosition) + ">" + "<" + df.format(xVelocity) + "," + df.format(yVelocity) + ">";
    return ball;
  }
}
