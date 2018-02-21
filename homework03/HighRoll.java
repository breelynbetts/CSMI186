import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

  static int highValue = 0;
  static DiceSet ds = null;

   public static void main( String args[] ) {
      ds = new DiceSet( Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      System.out.println( "\n   Welcome to the MainProgLoopDemo!!\n" );
      System.out.println( "     Press the 'q' key to quit the program." );

     // This line uses the two classes to assemble an "input stream" for the user to type
     // text into the program
      BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
      while( true ) {
         System.out.print( ">>" );
         String inputLine = null;
         try {
            inputLine = input.readLine();
            switch (inputLine.charAt(0)) {
              case '1': ds.roll();
                        System.out.println("");
                        break;
              case '2': System.out.println( "Which die would you like to roll?" );
                        ds.getIndividual( Integer.parseInt( input.readLine() ) - 1);
                        System.out.println("The die: " + input.readLine() + "rolled");
                        break;
              case '3': ds.sum();
                        System.out.println("");
                        break;
              case '4': highValue = ds.sum();
                        System.out.println("");
                        break;
              case '5': System.out.println(" high score = " + highValue);
                        break;
              case 'q': System.exit(0);
                        System.out.println("Thank you for playing.");
                        break;
              default: System.out.println( "Invalid Entry" );
            }
            if( 0 == inputLine.length() ) {
               System.out.println("enter some text!:");
            }
            System.out.println( "   You typed: " + inputLine );

            if( 'q' == inputLine.charAt(0) ) {
               break;
            }

         }
         catch( IOException ioe ) {
            System.out.println( "Caught IOException" );
         }
      }
   }
}
