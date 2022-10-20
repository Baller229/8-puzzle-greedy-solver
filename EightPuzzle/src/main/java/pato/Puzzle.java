package pato;
import mpLog.MPLog;

class Puzzle implements Comparable<Puzzle> {

   private int missplaced;
   private String position;
   private Puzzle previous;
   public Puzzle ( String value, Puzzle p  )
   {
      this.position = value;
      previous =p;
   }

   public boolean equals(Object other)
   {
      //MPLog.dbg ("equals");
      if (other != null && other instanceof Puzzle)
      {
         if ( position.equals(((Puzzle)other).position))
            return true;
      }
      return false ;
   }

   //==========================================
   //   SET PRIORITY PUZZLE IN PriorityQueue
   //==========================================

   public int compareTo( Puzzle other)
   {
      //MPLog.dbg ("compareTo");
      if (missplaced <other.missplaced)return  -1 ;
      if (missplaced ==other.missplaced)return 0 ;
      else return 1 ;
   }

   //===================================
   // SETTERS
   //===================================

   public void setMissplaced ( int n ) {
      missplaced =n;
   }

   //===================================
   // getters
   //===================================

   public Puzzle getPrevious () {
      return previous;
   }

   public String getPosition () {
      return position;
   }
}