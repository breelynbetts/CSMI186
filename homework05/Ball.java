/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File Name     :
 *  Purpose       :
 *  @author       : Breelyn Betts
 *  Date written  :
 *
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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



  public Ball() {

  }

  public Ball( double x, double y, double xv, double yv) {
    xPosition = x;
    yPosition = y;
    xVelocity = xv;
    yVelocity = yv;
  }

  public double validateLocationArgs(argValue) {
    return 0;
  }

  public double validateVelocityArgs(argValue) {

  }

  public double ballFriction() {
    return 0;
  }
}
