/** ---------------------------------------------------------------------------
 *  File name    : BrobInt.java
 *  @author      : Breelyn Betts
 *  Purpose      : Program that performs various operations on arbitrarily large numbers
 *  Date         : 04 - 03 - 18
 * ---------------------------------------------------------------------------- */

import java.util.Arrays;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"


   public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );


   public String internalValue = "";        // internal String representation of this BrobInt
   public  int   sign           = 0;         // "0" is positive, "1" is negative
   public String reversed      = "";        // the backwards version of the internal String representation
   public int[] intVersion     = null;      // int array for storing the string values; uses the reversed string
   public int longerValue      = 0;
   public int smallerValue     = 0;


  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      String val = value.trim();
      internalValue = val;
      reversed = new StringBuffer(internalValue).reverse().toString();
      if ( reversed.charAt( internalValue.length() - 1) == '-' ) {
        sign = 1;
        reversed = reversed.substring(0, reversed.length() - 1 );
      }
      intVersion = new int[reversed.length()];
      for ( int i = 0; i < reversed.length(); i++ ) {
        intVersion[i] = Integer.parseInt( "" + reversed.charAt(i) );
      }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
      String digits = "+-123456789";
      for ( int i = 0 ; i < internalValue.length(); i++ ) {
        if ( !digits.contains("" + internalValue.charAt(i)) ) {
          throw new NumberFormatException("Invalid argument given, number must be a number");
        }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String reverser() {
      return new StringBuffer(internalValue).reverse().toString();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      return new BrobInt(gint.reversed);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt gint ) {
     int carry = 0;
     int resultSign = 0;
     String resultValue = "";
     int borrow = 0;


      if ( reversed.length() >= gint.reversed.length() ) {
        longerValue = reversed.length();
        smallerValue = gint.reversed.length();
      } else if ( reversed.length() < gint.reversed.length() ) {
        longerValue = gint.reversed.length();
        smallerValue = reversed.length();
      }
      int[] result = new int[ longerValue + 2 ];

     if ( sign == 1 && gint.sign == 1 ) {
        resultSign = 1;
     }

     if ( sign == gint.sign ) {
       if (reversed.length() >= gint.reversed.length()) {
         for ( int i = 0; i <= longerValue; i++ ) {
           if ( i < smallerValue ) {
             result[i] = intVersion[i] + gint.intVersion[i] + carry;
             if ( result[i] > 9 ) {
               result[i] -= 10;
               carry = 1;
             } else {
               carry = 0;
             }
           } else if ( i < longerValue) {
             result[i] = intVersion[i] + carry;
             if ( result[i] > 9 ) {
               result[i] -= 10;
               carry = 1;
             } else {
               carry = 0;
             }
           } else {
             result[i] = carry;
           }

         }
       } else if (gint.reversed.length() > reversed.length()) {
         for ( int i = 0; i < longerValue; i++ ) {
           if ( i < smallerValue ) {
             result[i] = intVersion[i] + gint.intVersion[i] + carry;
             if ( result[i] > 9 ) {
               result[i] -= 10;
               carry = 1;
             } else {
               carry = 0;
             }
           } else {
             result[i] = gint.intVersion[i] + carry;
             if ( result[i] > 9 ) {
               result[i] -= 10;
               carry = 1;
             } else {
               carry = 0;
             }
           }
         }
       }
     } else if ( sign != gint.sign ) {
       if ( reversed.length() > gint.reversed.length() ) {
         for ( int i = 0; i <= longerValue; i++ ) {
           result[i] = intVersion[i] - gint.intVersion[i] + borrow;
           if ( gint.intVersion[i] > intVersion[i]) {
             result[i+1] -= 1;
             borrow = 10;
           } else {
             borrow = 0;
           }
         } resultSign = 0;
       } else if ( reversed.length() < gint.reversed.length()) {
         for ( int i = 0; i <= longerValue; i++ ) {
           result[i] = gint.intVersion[i] - intVersion[i] + borrow;
           if ( gint.intVersion[i] < intVersion[i]) {
             result[i+1] -= 1;
             borrow = 10;
           } else {
             borrow = 0;
           }
         }
         resultSign = 1;
       }
     }

     for (int i = result.length - 1; i >= 0; i-- ) {
       resultValue += String.valueOf(result[i]);
     }

      int j = 0;
      while ( resultValue.charAt(j) == '0') {
        j++;
      }
      resultValue = resultValue.substring(j, resultValue.length());
      if (resultSign == 1 ) {
         resultValue = "-" + resultValue;
      }

      return new BrobInt(resultValue);
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt gint ) {
      int borrow = 0;
      int resultSign = 0;
      String resultValue = "";
      int carry = 0;

      if ( reversed.length() >= gint.reversed.length() ) {
        longerValue = reversed.length();
        smallerValue = gint.reversed.length();
      } else if ( reversed.length() < gint.reversed.length() ) {
        longerValue = gint.reversed.length();
        smallerValue = reversed.length();
      }
      int[] result = new int[ longerValue + 1 ];

      if ( sign == 0 && gint.sign == 0 ) {
        if ( reversed.length() >= gint.reversed.length() ) {
          for ( int i = 0; i <= longerValue; i++ ) {
            if (i < smallerValue ) {
              result[i] = intVersion[i] - gint.intVersion[i] + borrow;
              if ( result[i] < 0 ) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else if ( i < longerValue ) {
              result[i] = intVersion[i] + borrow;
              if ( result[i] < 0) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else {
              result[i] = borrow;
            }

          } resultSign = 0;
        } else if ( reversed.length() < gint.reversed.length()) {
          for ( int i = 0; i <= longerValue; i++ ) {
            if (i < smallerValue) {
              result[i] = gint.intVersion[i] - intVersion[i] + borrow;
              if ( result[i] < 0 ) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else if ( i < longerValue) {
              result[i] = gint.intVersion[i] + borrow;
              if ( result[i] < 0) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else  {
              result[i] = borrow;
            }
          }
          resultSign = 1;
        }
      }

      else if ( sign == 0 && gint.sign == 1 ) {
        if (reversed.length() >= gint.reversed.length()) {
          for ( int i = 0; i <= longerValue; i++ ) {
            if ( i < smallerValue ) {
              result[i] = intVersion[i] + gint.intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            } else if ( i < longerValue) {
              result[i] = intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            } else {
              result[i] = carry;
            }
          }
        } else if (gint.reversed.length() > reversed.length()) {
          for ( int i = 0; i < longerValue; i++ ) {
            if ( i < smallerValue ) {
              result[i] = intVersion[i] + gint.intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            } else {
              result[i] = gint.intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            }
          }
        }
      }
      else if ( sign == 1 && gint.sign == 0 ) {
        if (reversed.length() >= gint.reversed.length()) {
          for ( int i = 0; i <= longerValue; i++ ) {
            if ( i < smallerValue ) {
              result[i] = intVersion[i] + gint.intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            } else if ( i < longerValue) {
              result[i] = intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            } else {
              result[i] = carry;
            }
          }
        } else if (gint.reversed.length() > reversed.length()) {
          for ( int i = 0; i < longerValue; i++ ) {
            if ( i < smallerValue ) {
              result[i] = intVersion[i] + gint.intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            } else {
              result[i] = gint.intVersion[i] + carry;
              if ( result[i] > 9 ) {
                result[i] -= 10;
                carry = 1;
              } else {
                carry = 0;
              }
            }
          } resultSign = 1;
        }
      }
      else if ( sign == 1 && gint.sign == 1 ) {
        if ( reversed.length() >= gint.reversed.length() && this.compareTo(gint) == 1 ) {
          for ( int i = 0; i <= longerValue; i++ ) {
            if (i < smallerValue ) {
              result[i] = intVersion[i] - gint.intVersion[i] + borrow;
              if ( result[i] < 0 ) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else if ( i < longerValue ) {
              result[i] = intVersion[i] + borrow;
              if ( result[i] < 0) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else {
              result[i] = borrow;
            }
          }
          if (this.compareTo(gint) == -1 ) {
            resultSign = 0;
          } else if (this.compareTo(gint) == 1) {
            resultSign = 1;
          }
        } else if ( reversed.length() < gint.reversed.length()) {
          for ( int i = 0; i <= longerValue; i++ ) {
            if (i < smallerValue) {
              result[i] = gint.intVersion[i] - intVersion[i] + borrow;
              toArray(result);
              if ( result[i] < 0 ) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else if ( i < longerValue) {
              result[i] = gint.intVersion[i] + borrow;
              if ( result[i] < 0) {
                result[i+1] -= 1;
                borrow = 10;
              } else {
                borrow = 0;
              }
            } else  {
              result[i] = borrow;
            }
          } resultSign = 0;
        } else if (this.compareTo(gint) == -1 ) {
          for ( int i = 0; i < longerValue; i++) {
            result[i] = gint.intVersion[i] - intVersion[i] + borrow;
            if (result[i] < 0) {
              result[i+1] -= 1;
              borrow = 10;
            } else {
              borrow = 0;
            }
          } resultSign = 0;
        }
      }
      for ( int i = result.length - 1; i >= 0; i-- ) {
        resultValue += result[i];
      }

      int j = 0;
      while ( resultValue.charAt(j) == '0') {
        j++;
      }
      resultValue = resultValue.substring(j, resultValue.length());
      if (resultSign == 1 ) {
         resultValue = "-" + resultValue;
      }
      return new BrobInt(resultValue);
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      int[] dividend = new int[2];
      //
      // if (reversed.compareTo(gint.toString()) < 0 ) {
      //    dividend = 1;
      // }
      //
      // if ( reversed.compareTo(gint.toString())) {
      //   dividend = 0;
      // }
      //



      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
     if( internalValue.length() > gint.internalValue.length() ) {
        return 1;
     } else if( internalValue.length() < gint.internalValue.length() ) {
        return (-1);
     } else {
        for( int i = 0; i < internalValue.length(); i++ ) {
           Character a = new Character( internalValue.charAt(i) );
           Character b = new Character( gint.internalValue.charAt(i) );
           if( new Character(a).compareTo( new Character(b) ) > 0 ) {
              return 1;
           } else if( new Character(a).compareTo( new Character(b) ) < 0 ) {
              return (-1);
           }
        }
     }
    return 0;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( int[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );

      System.exit( 0 );
   }
}
