import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HighRoll {

  static DiceSet ds = null;
  static int highValue = 0;

   public static void main( String[] args ) {
     if( 0 == args.length ) {
        System.out.println("USAGE: java HighRoll #unknown-channel #unknown-channel");
        System.exit(-1);
     }

     ds = new DiceSet( Integer.parseInt(args[0]), Integer.parseInt(args[1]) );


     while( true ) {
         System.out.print("1. select to roll all dice in set\n" +
            "2. select to roll a single die\n" +
            "3. select to calculate your sum\n" +
            "4. select to save your sum as a high score\n" +
            "5. select to display the high score\n" +
            "q. select to quit the program");
         System.out.print( ">>" );

         BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );

         String inputLine = null;
           try {

              inputLine = input.readLine();

              if ( 0 == inputLine.length() ) {
                System.out.println( "Enter some text please!" );
              }
              switch (inputLine.charAt(0)) {
                case '1': ds.rollAll();
                          System.out.println(ds.toString());
                          break;
                case '2': System.out.println( "Which die would you like to roll?" );
                          inputLine = input.readLine();
                          System.out.println(ds.rollIndividual( Integer.parseInt(inputLine) - 1));
                          System.out.println(ds.toString());
                          break;
                case '3': ds.sum();
                          System.out.println(ds.toString());
                          break;
                case '4': highValue = ds.sum();
                          System.out.println(ds.toString());
                          break;
                case '5': System.out.println(" Your high score = " + highValue);
                          System.out.println(ds.toString());
                          break;
                case 'q': System.exit(0);
                          break;
                default: System.out.println( "Invalid Entry" );
              }

          }
          catch( IOException ioe ) {
              System.out.println( "Caught IOException" );
         }
      }
   }
}
