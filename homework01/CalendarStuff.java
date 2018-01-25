/**
  * File Name : CalendarStuff.java
  * Purpose : Create an algorith that gives the number of days between two given dates.
  * @author : Breelyn Betts
  * Date : 2018 - 01 - 24
*/
public class CalendarStuff {

  /**
     * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
     */
     private static final int SUNDAY    = 0;
     private static final int MONDAY    = SUNDAY    + 1;
     private static final int TUESDAY   = MONDAY    + 1;
     private static final int WEDNESDAY = TUESDAY   + 1;
     private static final int THURSDAY  = WEDNESDAY + 1;
     private static final int FRIDAY    = THURSDAY  + 1;
     private static final int SATURDAY  = FRIDAY    + 1;

   /**
    * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
    */
    private static final int JANUARY    = 0;
    private static final int FEBRUARY   = JANUARY   + 1;
    private static final int MARCH      = FEBRUARY  + 1;
    private static final int APRIL      = MARCH     + 1;
    private static final int MAY        = APRIL     + 1;
    private static final int JUNE       = MAY       + 1;
    private static final int JULY       = JUNE      + 1;
    private static final int AUGUST     = JULY      + 1;
    private static final int SEPTEMBER  = AUGUST    + 1;
    private static final int OCTOBER    = SEPTEMBER + 1;
    private static final int NOVEMBER   = OCTOBER   + 1;
    private static final int DECEMBER   = NOVEMBER  + 1;


    private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };



    public CalendarStuff() {
       System.out.println( "Constructor called CalendarStuff" );
    }


    /**
     * A method to determine if the year argument is a leap year or not<br />
     *  Leap years are every four years, except for even-hundred years, except for every 400
     * @param    year  long containing four-digit year
     * @return         boolean which is true if the parameter is a leap year
     */

    public static boolean isLeapYear( long year ) {
       assert year > 1582;
       return (year % 400 == 0) || ((year % 4 == 0) & (year % 100 != 0));
    }


    /**
     * A method to calculate the days in a month, including leap years
     * @param    month long containing month number, starting with "1" for "January"
     * @param    year  long containing four-digit year; required to handle Feb 29th
     * @return         long containing number of days in referenced month of the year
     * notes: remember that the month variable is used as an indix, and so must
     *         be decremented to make the appropriate index value
     */
     public static long daysInMonth( long month, long year ) {
       if (isLeapYear(year) && (month - 1 == FEBRUARY )) {
         return (days[(int)month - 1] + 1 );
       }
       else {
         return (days[(int)month - 1]);
       }

  }

  /**
  * A method to determine if two dates are exactly equal
  * @param    month1 long    containing month number, starting with "1" for "January"
  * @param    day1   long    containing day number
  * @param    year1  long    containing four-digit year
  * @param    month2 long    containing month number, starting with "1" for "January"
  * @param    day2   long    containing day number
  * @param    year2  long    containing four-digit year
  * @return          boolean which is true if the two dates are exactly the same
  */
  public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     return (year1 == year2) && (month1 == month2) && (day1 == day2);
  }

  /**
  * A method to compare the ordering of two dates
  * @param    month1 long   containing month number, starting with "1" for "January"
  * @param    day1   long   containing day number
  * @param    year1  long   containing four-digit year
  * @param    month2 long   containing month number, starting with "1" for "January"
  * @param    day2   long   containing day number
  * @param    year2  long   containing four-digit year
  * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
  */
  public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {

    if (dateEquals(month1, day1, year1, month2, day2, year2)) {
      return 0;
    }
    else if( (year1 > year2) || (year1 == year2) && (month1 > month2) || (year1 == year2) && (month1 == month2) && (day1 > day2)) {
      return 1;
    }
    else
      return -1;
  }

  /**
  * A method to return whether a date is a valid date
  * @param    month long    containing month number, starting with "1" for "January"
  * @param    day   long    containing day number
  * @param    year  long    containing four-digit year
  * @return         boolean which is true if the date is valid
  * notes: remember that the month and day variables are used as indices, and so must
  *         be decremented to make the appropriate index value
  */
  public static boolean isValidDate( long month, long day, long year ) {
    if((isLeapYear(year) == true) && ((month - 1) == FEBRUARY)) {
      day--;
    } // add more
    if( (year >= 0) && (month - 1 >= JANUARY && month - 1 <= DECEMBER) && ((day >= 1) && (day <= days[(int)month - 1])) ) {
      return true;
    }
    else
      return false;
  }

  /**
  * A method to return a string version of the month name
  * @param    month long   containing month number, starting with "1" for "January"
  * @return         String containing the string value of the month (no spaces)
  */
  public static String toMonthString( int month ) {
     switch( month - 1 ) {
        case JANUARY    : return "January";
        case FEBRUARY   : return "February";
        case MARCH      : return "March";
        case APRIL      : return "April";
        case MAY        : return "May";
        case JUNE       : return "June";
        case JULY       : return "July";
        case AUGUST     : return "August";
        case SEPTEMBER  : return "September";
        case OCTOBER    : return "October";
        case NOVEMBER   : return "November";
        case DECEMBER   : return "December";
        default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
     }
  }

  /**
  * A method to return a string version of the day of the week name
  * @param    day int    containing day number, starting with "1" for "Sunday"
  * @return       String containing the string value of the day (no spaces)
  */
  public static String toDayOfWeekString( int day ) {
     switch( day - 1 ) {
        case SUNDAY     : return "Sunday";
        case MONDAY     : return "Monday";
        case TUESDAY    : return "Tuesday";
        case WEDNESDAY  : return "Wednesday";
        case THURSDAY   : return "Thursday";
        case FRIDAY     : return "Friday";
        case SATURDAY   : return "Saturday";
        default         : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
     }
  }

  /**
  * A method to return a count of the total number of days between two valid dates
  * @param    month1 long   containing month number, starting with "1" for "January"
  * @param    day1   long   containing day number
  * @param    year1  long   containing four-digit year
  * @param    month2 long   containing month number, starting with "1" for "January"
  * @param    day2   long   containing day number
  * @param    year2  long   containing four-digit year
  * @return          long   count of total number of days
  */
  // count the days program

  public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     long dayCount = 0;
     if (compareDate(month1, day1, year1, month2, day2, year2) == 1) {
       long temp = month1;
       month1 = month2;
       month2 = temp;
       temp = day1;
       day1 = day2;
       day2 = temp;
       temp = year1;
       year1 = year2;
       year2 = temp;
     }

     if (!isValidDate(month1, day1, year1)) {
       throw new IllegalArgumentException( "Illegal day value given to 'daysBetween()'." );
     } else if (!isValidDate(month2, day2, year2)) {
       throw new IllegalArgumentException( "Illegal day value given to 'daysBetween()'." );
     } else if (dateEquals(month1, day1, year1, month2, day2, year2) == true ){
       return 0;
     }

     // calculates differences in dates with the same years
     for (long i = year1; i <= year2; i++) {
        if (isLeapYear(i)) {
          dayCount ++;
        }
      }
     if ( year1 == year2) {
       if (month1 == month2) {
         dayCount = day2 - day1;
       } else if (month1 != month2) {
           for (long i = month1; i < month2; i++) {
             dayCount += (day1 - days[(int)month1]) + (day2 - days[(int)month2]) + days[(int)i];
           }
       }
     }

     else if (year1 != year2) {
       if (month1 == month2) {
         dayCount += ((year2 - year1) * 365) + (day2 - day1);
       }
       else if (month1 < month2) {
         for (long i = month1; i < month2; i++) {
          dayCount += days[(int)i] ;
          }
          dayCount += ((year2 - year1) * 365) + (days[(int)month1] - day1) + day2 - (days[(int)month1]) ;
        }
       else if (month1 > month2) {
         for (long i = month1; i < DECEMBER; i++) {
           dayCount += (days[(int)i] );
          }
          for (long i = JANUARY; i < month2; i++) {
             dayCount += ( days[(int)i] );
         }
         dayCount += (((year2 - year1) - 1) * 365) + (days[(int)month1] - day1) + day2 - 1 ;
       }
       }






     System.out.println(" DayCount = " + dayCount);
     return dayCount;
  }
}
