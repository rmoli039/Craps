/*=======================================================================
|   Source code:  Craps.java
|
|         Class:  Craps 
|
|        Author:  Richard Molina
|    Student ID:  6140544
|    Assignment:  Program #3 - Craps
|  
|            Course:  COP 3337 (Intermediate Programming)
|           Section:  U08
|        Instructor:  William Feild  
|          Due Date:  9 October 2018, at the beginning of class 
|
|	I hereby certify that this collective work is my own
|	and none of it is the work of any other person or entity.
|	______________________________________ [Signature]
|  
|     Language:  Java 
|  Compile/Run:   
| 		javac Craps.java
| 
|        Purpose:  This class allows the user to construct the game of Craps,
|				   play a game, get the outcome of the game for the player,
|				   get the number of dice rolls it took to finish the game,
|				   and reset the game for a new play-through.
|
|  Inherits From:  None
|
|     Interfaces:  None
|
|  +-----------------------------------------------------------------------
|
|      Constants:  NATURAL_ROLL is initialized to 7 because rolling a 7 in
|				   Craps is called a 'natural'.
|
|				   YOLEVEN_ROLL is initialized to 11 because rolling an 11
|				   in Craps is called a 'yo-leven'.
|
|				   SNAKEEYES_ROLL is initialized to 2 because rolling a 2 in
|				   Craps is called 'snake eyes'.
|
|				   ACEDEUCE_ROLL is initialized to 3 because rolling a 3 in
|				   Craps is called an 'ace deuce'.
|
|				   BOXCARS_ROLL is initialized to 12 because rolling a 12 in
|				   Craps is called 'box cars'.
|
| +-----------------------------------------------------------------------
|
|     Constructors:  Craps()
|                     - Constructs the game of Craps, setting all initial
|						values to 0/false
|					  - Constructs a die object with 6 sides, which is the
|						type of die used in Craps
|
|    Class Methods:  resetGame()
|					  - Sets the point and totalRolls values to 0 and the
|						win variable to false, allowing for a new game to
|						be played
|
| Instance Methods:  playGame()
|					  - Resets the game values
|					  - Plays a game of non-betting Craps to the end
|					  - Tracks whether the player wins or loses and how many times
|					    the dice were rolled
|					 getGameResult()
|					  - Returns a boolean for whether the player won the game
|					 getTotalRolls()
|					  - Returns the number of rolls it took to finish the game
|
|  *===========================================================================*/

public class Craps
{
	private int point;
	private int totalRolls;
	private boolean win;
	private Die gameDie;
	private static final int NATURAL_ROLL = 7;
	private static final int YOLEVEN_ROLL = 11;
	private static final int SNAKEEYES_ROLL = 2;
	private static final int ACEDEUCE_ROLL = 3;
	private static final int BOXCARS_ROLL = 12;
	
	public Craps()
	{
		point = 0;
		totalRolls = 0;
		win = false;
		gameDie = new Die(6);
	}
	
	/*---------------------------- playGame ----------------------------
    |  Method playGame ()
    |
    |  Purpose:  Resets the game values and plays a new game of non-betting
    |			 Craps to the end. Craps involves rolling two standard
    |			 dice at a time and comparing the sum to fixed values,
    |			 which determine if the player wins or loses. Rolls are
    |			 made continuously until a win or loss roll is made.
    |			 Tracks whether the player wins or loses and how many
    |			 times the dice were rolled.
    |
    |  @param	
    |
    |  @return  void
    *-------------------------------------------------------------------*/
	public void playGame()
	{
		resetGame();
		point = gameDie.rollDice(2,6);
		totalRolls++;
		
		if (point == NATURAL_ROLL || point == YOLEVEN_ROLL)
		{
			win = true;
		}	//close if
		else if (point != SNAKEEYES_ROLL && point != ACEDEUCE_ROLL && point != BOXCARS_ROLL)
		{
			int newRoll = 0;
			do {
				newRoll = gameDie.rollDice(2,6);
				totalRolls++;
			} while (newRoll != point && newRoll != NATURAL_ROLL);	//close do while
			
			if (newRoll == point)
			{
				win = true;
			}	//close if
			else
			{
			   win = false;
			}	//close else
		}	//close else if
		else
		{
			win = false;
		}	//close else
	}	//close playGame method
	
	/*---------------------------- getGameResult ----------------------------
    |  Method getGameResult ()
    |
    |  Purpose:  Return whether or not the player won the last game of Craps.
    |
    |  @param	 
    |
    |  @return  true/false whether the player won
    *-------------------------------------------------------------------*/
	public boolean getGameResult()
	{
		return win;
	}	//close getGameResult method
	
	/*---------------------------- getTotalRolls ----------------------------
    |  Method getTotalRolls ()
    |
    |  Purpose:  Return the number of dice rolls in the last game of Craps.
    |
    |  @param	 
    |
    |  @return  the total number of dice rolls
    *-------------------------------------------------------------------*/
	public int getTotalRolls()
	{
		return totalRolls;
	}	//close getTotalRolls method
	
	/*---------------------------- resetGame ----------------------------
    |  Method resetGame ()
    |
    |  Purpose:  Sets the point and totalRolls values to 0 and the
	|			 win variable to false, allowing for a new game to be played.
    |
    |  @param	 
    |
    |  @return  void
    *-------------------------------------------------------------------*/
	private void resetGame()
	{
		point = 0;
		totalRolls = 0;
		win = false;
	}	//close resetGame method
}