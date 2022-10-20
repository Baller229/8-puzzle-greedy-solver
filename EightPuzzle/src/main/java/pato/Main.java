package pato;

import java.io.IOException;

import static pato.Greedy.greedyAlgorithm;
import static pato.Greedy.missplacedPuzzleCount;

public class Main
{
public static void main(String[]args) throws IOException
   {
   String start_position = "508421736"; //123406758
   //MPLog.dbg ("start numbers :", start_position);
   System.out.println("\n\nStart *** GREEDY ALGORITHM *** \n");
   // meranie casu
   Measurement time = new Measurement();
   long duration1;
   long duration2;

   //******************************************************************************
   //    HEURISTIC.1 ALGORITHM
   //******************************************************************************
   Puzzle puzzle = new Puzzle (start_position, null);
   //MPLog.dbg ("puzzle: ", puzzle);
   puzzle.setMissplaced (missplacedPuzzleCount (start_position, "h1"));
   //MPLog.dbg ("puzzle missplaced: ", puzzle);
   time.Start ();
   greedyAlgorithm (puzzle, "h1");
   time.Stop ();
   duration1 = time.GetDurationInMiliSec ();
   //______________________________________________________________________________

   //******************************************************************************
   //    HEURISTIC.2 ALGORITHM
   //******************************************************************************
   Puzzle puzzle2 = new Puzzle (start_position, null);
   //MPLog.dbg ("puzzle2: ", puzzle2);
   puzzle2.setMissplaced (missplacedPuzzleCount (start_position, "h2"));
   //MPLog.dbg ("puzzle2 missplaced: ", puzzle2);
   time.Start ();
   greedyAlgorithm (puzzle2, "h2");
   time.Stop ();
   duration2 = time.GetDurationInMiliSec ();
   //______________________________________________________________________________
   System.out.println ();
   System.out.println("================================================================\n");
   System.out.println("HEURISTIC1: Time in miliseconds: " + duration1 + "\n");
   System.out.println("================================================================\n");
   System.out.println("HEURISTIC2: Time in miliseconds: " + duration2 + "\n");
   System.out.println("================================================================");
   System.out.println ();
   //------------------------------------------------------------------------------
   }
}
