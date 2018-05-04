/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name    : DynamicChangeMaker.java
 *  @author      : Breelyn Betts
 *  Purpose      : Class that finds the optimal solution for the amount of coins
 *                 with different denominations for a given target value
 *  Date         : 05 - 01 - 18
 *  Notes        : none
 *  Warnings     : none
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2018-05-01  Breelyn Betts Initial release; originaly have the DynamicChangeMaker() constructor,
                                     makeChangeWithDynamicProgramming() method, and a main method to parse
                                     arguments from the command line.
    1.1.0  tbt
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */


public class DynamicChangeMaker {

  private static int[] denominations = null;
  private static Tuple denominationTuple = null;
  private static Tuple[][] t = null;

  public DynamicChangeMaker() {
    super();
  }

  public static Tuple makeChangeWithDynamicProgramming( int[] denoms, int targetValue) {
    int length = denoms.length;
    int columns = targetValue + 1;

    System.out.println("\ndenominations length: " + length + "\ntargetValue: " + targetValue);
    if ( targetValue < 1 ) {
      throw new IllegalArgumentException("Target Value must be greater than 0");
    }

    for ( int i = 0; i < length - 1; i++ ) {
      for (int j = i + 1; j < length; j++ ) {
        if ( denoms[i] == denoms[j] || denoms[i] < 1 || denoms[j] < 1 || denoms.length < 1 ) {
          throw new IllegalArgumentException( "There may not be any duplicate denominations.");
        }
      }
    }

    t = new Tuple[length][columns];

    for ( int rows = 0; rows < length; rows++) {
      for (int col = 0; col < columns; col++) {
        if ( rows == 0 ) {
          if ( col == 0 ) {
            t[rows][col] = new Tuple(length);
          } else if (denoms[rows] > col) {
            t[rows][col] = Tuple.IMPOSSIBLE;
          } else {
            t[rows][col] = new Tuple(length);
            t[rows][col].setElement(rows, 1);
            if ( t[rows][col - denoms[rows]].isImpossible() ) {
              t[rows][col] = Tuple.IMPOSSIBLE;
            } else {
              t[rows][col] = t[rows][col].add(t[rows][col - denoms[rows]]);
            }
          }
        }
        else {
          if (col == 0) {
            t[rows][col] = new Tuple(denoms.length);
          }
          if (denoms[rows] > col) {
            t[rows][col] = Tuple.IMPOSSIBLE;
            if ( !(t[rows-1][col].isImpossible()) ){
              if ( t[rows-1][col].total() < t[rows][col].total()) {
                t[rows][col] = t[rows-1][col];
              }
            }
          } else {
            t[rows][col] = new Tuple(denoms.length);
            t[rows][col].setElement(rows, 1);
            if ( t[rows][col - denoms[rows]].isImpossible() ) {
              t[rows][col] = Tuple.IMPOSSIBLE;
            } else {
              t[rows][col] = t[rows][col].add(t[rows][col - denoms[rows]]);
            } if ( !(t[rows-1][col].isImpossible()) ){
              if ( t[rows-1][col].total() < t[rows][col].total()) {
                t[rows][col] = t[rows-1][col];
              }
            }
          }
        }
      }
    }
    System.out.println("\t tuple: " + t[length-1][targetValue]);


    return t[length - 1][targetValue];
  }

  public static void main ( String args[] ) {
    // only need for extra credit
    // argument parse / translation
    // display result
    // would have to return a tuple
    // denominations = String.parseString(args[0]);
    // String[] denominations = denominations.split(",");
    // denoms = denominations;
    // targetValue = Integer.parseInt(args[1]);
    // Tuple[][] t = new Tuple[denoms.length][targetValue];
  }





}
