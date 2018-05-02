// add JavaDocs


public class DynamicChangeMaker {

  // public static int targetValue = 0;
  // public static int[] denoms = null;
  // public static String denominations = "";


  public DynamicChangeMaker() {
    super();
  }

  public static Tuple makeChangeWithDynamicProgramming( int[] denoms, int targetValue) {
    int rows = 0;
    int columns = 0;

    for ( int i = 0; i < denoms.length - 1; i++ ) {
      for ( int j = i + 1; j < denoms.length; i++ ) {
        if ( denoms[i] == denoms[j] || denoms[i] == 0) {
          throw new IllegalArgumentException("Argument cannot have duplicates or zeroes");
        }
      }
      rows = denoms.length;
    }
     // check for targetValue
    if ( targetValue < 1 ) {
      throw new IllegalArgumentException("Target Value muse be greater than 0");
    } else {
      columns = targetValue + 1;
    }

    Tuple[][] t = new Tuple[rows][columns];

    for ( int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if ( i == 0 ) {
          if (j == 0) {
            t[i][j] = new Tuple(denoms.length);
          }
          if (denoms[i] > j) {
            t[i][j] = Tuple.IMPOSSIBLE;
          } else {
            t[i][j] = new Tuple(denoms.length);
            t[i][j].setElement(i, 1);
            if ( t[i][j - denoms[i]].isImpossible() ) {
              t[i][j] = Tuple.IMPOSSIBLE;
            } else {
              t[i][j] = t[i][j].add(t[i][j - denoms[i]]);
            }
          }
        }
        else {
          if (j == 0) {
            t[i][j] = new Tuple(denoms.length);
          }
          if (denoms[i] > j) {
            t[i][j] = Tuple.IMPOSSIBLE;
            if ( !(t[i-1][j].isImpossible()) ){
              if ( t[i-1][j].total() < t[i][j].total()) {
                t[i][j] = t[i-1][j];
              }
            }
          } else {
            t[i][j] = new Tuple(denoms.length);
            t[i][j].setElement(i, 1);
            if ( t[i][j - denoms[i]].isImpossible() ) {
              t[i][j] = Tuple.IMPOSSIBLE;
            } else {
              t[i][j] = t[i][j].add(t[i][j - denoms[i]]);
            } if ( !(t[i-1][j].isImpossible()) ){
              if ( t[i-1][j].total() < t[i][j].total()) {
                t[i][j] = t[i-1][j];
              }
            }
          }
        }
      }
    }
    return t[rows-1][columns - 1];
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
