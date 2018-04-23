public class Fibonacci {

   private static final String usageMessage = "\n  You must enter an integer number....." +
                                              "\n    Please try again!" +
                                              "\n  USAGE: java Fibonacci <required_integer>\n\n";
   private static int    maxCount    = 0;
   private static int    working     = 15000;
   private static String end1        = "st";
   private static String end2        = "nd";
   private static String end3        = "rd";
   private static String endRest     = "th";
   private static String cardinality = "";
   public static BrobInt brob1 = new BrobInt("0");
   public static BrobInt brob2 = new BrobInt("1");
   public static BrobInt brob3 = new BrobInt("1");

   private static final  int NO_CMD_LINE_ARGS = -1;
   private static final  int BAD_CMD_LINE_ARG = -2;

   public Fibonacci() {
      super();
   }

   public static void main( String[] args ) {
      System.out.println( "\n\n   Welcome to the Fibonacci sequence number finder!\n" );
      if( 0 == args.length ) {
         System.out.println( usageMessage );
         System.exit( NO_CMD_LINE_ARGS );
      }
      try {
         maxCount = Integer.parseInt( args[0] );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
         System.exit( BAD_CMD_LINE_ARG );
      }
      if( 2 == args.length ) {
         try {
            working = Integer.parseInt( args[1] );
         }
         catch( NumberFormatException nfe ) {
            System.out.println( "\n   Sorry, that does not compute!!" + usageMessage );
            System.exit( BAD_CMD_LINE_ARG );
         }
      }

     // this is just for making the output pretty... no real "fibonacci" functionality
      int lastIndex = args[0].length() - 1;
      switch( args[0].charAt( lastIndex ) ) {
         case '1': cardinality = end1;
                   break;
         case '2': cardinality = end2;
                   break;
         case '3': cardinality = end3;
                   break;
         default : cardinality = endRest;
                   break;
      }

      System.out.println( "\n\n   Starting from zero, the " + maxCount + cardinality + " Fibonacci number is: " );

     // NOTE: this section is just a happy notification that lets the user know to be patient.......
      if( maxCount > working ) {
         System.out.println( "\n                This may take me a while; please be patient!!\n\n" );
      }

      if ( maxCount == 0 ) {
        brob3 = brob1;
      } else if ( maxCount == 1 ) {
        brob3 = brob2;
      } else {
        for (int i = 2; i < maxCount; i++ ) {
          brob3 = brob1.add(brob2);
          brob1 = brob2;
          brob2 = brob3;
        }
      }
      System.out.println( "\t" + brob3 );

      System.exit( 0 );
   }
}
