package pato;

import mpLog.MPLog;

import java.io.IOException;
import java.util.*;
import java.lang.Math;

public class Greedy {

//===========================================================
//                *** GREEDY ALGORITHM ***
//===========================================================
public static void greedyAlgorithm ( Puzzle puzzle, String algorihm)
{
   MPLog.dbg ("greedy start");
   double startTime=System.currentTimeMillis();
   PriorityQueue <Puzzle> positionCollection = new PriorityQueue<Puzzle>();
   positionCollection.add(puzzle);
   //MPLog.dbg ("positionCollection: ", positionCollection);

   LinkedList<Puzzle> alreadyUsedPosition = new LinkedList<Puzzle>();

   Puzzle current_puzzle;
   String nextPosition;
   Puzzle nextDirection;

   do
   {
      //MPLog.dbg ("greedy do while");
      current_puzzle = positionCollection.poll();
      if (alreadyUsedPosition.contains(current_puzzle))
         continue;
      else
         alreadyUsedPosition.add(current_puzzle);

      if (CheckUp (current_puzzle))
      {
         //MPLog.dbg ("Up: ", current_puzzle);
         nextPosition= nextUp (current_puzzle.getPosition ());
         nextDirection= new Puzzle (nextPosition,current_puzzle); //save nextPosition node and his parent
         nextDirection.setMissplaced (missplacedPuzzleCount (nextDirection.getPosition (), algorihm));
         positionCollection.add( nextDirection);
      }
      if (CheckRight (current_puzzle))
      {
         //MPLog.dbg ("Right: ", current_puzzle);
         nextPosition= nextRight (current_puzzle.getPosition ());
         nextDirection= new Puzzle (nextPosition,current_puzzle);
         nextDirection.setMissplaced (missplacedPuzzleCount (nextDirection.getPosition (),algorihm));
         positionCollection.add(nextDirection);
      }
      if (CheckDown (current_puzzle))
      {
         //MPLog.dbg ("DownAvailability: ", current_puzzle);
         nextPosition= nextDown (current_puzzle.getPosition ());
         nextDirection= new Puzzle (nextPosition,current_puzzle);
         nextDirection.setMissplaced (missplacedPuzzleCount (nextDirection.getPosition (),algorihm));
         positionCollection.add(nextDirection);
      }
      if (CheckLeft (current_puzzle))
      {
         //MPLog.dbg ("Left: ", current_puzzle);
         nextPosition= nextLeft (current_puzzle.getPosition ());
         nextDirection= new Puzzle (nextPosition,current_puzzle);
         nextDirection.setMissplaced (missplacedPuzzleCount (nextDirection.getPosition (),algorihm));
         positionCollection.add(nextDirection);
      }
   } while (!(current_puzzle.getPosition ().equals("123456780")));
   //MPLog.dbg ("End, current_puzzle equals: ", current_puzzle.getPosition ());
   System.out.println("\nThe sequence of states: ");
   printAllPuzzlePositions (current_puzzle);
   System.out.println("The cost of the solution: "+ solvedPuzzleCost (current_puzzle));
}

//===================================================================
// NEXT STEP LEFT DOWN RIGHT UP
//===================================================================
   public static String nextLeft ( String position)
   {
      //MPLog.dbg ("nextLeft");
      int zeroPuzzleIndex = position.indexOf('0');
      char beforeZero = position.charAt(zeroPuzzleIndex-1);
      position = position.substring(0,zeroPuzzleIndex-1)+'0'+beforeZero+position.substring(zeroPuzzleIndex+1);
      return position;
   }

   public static String nextDown ( String str)
   {
      //MPLog.dbg ("nextDown");
      int indexZero = str.indexOf('0');
      char underZero = str.charAt(indexZero+3);
      int indexUnder = str.indexOf(underZero);
      str = str.substring(0,indexZero)+underZero+str.substring(indexZero+1, indexUnder)+'0'+str.substring(indexUnder+1);
      return str;
   }

   public static String nextRight ( String str)
   {
      //MPLog.dbg ("nextRight");
      int indexZero = str.indexOf('0');   //
      char afterZero = str.charAt(indexZero+1);
      str = str.substring(0,indexZero)+afterZero+'0'+str.substring(indexZero+2);
      return str;
   }

   public static String nextUp ( String str)
   {
      //MPLog.dbg ("nextUp");
      int indexZero = str.indexOf('0');
      char overZero = str.charAt(indexZero-3);
      int indexOver = str.indexOf(overZero);
      str= str.substring(0,indexOver)+'0'+str.substring(indexOver+1, indexZero)+overZero+str.substring(indexZero+1);
         return str;
   }

//===================================================================
// check availablity to move
//===================================================================
   public static boolean CheckLeft ( Puzzle puzzle)
   {
      //MPLog.dbg ("Left");
      String position = puzzle.getPosition ();
      if (position.charAt(0)=='0' || position.charAt(3)=='0' ||position.charAt(6)=='0')
         return false;
      return true;
   }

   public static boolean CheckRight ( Puzzle puzzle)
   {
      //MPLog.dbg ("Right");
      String position = puzzle.getPosition ();
      if (position.charAt(2)=='0' || position.charAt(5)=='0' ||position.charAt(8)=='0')
         return false;
      return true;
      }

   public static boolean CheckDown ( Puzzle puzzle)
   {
      //MPLog.dbg ("Down");
      String position = puzzle.getPosition ();
      if (position.charAt(6)=='0' || position.charAt(7)=='0' ||position.charAt(8)=='0')
         return false;
      return true;
   }

   public static boolean CheckUp ( Puzzle puzzle)
   {
      //MPLog.dbg ("Up");
      String str = puzzle.getPosition ();
      if (str.charAt(0)=='0' || str.charAt(1)=='0' ||str.charAt(2)=='0')
         return false;
      return true;
   }

//===================================================================
// CALCULATE NUMBER OF MISSPLACED PUZZLES
//===================================================================
   public static int missplacedPuzzleCount ( String position, String algorithm)
   {
      if(algorithm == "h1")
      {
         //MPLog.dbg ("CalculateWrongTiles");
         int rows= 0;
         int columns=0;
         char numAtPosition;

          for (int i=0; i<position.length(); i++)
          {
            numAtPosition = position.charAt(i);
            if (  numAtPosition==('1') || numAtPosition==('2') || numAtPosition==('3') )
               if (!(i==0 || i==1 || i==2) )
                  rows++;
            if (  numAtPosition==('4') || numAtPosition==('5') || numAtPosition==('6') )
               if (!(i==3 || i==4 || i==5) )
                  rows++;
            if (  numAtPosition==('7') || numAtPosition==('8') || numAtPosition==('0') )
               if (!(i==6 || i==7 || i==8) )
                  rows++;
          }

         for (int i=0; i<position.length(); i++)
         {
            numAtPosition = position.charAt(i);
            if (  numAtPosition==('1') || numAtPosition==('4') || numAtPosition==('7') )
               if (!(i==0 || i==3 || i==6) )
                  columns++;
            if (  numAtPosition==('2') || numAtPosition==('5') || numAtPosition==('8') )
               if (!(i==1 || i==4 || i==7) )
                  columns++;
            if (  numAtPosition==('3') || numAtPosition==('6') || numAtPosition==('0') )
               if (!(i==2 || i==5 || i==8) )
                  columns++;
         }
         return rows + columns;
      }
      if(algorithm == "h2")
      {
         int row = 0;
         int column = 0;
         int finalRow = 0;
         int count = 0;
         int finalColumn = 0;
         char numAtPosition;
         int[] missplacedCount = new int[10];
         int indexOfFinalPosition;
         String final_position = "123456780";

         for (int i=0; i<position.length(); i++)
         {
            if(position.charAt (i) == final_position.charAt (i))
            {
               missplacedCount[i] = 0;
               continue;
            }
            numAtPosition = position.charAt(i);
            if ( i <= 2 )
            {
               row = 1;
               indexOfFinalPosition = final_position.indexOf (numAtPosition);
               finalRow = countRowPath(indexOfFinalPosition);
               missplacedCount[i] = countPuzzleStep (row, finalRow);
            }
            if ( i >= 3 && i <= 5 )
            {
               row = 2;
               indexOfFinalPosition = final_position.indexOf (numAtPosition);
               finalRow = countRowPath(indexOfFinalPosition);
               missplacedCount[i] = countPuzzleStep (row, finalRow);
            }
            if ( i >= 6 )
            {
               row = 3;
               indexOfFinalPosition = final_position.indexOf (numAtPosition);
               finalRow = countRowPath(indexOfFinalPosition);
               missplacedCount[i] = countPuzzleStep (row, finalRow);
            }
         }

         for (int i=0; i<position.length(); i++)
         {
            if(position.charAt (i) == final_position.charAt (i))
            {
               missplacedCount[i] += 0;
               continue;
            }
            numAtPosition = position.charAt(i);
            if ( i == 0 || i == 3 || i == 6 )
            {
               column = 1;
               indexOfFinalPosition = final_position.indexOf (numAtPosition);
               finalColumn = countColumnPath(indexOfFinalPosition);
               missplacedCount[i] += countPuzzleStep (column, finalColumn);
            }
            if ( i == 1 || i == 4 || i == 7 )
            {
               column = 2;
               indexOfFinalPosition = final_position.indexOf (numAtPosition);
               finalColumn = countColumnPath(indexOfFinalPosition);
               missplacedCount[i] += countPuzzleStep (column, finalColumn);
            }

            if ( i == 2 || i == 5 || i == 8 )
            {
               column = 3;
               indexOfFinalPosition = final_position.indexOf (numAtPosition);
               finalColumn = countColumnPath(indexOfFinalPosition);
               missplacedCount[i] += countPuzzleStep (column, finalColumn);
            }
         }
         for(int i=0; i< missplacedCount.length; i++)
         {
            count += missplacedCount[i];
         }
         return count;
      }
      return 0;
   }


public static int countRowPath(int index)
{
   if ( index <= 2 )
   {
      return 1;
   }
   if ( index >= 3 && index <= 5 )
   {
      return 2;
   }
   if ( index >= 6 )
   {
      return 3;
   }
   return 0;
}
public static int countColumnPath(int index)
{
   if ( index == 0 || index == 3 || index == 6 )
   {
      return 1;
   }
   if ( index == 1 || index == 4 || index == 7 )
   {
      return 2;
   }
   if ( index == 2 || index == 5 || index == 8 )
   {
      return 3;
   }
   return 0;
}

public static int countPuzzleStep(int missP, int finalP)
{
   return Math.abs (finalP - missP);
}
//===================================================================
//  COST OF SOLVED PUZZLE
//===================================================================
public static int solvedPuzzleCost ( Puzzle puzzle)
{
   //MPLog.dbg ("pathCost");
   int cost = 0;
   Puzzle temp  = puzzle;
   while (temp != null )
   {
      cost++;
      temp=temp.getPrevious () ;
   }
   return cost;
}

//===================================================================
// PRINT NODES PATH
//===================================================================
public static void printAllPuzzlePositions ( Puzzle position )
{
   //MPLog.dbg ("Printing..");
   if (position==null) return ;
   printAllPuzzlePositions (position.getPrevious ());
   String currentPosition = position.getPosition ();
   System.out.println("-------------");
   System.out.printf("| %c | %c | %c |",currentPosition.charAt(0), currentPosition.charAt(1), currentPosition.charAt(2));
   System.out.println("\n-------------");
   System.out.printf("| %c | %c | %c |",currentPosition.charAt(3), currentPosition.charAt(4), currentPosition.charAt(5));
   System.out.println("\n-------------");
   System.out.printf("| %c | %c | %c |",currentPosition.charAt(6), currentPosition.charAt(7), currentPosition.charAt(8));
   System.out.println("\n-------------\n");
   //System.out.print(position.getString()+" . ");
}
}