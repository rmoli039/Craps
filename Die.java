/*=======================================================================
|   Source code:  Die.java
|
|         Class:  Die 
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
| 		javac Die.java
| 
|        Purpose:  This class allows the user to construct 'die' objects, each 
|				   representing a real-world number die with an input number of
|				   sides, from 2 to 100. The user can then roll a single die
|				   or an input number of dice and receive the random number
|				   rolled/sum of the random numbers rolled.				
|
|  Inherits From:  None
|
|     Interfaces:  None
|
|  +-----------------------------------------------------------------------
|
|      Constants:  None
|
| +-----------------------------------------------------------------------
|
|     Constructors:  Die()
|                     - Constructs a die with the standard 6 sides
|					 Die(int numSides)
|					  - Takes a number of sides as input
|					  - Constructs a die with the input number of sides,
|					    rounding numbers < 2 to 2 and numbers > 100 to 100
|
|    Class Methods:  None
|
| Instance Methods:  rollDie(), rollDie(int numSides)
|					  - Returns a random rolled number for the die object
|						based on its constructed number of sides
|					  - User can input a number of sides to override the
|						constructed number of sides
|					 rollDice(int dice), rollDice(int dice, int numSides)
|					  - Calls the rollDie() method an input number of times and
|					    sums the total value rolled
|					  - User can input a number of sides to override the
|						constructed number of sides
|
|     Source:  Horstmann, Cay S. Big Java: Early Objects, 5th Edition. Wiley,
|					01/2013, pp.283-284. [Yuzu].
|
|  *===========================================================================*/

import java.util.Random;		//Allows the use of random numbers

public class Die
{
	private int sides;
	private Random generator;
	
	public Die()
	{
		sides = 6;
		generator = new Random();
	}
		
	public Die(int numSides)
	{
		//Rounds input < 2 to 2 & input > 100 to 100, the real-life bounds of a fair die
		if (numSides < 2)
		{
			sides = 2;
		}	//close if
		else if (numSides > 100)
		{
			sides = 100;
		}	//close else
		else
		{
			sides = numSides;
		}	//close else
		generator = new Random();
	}
	
	/*---------------------------- rollDie ----------------------------
    |  Method rollDie ()
    |
    |  Purpose:  Use a random number generator to simulate rolling a number
    |			 die with the constructed number of sides.
    |
    |  @param	 
    |
    |  @return  the value "rolled" by the random number generator,
    |			between 1 and the number of constructed sides, inclusive
    *-------------------------------------------------------------------*/
	public int rollDie()
	{
		return 1 + generator.nextInt(sides);
	}	//close rollDie method

	/*---------------------------- rollDie ----------------------------
    |  Method rollDie (numSides)
    |
    |  Purpose:  Use a random number generator to simulate rolling a number
    |			 die with an input number of sides. Rounds input < 2 to 2 & 
    |			 input > 100 to 100, the real-life bounds of a fair die.
    |
    |  @param	numSides the desired number of sides for the die rolled
    |					 by the user 
    |
    |  @return  the value "rolled" by the random number generator,
    |			between 1 and the number of input sides, inclusive
    *-------------------------------------------------------------------*/
	public int rollDie(int numSides)
	{
		if (numSides < 2)
		{
			return 1 + generator.nextInt(2);
		}	//close if
		else if (numSides > 100)
		{
			return 1 + generator.nextInt(100);
		}	//close else if
		else
		{
			return 1 + generator.nextInt(numSides);
		}	//close else
	}	//close rollDie method
	
	/*---------------------------- rollDice ----------------------------
    |  Method rollDice (dice)
    |
    |  Purpose:  Calls the rollDie() method an input number of times and
    |			 sums the total value rolled.
    |
    |  @param	dice the desired number of values to roll and sum
    |
    |  @return  the sum of the values "rolled" by the random number
    |			generator, between 1 and the number of constructed sides,
    |			inclusive
    *-------------------------------------------------------------------*/
	public int rollDice(int dice)
	{
		int total = 0;
		for (int counter = 1; counter <= dice; counter++)
		{
			total += rollDie(sides);
		}	//close for
		return total;
	}	//close rollDice method
	
	/*---------------------------- rollDice ----------------------------
    |  Method rollDice (dice, numSides)
    |
    |  Purpose:  Calls the rollDie() method an input number of times and
    |			 sums the total value rolled. Rolls dice with an input
    |			 number of sides.
    |
    |  @param	dice the desired number of values to roll and sum
    
    |			numSides the desired number of sides for the dice rolled
    |					 by the user 
    |
    |  @return  the sum of the values "rolled" by the random number
    |			generator, between 1 and the number of input sides,
    |			inclusive
    *-------------------------------------------------------------------*/
	public int rollDice(int dice, int numSides)
	{
		int total = 0;
		for (int counter = 1; counter <= dice; counter++)
		{
			total += rollDie(numSides);
		}	//close for
		return total;
	}	//close rollDice method
}