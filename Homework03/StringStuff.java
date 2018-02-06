import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {

  /**
   * Method to determine if a string contains one of the vowels: A, E, I, O, U, and sometimes Y.
   * Both lower and upper case letters are handled.  In this case, the normal English rule for Y means
   * it gets included.
   *
   * @param s String containing the data to be checked for &quot;vowel-ness&quot;
   * @return  boolean which is true if there is a vowel, or false otherwise
   */

   public static boolean containsVowel( String s ) {
      s = s.toLowerCase();
      // int vowels = 0;
      for (int i = 0; i <= s.length(); ++i ) {
        char c = s.charAt(i);
        if ( c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y') {
          return true;
        }
      }
      return false;
   }

   /**
    * Method to determine if a string is a palindrome.  Does it the brute-force way, checking
    * the first and last, second and last-but-one, etc. against each other.  If something doesn't
    * match that way, returns false, otherwise returns true.
    *
    * @param s String containing the data to be checked for &quot;palindrome-ness&quot;
    * @return  boolean which is true if this a palindrome, or false otherwise
    */
    public static boolean isPalindrome( String s ) {
      int word = s.length();
      for (int i = 0; i < (word/2); ++i ) {
        if (s.charAt(i) != s.charAt( word - i - 1)) {
          return false;
        }
      }
      return true;
    }

    /**
     * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
     * numbers of the alphabet.  The letters B, D, F, H, J, L, N, P, R, T, V, X, and Z are even,
     * corresponding to the numbers 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, and 26.
     *
     * @param s String containing the data to be parsed for &quot;even&quot; letters
     * @return  String containing the &quot;even&quot; letters from the input
     */
     public static String evensOnly( String s ) {
       String evens = " BDFHJLNPRTVXZbdfhjlmprtvxz";
       String noOdds = "";

       for (int i = 0; i < s.length(); i++) {
         if (evens.contains(Character.toString(s.charAt(i)))) {
           noOdds = noOdds + (Character.toString(s.charAt(i)));
         }
       }
       System.out.println( " string output " + noOdds);
       return noOdds;
     }

     /**
      * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
      * numbers of the alphabet.  The letters A, C, E, G, I, K, M, O, Q, S, U, W, and Y are odd,
      * corresponding to the numbers 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, and 25.
      *
      * @param s String containing the data to be parsed for &quot;odd&quot; letters
      * @return  String containing the &quot;odd&quot; letters from the input
      */
      public static String oddsOnly( String s ) {
        String odds = " ACEGIKMOQSUWYacegikmoqsuwy";
        String noEvens = "";
        for (int i = 0; i < s.length(); i++) {
          if (odds.contains(Character.toString(s.charAt(i)))) {
            noEvens = noEvens + (Character.toString(s.charAt(i)));
          }
        }
          System.out.println( " string output " + noEvens);
          return new String( noEvens );

      }

      /**
       * Method to return the characters in a string that correspond to the &quot;EVEN&quot; index
       * numbers of the alphabet, but with no duplicate characters in the resulting string.
       *
       * @param s String containing the data to be parsed for &quot;even&quot; letters
       * @return  String containing the &quot;even&quot; letters from the input without duplicates
       */
       public static String evensOnlyNoDupes( String s ) {
         s = evensOnly(s);
         String noDupes = "";

         for ( int i = 0; i < s.length(); i++ ) {
           if (!noDupes.contains(Character.toString(s.charAt(i)))) {
             noDupes = noDupes + (Character.toString(s.charAt(i)));
           }
         }
          System.out.println("new string output" + noDupes);
          return new String( noDupes );
       }

    /**
      * Method to return the characters in a string that correspond to the &quot;ODD&quot; index
      * numbers of the alphabet, but with no duplicate characters in the resulting string.
      *
      * @param s String containing the data to be parsed for &quot;odd&quot; letters
      * @return  String containing the &quot;odd&quot; letters from the input without duplicates
      */
      public static String oddsOnlyNoDupes( String s ) {
        s = oddsOnly(s);
        String noDupes = "";

        for (int i = 0; i < s.length(); i++ ) {
          if (!noDupes.contains(Character.toString(s.charAt(i)))) {
            noDupes = noDupes + (Character.toString(s.charAt(i)));
          }
        }
        System.out.println("new string output" + noDupes);
          return new String( noDupes );
      }


      /**
       * Method to return the reverse of a string passed as an argument
       *
       * @param s String containing the data to be reversed
       * @return  String containing the reverse of the input string
       */
       public static String reverse( String s ) {
         String reverse = "";

         for (int i = s.length() - 1; i >= 0; i-- ) {
           reverse = reverse + s.charAt(i);
         }
         System.out.println( "reverse: " + reverse);
          return new String( reverse );
       }

}
