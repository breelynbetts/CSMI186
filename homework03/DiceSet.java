public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
     this.count = count;

     this.sides = sides;
     if ( count < 1 ) {
       throw new IllegalArgumentException( "Invalid number of dice given" );
     }
     if ( sides < 4 ) {
       throw new IllegalArgumentException( "Illegal number of sides given" );
     }
     for ( int i =0; i < count - 1; i ++ ) {
       ds[i] = new Die( sides );
     }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
     int sum = 0;
     for ( int i = 0; i < count -1; i++ ) {
       sum += ds[i].getValue();
     }
      return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
     for ( int i =0; i < count - 1; i ++ ) {
       ds[i].roll();
     }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
      return ds[dieIndex].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int getIndividual( int dieIndex ) {
      return ds[dieIndex].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
     String output = "";
     for ( int i =0; i < count - 1; i ++ ) {
       output += ds[i].toString();
     }
     return output;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
      return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet ds ) {
      if (count == ds.count ) {
        return true;
      } else if (sides == ds.sides) {
        return true;
      }
      return false;
   }

  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {

   }

}
