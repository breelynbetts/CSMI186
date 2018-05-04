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
    if (checkDupes(denoms)) {
      return Tuple.IMPOSSIBLE;
    }
    if (validateTargetValue(targetValue)) {
      return Tuple.IMPOSSIBLE;
    }
    if (validateDenoms(denoms)) {
      return Tuple.IMPOSSIBLE;
    }

    int length = denoms.length;
    int columns = targetValue + 1;
    t = new Tuple[length][columns];

    System.out.println("\ndenominations length: " + length + "\ntargetValue: " + targetValue);
    fillWithZeroes(t, denoms);

    for ( int i = 0; i < length; i++) {
      for (int j = 0; j < columns; j++) {

          if ( j == 0 ) {
            t[i][j] = new Tuple(length);
          } else if (denoms[i] > j) {
            t[i][j] = Tuple.IMPOSSIBLE;
          } else {
            t[i][j] = new Tuple(length);
            t[i][j].setElement(i, 1);
            if ( t[i][j - denoms[i]].isImpossible() ) {
              t[i][j] = Tuple.IMPOSSIBLE;
            } else {
              t[i][j] = t[i][j].add(t[i][j - denoms[i]]);
            }
          }

          if (i!= 0) {
            if ( !(t[i-1][j].isImpossible()) ){
              if ( t[i][j].isImpossible()) {
                t[i][j] = t[i-1][j];
              }
              if ( t[i-1][j].total() < t[i][j].total()) {
                t[i][j] = t[i-1][j];
              }
            }
          }

      }
    }
    System.out.println("\t tuple: " + t[length-1][targetValue]);

    return t[length - 1][targetValue];
  }

  /**
   * method to check that each element is unique within the denominations array
   * @param denom boolean value that evaluates each element of the integer array to
   *   check for duplicate values
   *   note the method displays true if there are duplicates
   */

  public static boolean checkDupes( int[] denoms ) {
    for ( int i = 0; i < denoms.length - 1; i++ ) {
      for (int j = i + 1; j < denoms.length; j++ ) {
        if ( denoms[i] == denoms[j] || denoms[i] < 1 || denoms[j] < 1 || denoms.length < 1 ) {
          return true;
        }
      }
    } return false;
  }


  public static void fillWithZeroes( Tuple[][] t, int[] denom) {
    for ( int i = 0; i < denom.length; i++ ) {
      t[i][0] = new Tuple(denom.length);
    }
  }

  /**
   * method to check that target value is greater than 0
   * @param target boolean value that evaluates the given target value and checks
   *   for negative values
   */
  public static boolean validateTargetValue( int target ) {
    if (target < 0) {
      return true;
    } return false;
  }

  /**
   * method to check that each element of the denominations array is greater than 0
   * @param denom boolean value that evaluates each element of the integer array to
   *   check for negative values
   */
  public static boolean validateDenoms( int[] denom) {
    for (int i = 0; i < denom.length; i++ ) {
      if ( denom[i] < 1 ) {
        return true;
      }
    } return false;
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
